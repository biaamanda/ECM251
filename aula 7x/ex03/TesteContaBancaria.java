import java.util.Scanner;

public class TesteContaBancaria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do titular da conta: ");
        String titular = scanner.nextLine();

        System.out.print("Digite o número da conta: ");
        String numeroConta = scanner.nextLine();

        System.out.print("Digite o saldo inicial da conta: ");
        double saldoInicial = scanner.nextDouble();
        

        ContaBancaria conta = new ContaBancaria(titular, numeroConta, saldoInicial);

        System.out.println("\n--- Conta Bancária Criada ---");
        System.out.println("Titular: " + conta.getName());
        System.out.println("Número da Conta: " + conta.getAccountNumber());
        System.out.println("Saldo Inicial: " + conta.getBalance());

        System.out.print("Valor do depósito: ");
        double deposito = scanner.nextDouble();
        conta.deposit(deposito);
        System.out.println("Após depósito: " + conta.getBalance());

        System.out.print("Valor do saque: ");
        double saque = scanner.nextDouble();
        if (conta.withdraw(saque)) {
            System.out.println("Após saque: " + conta.getBalance());
        } else {
            System.out.println("Saque nao realizado (valor inválido ou saldo insuficiente). Saldo: " + conta.getBalance());
        }

        scanner.close();
    }
}
