/*d) Escrever a classe Boleto, como extensão da classe
Pagamento, com os atributos próprio numeroDoBoleto
(String), dia (int), mes (int) e ano (int) de vencimento e
escrever, nessa classe, métodos construtores, métodos de
acesso e métodos modificadores para o atributo próprio */

public class Boleto extends Pagamento {
    private String numeroDoBoleto;
    private int dia;
    private int mes;
    private int ano;

    public Boleto(String nomeDoPagador, String cpf, double valorASerPago, String numeroDoBoleto, int dia, int mes, int ano) {
        super(nomeDoPagador, cpf, valorASerPago);
        this.numeroDoBoleto = numeroDoBoleto;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public String getNumeroDoBoleto() {
        return numeroDoBoleto;
    }

    public void setNumeroDoBoleto(String numeroDoBoleto) {
        this.numeroDoBoleto = numeroDoBoleto;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
