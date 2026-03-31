package ex02;
/*b) A classe ContaCorrente tem os atributos numero e digito, ambos int, o atributo agencia do tipo Agencia e o atributo saldo do tipo double. 
Crie um construtor que receba os atributos como parâmetros e os métodos de acesso e os modificadores. Crie também um método depositar() que receba um parâmetro double com o valor do depósito e aumente o saldo da conta. Crie também um método sacar() que receba um parâmetro double com o valor do saque e diminua o saldo da conta;
A conta não pode ficar negativa. Neste caso, deve ser dada uma mensagem que o saque não foi efetuado e o retorno deve ser zero. Caso contrário o retorno deve ser o valor sacado. 
Crie também um método consultarSaldo() que não receba parâmetros e retorne o saldo. 
Crie, finalmente, um método imprimirSaldo() que imprima o numero da conta corrente com dígito, o número da agência com dígito e o saldo da conta corrente;
Ainda na classe ContaCorrente, o número da conta deve ter no máximo 4 dígitos e ser positivo. O digito da conta deve ser validado a partir do seguinte algoritmo de módulo 11: multiplique o primeiro dígito da conta por 4, o segundo por 6, o terceiro por 8 e o quarto por 2; some
tudo e calcule o resto da divisão (módulo) da soma por 11. Este é o valor do dígito.
Obs.: se o resultado for 10 o dígito é 0; */

public class ContaCorrente {
    private int number;
    private int digit;
    private Agencia agencia;
    private double balance;

    public ContaCorrente(int number, Agencia agencia, double balance) {
        if (number <= 0 || number > 9999) {
            throw new IllegalArgumentException("Numero invalido.");
        }

        this.number = number;
        this.digit = calculateDigit(number);
        this.agencia = agencia;
        this.balance = balance;
    }

    private int calculateDigit(int number) {
        int[] weights = {4, 6, 8, 2};
        int sum = 0;
        String numStr = String.format("%04d", number);

        for (int i = 0; i < 4; i++) {
            sum += Character.getNumericValue(numStr.charAt(i)) * weights[i];
        }
        int mod = sum % 11;
        return (mod == 10) ? 0 : mod;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            balance += valor;
        }
    }

public double sacar(double valor) {
    if (valor <= 0) {
        System.out.println("Valor inválido!");
        return 0;
    }

    if (valor > balance) {
        System.out.println("Saldo insuficiente.");
        return 0;
    }

    balance -= valor;
    return valor;
}

    public double consultarSaldo() {
        return balance;
    }

    public void imprimirSaldo() {
        System.out.println("Conta: " + number + "-" + digit);
        System.out.println("Agência: " + agencia.getNumber() + "-" + agencia.getDigit());
        System.out.println("Saldo: R$ " + balance);
    }
}
