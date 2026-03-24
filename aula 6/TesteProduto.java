import java.util.Scanner;

public class TesteProduto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do produto: ");
        String name = scanner.nextLine();

        System.out.print("Digite o preço do produto: ");
        double price = scanner.nextDouble();

        System.out.print("Digite a quantidade do produto: ");
        int quantity = scanner.nextInt();

        Produto product = new Produto(name, price, quantity);

        System.out.println("\n--- Produto Criado ---");
        System.out.println("Nome: " + product.getName());
        System.out.println("Preço: " + product.getPrice());
        System.out.println("Quantidade: " + product.getQuantity());

        scanner.close();
    }
}
