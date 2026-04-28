public class Comissionado extends Empregado {
    private double totalVendas;
    private double percentualComissao;

    public Comissionado() {
        super();
        this.totalVendas = 0.0;
        this.percentualComissao = 0.0;
    }

    public Comissionado(String nome, String sobrenome, String cpf, int matricula, double totalVendas, double percentualComissao) {
        super(nome, sobrenome, cpf, matricula);
        this.totalVendas = totalVendas;
        this.percentualComissao = percentualComissao;
    }

    @Override
    public double salario() {
        return totalVendas * (percentualComissao / 100);
    }

    @Override
    public String dados() {
        return super.dados() +
               "\nTotal de Vendas: R$ " + totalVendas +
               "\nPercentual de Comissão: " + percentualComissao + "%";
    }
}
