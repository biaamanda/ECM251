public class Horista extends Empregado {
    private double valorHora;
    private int horasTrabalhadas;

    public Horista() {
        super();
        this.valorHora = 0.0;
        this.horasTrabalhadas = 0;
    }

    public Horista(String nome, String sobrenome, String cpf, int matricula, double valorHora, int horasTrabalhadas) {
        super(nome, sobrenome, cpf, matricula);
        this.valorHora = valorHora;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public double salario() {
        return valorHora * horasTrabalhadas;
    }

    public String dados() {
        return super.dados() +
               "\nValor Hora: R$ " + valorHora +
               "\nHoras Trabalhadas: " + horasTrabalhadas;
    }
}