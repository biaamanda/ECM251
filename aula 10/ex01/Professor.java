package ex01;
/*Crie a classe Professor com três construtores (um padrão, um
que receba só matrícula e outro que receba todos os
parâmetros), métodos de acesso e modificadores e os atributos
privados nome, do tipo String, idade, do tipo int e matricula, do
tipo int. Crie o método toString() que retorna o valor dos
atributos.
Crie os métodos de persistência da classe Professor:
- public void inserir(Connection conn);
- public void alterar(Connection conn);
- public void excluir(Connection conn);
- public void carregar(Connection conn); */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Professor {
    private String nome;
    private int idade;
    private int matricula;

    // Construtor padrão
    public Professor() {
        this.nome = "";
        this.idade = 0;
        this.matricula = 0;
    }

    // Construtor com apenas matrícula
    public Professor(int matricula) {
        this.matricula = matricula;
    }

    // Construtor completo
    public Professor(String nome, int idade, int matricula) {
        this.nome = nome;
        this.idade = idade;
        this.matricula = matricula;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String toString() {
        return "Professor [nome=" + nome + ", idade=" + idade + ", matricula=" + matricula + "]";
    }

    // Inserir no banco
    public void inserir(Connection conn) {
        String sql = "INSERT INTO professor (matricula, nome, idade) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, matricula);
            stmt.setString(2, nome);
            stmt.setInt(3, idade);

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Professor inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir professor: " + e.getMessage());
        }
    }

    // Alterar dados no banco
    public void alterar(Connection conn) {
        String sql = "UPDATE professor SET nome = ?, idade = ? WHERE matricula = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.setInt(3, matricula);

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Professor alterado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao alterar professor: " + e.getMessage());
        }
    }

    // Excluir do banco
    public void excluir(Connection conn) {
        String sql = "DELETE FROM professor WHERE matricula = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, matricula);

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Professor excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir professor: " + e.getMessage());
        }
    }

    // Carregar dados do banco
    public void carregar(Connection conn) {
        String sql = "SELECT nome, idade FROM professor WHERE matricula = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, matricula);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                this.nome = rs.getString("nome");
                this.idade = rs.getInt("idade");
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro ao carregar professor: " + e.getMessage());
        }
    }
}