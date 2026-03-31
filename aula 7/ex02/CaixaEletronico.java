package ex02;
/*e) Para testar, faça uma classe CaixaEletronico, que irá conter o método main(). Nele, instancie um cliente com os seguintes dados:
Nome: Ademar Apior
CPF: 123231518-12
Conta Corrente: 1234 Dígito: 4
Agencia: 7890 Dígito: 5
Saldo Inicial: 150.00

Operações:
- sacar 140.0 (sucesso);
- consultar saldo (resultado é 10.0);
- sacar 200.0 (falha);
- consultar saldo (resultado é 10.0);
- depositar 25.45 (sucesso);
- imprimir saldo (além dos dados de cliente, account
e agencia, o saldo deve ser 35.45) */

public class CaixaEletronico {
    public static void main(String[] args) {
        Agencia agencia = new Agencia("Agencia Central", 7890);
        ContaCorrente account = new ContaCorrente(1234, agencia, 150.00);
        Cliente cliente = new Cliente("Ademar Apior", "123231518-12", account);

        System.out.println("Cliente: " + cliente.getName());
        System.out.println("CPF: " + cliente.getCpf());

        account.sacar(140.0);

        System.out.println("Saldo: " + account.consultarSaldo());

        account.sacar(200.0);

        System.out.println("Saldo: " + account.consultarSaldo());

        account.depositar(25.45);

        System.out.println("\n=== DADOS FINAIS ===");
        System.out.println("Cliente: " + cliente.getName());
        System.out.println("CPF: " + cliente.getCpf());
        account.imprimirSaldo();
    }
}
