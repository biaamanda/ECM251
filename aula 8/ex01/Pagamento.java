/*
Desenvolver a hierarquia de classes abaixo.
a) Escrever, a classe Pagamento, cujos
atributos únicos são nomeDoPagador (String), cpf (String) e valorASerPago (double)
 e escrever, nessa classe, métodos construtores, métodos de acesso e métodos
modificadores para os atributos; */

public class Pagamento {
    private String nomeDoPagador;
    private String cpf;
    private double valorASerPago;

    public Pagamento(String nomeDoPagador, String cpf, double valorASerPago) {
        this.nomeDoPagador = nomeDoPagador;
        this.cpf = cpf;
        this.valorASerPago = valorASerPago;
    }

    public String getNomeDoPagador() {
        return nomeDoPagador;
    }

    public void setNomeDoPagador(String nomeDoPagador) {
        this.nomeDoPagador = nomeDoPagador;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getValorASerPago() {
        return valorASerPago;
    }

    public void setValorASerPago(double valorASerPago) {
        this.valorASerPago = valorASerPago;
    }
}