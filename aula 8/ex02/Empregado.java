public class Empregado extends PessoaFisica {
    private int matricula;

    public Empregado() {
        super();
        this.matricula = 0;
    }

    public Empregado(String nome, String sobrenome, String cpf, int matricula) {
        super(nome, sobrenome, cpf);
        this.matricula = matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public double salario() {
        return 0.0;
    }

    @Override
    public String dados() {
        return super.dados() +
               "\nMatrícula: " + matricula;
    }
}