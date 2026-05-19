package ex03;
/*b) A classe ContaCorrente tem os atributos numero e digito,
ambos int, o atributo agencia do tipo Agencia e o atributo saldo
do tipo double. Crie um construtor que recebe os atributos como
parâmetros e os métodos de acesso e os modificadores. Crie
também um método depositar() que receba um parâmetro
double com o valor do depósito e aumente o saldo da conta. Crie
também um método sacar() que receba um parâmetro double
com o valor do saque e diminua o saldo da conta
A conta não pode ficar negativa. Neste caso, deve ser dada
uma mensagem que o saque não foi efetuado e o retorno deve
ser zero. Caso contrário o retorno deve ser o valor sacado. Crie
também um método consultarSaldo() que não recebe
parâmetros e retorne o saldo. Crie, finalmente, um método
imprimirSaldo() que imprima o número da conta corrente com
dígito, o número da agência com dígito e o saldo da conta
corrente
Ainda na classe ContaCorrente, o número do conta deve ter no
máximo 4 dígitos e ser positivo. O digito da conta deve ser
validado a partir do seguinte algoritmo de módulo 11:
“Multiplique o primeiro dígito da conta por 4, o segundo por 6, o
terceiro por 8 e o quarto por 2; some tudo e calcule o resto da
divisão (módulo) da soma por 11. Este é o valor do dígito”;
- Obs: se o resultado for 10 o dígito é 0. */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaCorrente {

    private int numero;
    private int digito;
    private Agencia agencia;
    private double saldo;

    public ContaCorrente(int numero, Agencia agencia, double saldo) {

        if (numero <= 0 || numero > 9999)
            throw new IllegalArgumentException("Número da conta inválido");

        this.numero = numero;
        this.digito = calcularDigito(numero);
        this.agencia = agencia;
        this.saldo = saldo;
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

    public void depositar(double valor) {
        if (valor > 0)
            saldo += valor;
    }

    public double sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return valor;
        } else {
            System.out.println("Saque não efetuado. Saldo insuficiente.");
            return 0;
        }
    }

    public double consultarSaldo() {
        return saldo;
    }

    public void imprimirSaldo() {
        System.out.println("Conta: " + numero + "-" + digito +
                " | Agência: " + agencia.getNumero() + "-" + agencia.getDigito() +
                " | Saldo: R$ " + saldo);
    }

    // CRUD (persistindo apenas numero e digito)

    public void inserir(Connection conn) {
        String sql = "INSERT INTO conta_corrente (numero, digito) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            stmt.setInt(2, digito);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Connection conn) {
        String sql = "UPDATE conta_corrente SET digito = ? WHERE numero = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, digito);
            stmt.setInt(2, numero);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Connection conn) {
        String sql = "DELETE FROM conta_corrente WHERE numero = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void carregar(Connection conn) {
        String sql = "SELECT digito FROM conta_corrente WHERE numero = ?";
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
