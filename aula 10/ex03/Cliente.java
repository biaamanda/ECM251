package ex03;
/*Crie as classes Cliente, ContaCorrente e Agencia conforme abaixo:
a) A classe Cliente possui os atributos nome e cpf, ambos do tipo
String, e um atributo conta do tipo ContaCorrente. Crie um
construtor que recebe os atributos como parâmetros e os
métodos de acesso e os modificadores; 

e) Em cada uma das três classes crie os métodos CRUD de
persistência. O método atualizar() sempre deve atualizar todos os
campos da tabela, exceto a chave primária (PK). Não persista
todos os campos. Faça conforme abaixo:
Cliente: cpf (pk), nome
Conta Corrente: numero (pk), digito
Agencia: numero (pk), digito*/

public class Cliente {
    private String nome;
    private String cpf;
    private ContaCorrente conta;

    public Cliente(String nome, String cpf, ContaCorrente conta) {
        this.nome = nome;
        this.cpf = cpf;
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ContaCorrente getConta() {
        return conta;
    }

    public void setConta(ContaCorrente conta) {
        this.conta = conta;
    }
}
