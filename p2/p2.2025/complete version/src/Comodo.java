/**
 * Representa um cômodo da residência, com seu consumo (kWh) e o custo
 * calculado a partir do valor do kWh do cliente.
 */
public class Comodo {

    private String nome;
    private double consumo;
    private double custo;

    public Comodo() {
    }

    public Comodo(String nome, double consumo) {
        this.nome = nome;
        this.consumo = consumo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    /**
     * Custo (R$) = Valor do kWh (R$) * Consumo (kWh)
     */
    public void calcularCusto(double valorKwh) {
        this.custo = valorKwh * this.consumo;
    }

    @Override
    public String toString() {
        return String.format("%s - Consumo: %.2f kWh - Custo: R$ %.2f", nome, consumo, custo);
    }
}
