/*c) Escrever a classe Cheque, como extensão da classe
Pagamento, com o atributo próprio numeroDoCheque
(String) e escrever, nessa classe, métodos construtores,
métodos de acesso e métodos modificadores para o
atributo próprio */

public class Cheque extends Pagamento {
    private String numeroDoCheque;

    public Cheque(String nomeDoPagador, String cpf, double valorASerPago, String numeroDoCheque) {
        super(nomeDoPagador, cpf, valorASerPago);
        this.numeroDoCheque = numeroDoCheque;
    }

    public String getNumeroDoCheque() {
        return numeroDoCheque;
    }

    public void setNumeroDoCheque(String numeroDoCheque) {
        this.numeroDoCheque = numeroDoCheque;
    }
}
