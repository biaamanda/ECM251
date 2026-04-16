import java.util.Scanner;

public class TesteProduto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do produto: ");
        String name = scanner.nextLine();

        System.out.print("Digite o código do produto: ");
        String code = scanner.nextLine();

        System.out.print("Digite o preço do produto: ");
        double price = scanner.nextDouble();

        Produto produto = new Produto(name, code, price);

        System.out.println("\nEtiqueta do Produto:");
        System.out.println("Nome: " + produto.getName());
        System.out.println("Código: " + produto.getCode());
        System.out.println("Preço: " + produto.getPrice());

        scanner.close();
    }
}
