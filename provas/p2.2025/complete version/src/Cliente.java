import java.util.ArrayList;
import java.util.List;

/**
 * Representa o cliente (usuário da residência), com o valor do kWh
 * contratado e a lista dos cômodos consultados/cadastrados.
 */
public class Cliente {

    private int id;
    private String nome;
    private double valorKwh;
    private final List<Comodo> comodos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nome, double valorKwh) {
        this.nome = nome;
        this.valorKwh = valorKwh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorKwh() {
        return valorKwh;
    }

    public void setValorKwh(double valorKwh) {
        this.valorKwh = valorKwh;
    }

    public List<Comodo> getComodos() {
        return comodos;
    }

    public void addComodo(Comodo comodo) {
        comodos.add(comodo);
    }

    public void calcularTodosCustos() {
        for (Comodo c : comodos) {
            c.calcularCusto(valorKwh);
        }
    }

    public double getCustoTotal() {
        double total = 0;
        for (Comodo c : comodos) {
            total += c.getCusto();
        }
        return total;
    }
}
