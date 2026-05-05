package ex01;
/*Dadas as classes Pessoa, FuncionarioAposentado e
Funcionario, sabendo que existem os atributos salario,
salarioAposentadoria, nome, idade e cargo e sabendo que o
método categoria(), dado abaixo, pertence à classe Funcionario, pede-se:
a) Construa as classes em Java, lembrando de considerar
construtores e incluir alguns métodos, implementando o encapsulamento;
b) Monte um programa de acesso;
c) Modifique o código para que seja implementado o
Polimorfismo (Sobrecarga e Sobreposição);
d) Dado o método categoria(): */

public class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }
}