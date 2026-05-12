package ex01;
/*Crie a classe Professor com três construtores (um padrão, um
que receba só matrícula e outro que receba todos os
parâmetros), métodos de acesso e modificadores e os atributos
privados nome, do tipo String, idade, do tipo int e matricula, do
tipo int. Crie o método toString() que retorna o valor dos
atributos.
Crie os métodos de persistência da classe Professor:
- public void inserir(Connection conn);
- public void alterar(Connection conn);
- public void excluir(Connection conn);
- public void carregar(Connection conn); */

public class Professor {
    private String nome;
    private int idade;
    private int matricula;

    public Professor(String nome, int idade, int matricula) {
        this.nome = nome;
        this.idade = idade;
        this.matricula = matricula;
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

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
}
