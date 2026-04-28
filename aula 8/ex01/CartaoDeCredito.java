/*b) Escrever a classe CartaoDeCredito, como extensão da
classe Pagamento, com o atributo próprio
numeroDoCartao (String) e escrever, nessa classe,
métodos construtores, métodos de acesso e métodos
modificadores para o atributo próprio. */


public class CartaoDeCredito extends Pagamento {
    private String numeroDoCartao;

    public CartaoDeCredito(String nomeDoPagador, String cpf, double valorASerPago, String numeroDoCartao) {
        super(nomeDoPagador, cpf, valorASerPago);
        this.numeroDoCartao = numeroDoCartao;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }
}
