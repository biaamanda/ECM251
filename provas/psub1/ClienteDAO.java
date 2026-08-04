import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/cem_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "senha";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void salvar(Cliente cliente) throws SQLException {
        try (Connection conn = conectar()) {
            conn.setAutoCommit(false);
            try {
                String sqlCliente = "INSERT INTO cliente (nome, taxa_manuseio) VALUES (?, ?) "
                        + "ON DUPLICATE KEY UPDATE taxa_manuseio = VALUES(taxa_manuseio)";
                try (PreparedStatement ps = conn.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, cliente.getNome());
                    ps.setDouble(2, cliente.getTaxaManuseio());
                    ps.executeUpdate();
                }

                int clienteId = buscarIdPorNome(conn, cliente.getNome());
                cliente.setId(clienteId);

                try (PreparedStatement psDel = conn.prepareStatement("DELETE FROM nomeVolume WHERE cliente_id = ?")) {
                    psDel.setInt(1, clienteId);
                    psDel.executeUpdate();
                }

                String sqlNomeVolume = "INSERT INTO NomeVolume (cliente_id, nome, peso, real_peso, custo) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement psNomeVolume = conn.prepareStatement(sqlNomeVolume)) {
                    for (NomeVolume c : cliente.getNomeVolume()) {
                        psNomeVolume.setInt(1, clienteId);
                        psNomeVolume.setString(2, n.getNomeVolume());
                        psNomeVolume.setDouble(3, n.getPeso());
                        psNomeVolume.setDouble(3, n.getrealPeso());
                        psNomeVolume.setDouble(4, n.getCusto());
                        psNomeVolume.addBatch();
                    }
                    psNomeVolume.executeBatch();
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
                    cliente.setNome(rs.getString("Nome do Cliente: "));
                    cliente.setTaxaManuseio(rs.getDouble("Taxa de Manuseion (R$)"));

                    String sqlNomeVolumes = "SELECT * FROM nomeVolume WHERE cliente_id = ? ORDER BY id";
                    try (PreparedStatement psnomeVolume = conn.prepareStatement(sqlNomeVolumes)) {
                        psnomeVolume.setInt(1, cliente.getId());
                        try (ResultSet rsNomeVolumes = psnomeVolume.executeQuery()) {
                            while (rsNomeVolumes.next()) {
                                NomeVolumes c = new NomeVolumes(rsNomeVolumes.getString("Nome do Volume"), rsNomeVolumes.getDouble("Peso (kg)"), rsNomeVolumes.getDouble("R$/kg"));
                                cliente.addNomeVolumes(n);
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
