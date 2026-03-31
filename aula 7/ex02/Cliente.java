package ex02;
/*Crie as classes Cliente, ContaCorrente e Agencia conforme abaixo:
a) A classe Cliente possui os atributos nome e cpf, ambos do tipo String, e um atributo conta do tipo ContaCorrente. Crie um construtor que receba os atributos como parâmetros e os métodos de acesso e os modificadores */

public class Cliente {
    private String name;
    private String cpf;
    private ContaCorrente account;

    public Cliente(String name, String cpf, ContaCorrente account) {
        this.name = name;
        this.cpf = cpf;
        this.account = account;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ContaCorrente getAccount() {
        return account;
    }
    public void setAccount(ContaCorrente account) {
        this.account = account;
    }
}
