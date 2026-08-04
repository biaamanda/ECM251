
public class NomeVolume {

    private String nome;
    private double peso;
    private double realPeso;
    private double custo;

    public NomeVolume() {
    }

    public NomeVolume(String nome, double peso, double realPeso) {
        this.nome = nome;
        this.peso = peso;
        this.realPeso = realPeso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getpeso() {
        return peso;
    }

    public void setpeso(double peso) {
        this.peso = peso;
    }

    public double getrealPeso() {
        return peso;
    }

    public void setrealPeso(double realPeso) {
        this.realPeso = realPeso;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public void calcularCusto(double taxaManuseio) {
        this.custo = this.peso * this.realPeso + taxaManuseio;
    }

    @Override
    public String toString() {
        return String.format("Custo: R$ %.2f", nome, peso, realPeso, custo);
    }
}
