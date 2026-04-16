import java.util.Scanner;

public class TesteCidade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da cidade: ");
        String cityName = scanner.nextLine();

        System.out.print("Digite o estado: ");
        String state = scanner.nextLine();

        System.out.print("Digite a populaçao: ");
        int population = scanner.nextInt();

        Cidade cidade = new Cidade(cityName, state, population);

        System.out.println("A cidade de " + cidade.getName() + ", localizada em " + cidade.getState() + ", possui " + cidade.getPopulation() + " habitantes.");

        scanner.close();
    }
}
