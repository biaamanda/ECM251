import java.util.Scanner;

public class TesteFuncionario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   

        System.out.print("Nome do funcionario: ");
        String name = scanner.nextLine();
        System.out.print("Posicao do funcionario: ");
        String position = scanner.nextLine();
        System.out.print("Salario do funcionario: ");
        double salary = scanner.nextDouble();

        Funcionario funcionario = new Funcionario(name, position, salary);

        System.out.println("\n--- Funcionario Criado ---");
        System.out.println("Nome: " + funcionario.getName());
        System.out.println("Posicao: " + funcionario.getPosition());
        System.out.println("Salario anual: " + funcionario.getAnnualSalary());
        
        scanner.close();
    }
}
