import java.util.Scanner;

public class TestePessoa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome:");
        String name = sc.nextLine();
        System.out.println("Idade:");
        int age = sc.nextInt();
        System.out.println("Altura(em metros):");
        double height = sc.nextDouble();

        Pessoa pessoa = new Pessoa(name, age, height);

        System.out.println("\nBem vindo," + pessoa.getName() + "! Sua idade é " + pessoa.getAge() + " anos e sua altura é " + pessoa.getHeight() + " metros.");

        sc.close();
    }
}
