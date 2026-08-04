import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private int id;
    private String nome;
    private double taxaManuseio;
    private final List<NomeVolume> nomeVolume = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nome, double taxaManuseio) {
        this.nome = nome;
        this.taxaManuseio = taxaManuseio;
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

    public double getTaxaManuseio() {
        return taxaManuseio;
    }

    public void setTaxaManuseio(double taxaManuseio) {
        this.taxaManuseio = taxaManuseio;
    }

    public List<NomeVolume> getNomeVolume() {
        return nomeVolume;
    }

    public void addNomeVolume(NomeVolume nomeVolume) {
        nomeVolume.add(nomeVolume);
    }

    public void calcularTodosCustos() {
        for (NomeVolume n : nomeVolume) {
            n.calcularCusto(taxaManuseio);
        }
    }

    public double getCustoTotal() {
        double total = 0;
        for (NomeVolume n : nomeVolume) {
            total += n.getCusto();
        }
        return total;
    }
}
