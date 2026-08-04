import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe responsável por toda a comunicação com o Banco de Dados MySQL
 * (schema "cem_db", tabelas "cliente" e "comodo" - vide sql/schema.sql).
 *
 * Ajuste URL/USER/PASSWORD conforme a instância MySQL usada na correção.
 */
public class ClienteDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/cem_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "senha";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Insere o cliente (ou atualiza o valor do kWh se o nome já existir)
     * e substitui os cômodos associados a ele.
     */
    public void salvar(Cliente cliente) throws SQLException {
        try (Connection conn = conectar()) {
            conn.setAutoCommit(false);
            try {
                String sqlCliente = "INSERT INTO cliente (nome, valor_kwh) VALUES (?, ?) "
                        + "ON DUPLICATE KEY UPDATE valor_kwh = VALUES(valor_kwh)";
                try (PreparedStatement ps = conn.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, cliente.getNome());
                    ps.setDouble(2, cliente.getValorKwh());
                    ps.executeUpdate();
                }

                int clienteId = buscarIdPorNome(conn, cliente.getNome());
                cliente.setId(clienteId);

                try (PreparedStatement psDel = conn.prepareStatement("DELETE FROM comodo WHERE cliente_id = ?")) {
                    psDel.setInt(1, clienteId);
                    psDel.executeUpdate();
                }

                String sqlComodo = "INSERT INTO comodo (cliente_id, nome, consumo, custo) VALUES (?, ?, ?, ?)";
                try (PreparedStatement psComodo = conn.prepareStatement(sqlComodo)) {
                    for (Comodo c : cliente.getComodos()) {
                        psComodo.setInt(1, clienteId);
                        psComodo.setString(2, c.getNome());
                        psComodo.setDouble(3, c.getConsumo());
                        psComodo.setDouble(4, c.getCusto());
                        psComodo.addBatch();
                    }
                    psComodo.executeBatch();
                }

                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }

    private int buscarIdPorNome(Connection conn, String nome) throws SQLException {
        String sql = "SELECT id FROM cliente WHERE nome = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        throw new SQLException("Cliente não encontrado após inserção: " + nome);
    }

    /**
     * Busca o cliente pelo nome e já traz os 3 cômodos associados,
     * com os custos recalculados.
     */
    public Cliente buscarPorNome(String nome) throws SQLException {
        try (Connection conn = conectar()) {
            String sqlCliente = "SELECT * FROM cliente WHERE nome = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlCliente)) {
                ps.setString(1, nome);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        return null;
                    }

                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setValorKwh(rs.getDouble("valor_kwh"));

                    String sqlComodos = "SELECT * FROM comodo WHERE cliente_id = ? ORDER BY id";
                    try (PreparedStatement psComodo = conn.prepareStatement(sqlComodos)) {
                        psComodo.setInt(1, cliente.getId());
                        try (ResultSet rsComodo = psComodo.executeQuery()) {
                            while (rsComodo.next()) {
                                Comodo c = new Comodo(rsComodo.getString("nome"), rsComodo.getDouble("consumo"));
                                cliente.addComodo(c);
                            }
                        }
                    }
                    cliente.calcularTodosCustos();
                    return cliente;
                }
            }
        }
    }
}
