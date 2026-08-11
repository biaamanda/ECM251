package ex02;
/*Crie a classe Disciplina com três construtores (um padrão, um
que receba só codigo e outro que receba todos os parâmetros),
métodos de acesso e modificadores e os atributos privados
nome, do tipo String, professores, do tipo ArrayList<Professor>,
codigo, do tipo String. Crie o método toString() que retorna o
valor dos atributos.
Crie os métodos de persistência da classe Disciplina:
- public void inserir(Connection conn);
- public void alterar(Connection conn);
- public void excluir(Connection conn);
- public void carregar(Connection conn) */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Disciplina {

    private String nome;
    private ArrayList<Professor> professores;
    private String codigo;

    public Disciplina() {
        this.professores = new ArrayList<>();
    }

    public Disciplina(String codigo) {
        this.codigo = codigo;
        this.professores = new ArrayList<>();
    }

    public Disciplina(String nome, ArrayList<Professor> professores, String codigo) {
        this.nome = nome;
        this.professores = professores;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(ArrayList<Professor> professores) {
        this.professores = professores;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Disciplina [codigo=" + codigo +
               ", nome=" + nome +
               ", professores=" + professores + "]";
    }

    public void inserir(Connection conn) {
        String sql = "INSERT INTO disciplina (codigo, nome) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.codigo);
            stmt.setString(2, this.nome);
            stmt.executeUpdate();
            System.out.println("Disciplina inserida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Connection conn) {
        String sql = "UPDATE disciplina SET nome = ? WHERE codigo = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.nome);
            stmt.setString(2, this.codigo);
            stmt.executeUpdate();
            System.out.println("Disciplina alterada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Connection conn) {
        String sql = "DELETE FROM disciplina WHERE codigo = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.codigo);
            stmt.executeUpdate();
            System.out.println("Disciplina excluída com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void carregar(Connection conn) {
        String sql = "SELECT nome FROM disciplina WHERE codigo = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                this.nome = rs.getString("nome");
                System.out.println("Disciplina carregada com sucesso!");
            } else {
                System.out.println("Disciplina não encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}