public class Desempregado extends PessoaFisica {
    private double seguroDesemprego;

    public Desempregado() {
        super();
        this.seguroDesemprego = 0.0;
    }

    public Desempregado(String nome, String sobrenome, String cpf, double seguroDesemprego) {
        super(nome, sobrenome, cpf);
        this.seguroDesemprego = seguroDesemprego;
    }

    public double getSeguroDesemprego() {
        return seguroDesemprego;
    }

    public void setSeguroDesemprego(double seguroDesemprego) {
        this.seguroDesemprego = seguroDesemprego;
    }

    @Override
    public String dados() {
        return super.dados() +
               "\nSeguro Desemprego: R$ " + seguroDesemprego;
    }
}
