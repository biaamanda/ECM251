public class ClienteTest {

    public static void main(String[] args) {
        Cliente cliente = new Cliente("Instituto Maua de Tecnologia - IMT", 500.00);
        cliente.addNomeVolumeFields(new NomeVolume("Notebook", 2.5, 100.00));
        cliente.addNomeVolumeFields(new NomeVolume("Canetão", 2.0, 20.00));
        cliente.addNomeVolumeFields(new NomeVolume("Datashow", 5.0, 120.00));

        cliente.calcularTodosCustos();

        System.out.println("Cliente: " + cliente.getNome());
        System.out.printf("Valor do kWh: R$ %.2f%n", cliente.getTaxaManuseio());
        for (NomeVolume c : cliente.getNomeVolume()) {
            System.out.println("  " + c);
        }
        System.out.printf("Custo total: R$ %.2f%n%n", cliente.getCustoTotal());

    }

}
