package ex03;
/*d) A classe Agencia tem os atributos nome do tipo String, numero
e digito do tipo int. Crie um construtor que recebe os atributos
como parâmetros e os métodos de acesso e os modificadores. O
número e o dígito da agência devem seguir os mesmos padrões
do número e do dígito da conta corrente;*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Agencia {

    private String nome;
    private int numero;
    private int digito;

    public Agencia(String nome, int numero) {
        if (numero <= 0 || numero > 9999)
            throw new IllegalArgumentException("Número da agência inválido");

        this.nome = nome;
        this.numero = numero;
        this.digito = calcularDigito(numero);
    }

    private int calcularDigito(int numero) {
        String numStr = String.format("%04d", numero);

        int soma =
                Character.getNumericValue(numStr.charAt(0)) * 4 +
                Character.getNumericValue(numStr.charAt(1)) * 6 +
                Character.getNumericValue(numStr.charAt(2)) * 8 +
                Character.getNumericValue(numStr.charAt(3)) * 2;

        int resto = soma % 11;
        return (resto == 10) ? 0 : resto;
    }

    public String getNome() { 
        return nome; 
    }

    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public int getNumero() { 
        return numero; 
    }
    public int getDigito() { 
        return digito; 
    }


    public void inserir(Connection conn) {
        String sql = "INSERT INTO agencia (numero, digito) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            stmt.setInt(2, digito);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Connection conn) {
        String sql = "UPDATE agencia SET digito = ? WHERE numero = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, digito);
            stmt.setInt(2, numero);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Connection conn) {
        String sql = "DELETE FROM agencia WHERE numero = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void carregar(Connection conn) {
        String sql = "SELECT digito FROM agencia WHERE numero = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.digito = rs.getInt("digito");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}