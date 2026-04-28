import java.util.Scanner;

public class teste {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome do pagador: ");
        String nomeDoPagador = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Valor a ser pago: ");
        double valorASerPago = scanner.nextDouble();

        CartaoDeCredito cartao = new CartaoDeCredito(nomeDoPagador, cpf, valorASerPago, "1234-5678-9012-3456");
        Cheque cheque = new Cheque(nomeDoPagador, cpf, valorASerPago, "987654321");
        Boleto boleto = new Boleto(nomeDoPagador, cpf, valorASerPago, "123456789", 30, 12, 2024);

        System.out.println("\n--- Detalhes do Pagamento ---");
        System.out.println("Cartão de Crédito:");
        System.out.println("Nome do Pagador: " + cartao.getNomeDoPagador());
        System.out.println("CPF: " + cartao.getCpf());
        System.out.println("Valor a ser Pago: " + cartao.getValorASerPago());
        System.out.println("Número do Cartão: " + cartao.getNumeroDoCartao());
        System.out.println("\nCheque:");
        System.out.println("Nome do Pagador: " + cheque.getNomeDoPagador());
        System.out.println("CPF: " + cheque.getCpf());
        System.out.println("Valor a ser Pago: " + cheque.getValorASerPago());
        System.out.println("Número do Cheque: " + cheque.getNumeroDoCheque());
        System.out.println("\nBoleto:");
        System.out.println("Nome do Pagador: " + boleto.getNomeDoPagador());
        System.out.println("CPF: " + boleto.getCpf());
        System.out.println("Valor a ser Pago: " + boleto.getValorASerPago());
        System.out.println("Número do Boleto: " + boleto.getNumeroDoBoleto());

        scanner.close();
    }
}