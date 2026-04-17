/*Crie a classe ContaBancaria com os atributos: número da conta, nome do titular e saldo.
Crie métodos para depositar e sacar valores (não permita saldo negativo).
Na classe de teste, simule uma operação de depósito e saque. */

public class ContaBancaria {
    String accountNumber;
    String name;
    double balance;

    public ContaBancaria(String accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double valor) {
        if (valor <= 0 || valor > balance) {
            return false;
        }
        balance -= valor;
        return true;
    }
}
