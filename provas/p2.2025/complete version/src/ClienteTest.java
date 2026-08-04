/**
 * Classe de teste das classes de modelo (Cliente/Comodo), sem depender
 * da GUI nem do Banco de Dados. Usa os mesmos valores do exemplo do
 * enunciado (Sala, Cozinha, Quarto) para validar o cálculo do custo.
 */
public class ClienteTest {

    public static void main(String[] args) {
        Cliente cliente = new Cliente("Brian Harold May", 0.50);
        cliente.addComodo(new Comodo("Sala", 100.0));
        cliente.addComodo(new Comodo("Cozinha", 50.0));
        cliente.addComodo(new Comodo("Quarto", 10.0));

        cliente.calcularTodosCustos();

        System.out.println("Cliente: " + cliente.getNome());
        System.out.printf("Valor do kWh: R$ %.2f%n", cliente.getValorKwh());
        for (Comodo c : cliente.getComodos()) {
            System.out.println("  " + c);
        }
        System.out.printf("Custo total: R$ %.2f%n%n", cliente.getCustoTotal());

        verificar("Custo da Sala", cliente.getComodos().get(0).getCusto(), 50.0);
        verificar("Custo da Cozinha", cliente.getComodos().get(1).getCusto(), 25.0);
        verificar("Custo do Quarto", cliente.getComodos().get(2).getCusto(), 5.0);
        verificar("Custo total", cliente.getCustoTotal(), 80.0);
    }

    private static void verificar(String descricao, double valorObtido, double valorEsperado) {
        String resultado = (Math.abs(valorObtido - valorEsperado) < 0.001) ? "OK" : "FALHA";
        System.out.printf("[%s] %s -> obtido: %.2f | esperado: %.2f%n",
                resultado, descricao, valorObtido, valorEsperado);
    }
}
