package ex02;
/*Crie a classe Disciplina com três construtores (um padrão, um
que receba só codigo e outro que receba todos os parâmetros),
métodos de acesso e modificadores e os atributos privados
nome, do tipo String, professores, do tipo ArrayList<Professor>,
codigo, do tipo String. Crie o método toString() que retorna o
valor dos atributos.
Crie os métodos de persistência da classe Disciplina:
- public void inserir(Connection conn);
- public void alterar(Connection conn);
- public void excluir(Connection conn);
- public void carregar(Connection conn) */

import java.util.ArrayList;

import ex01.Professor;


public class Disciplina {
    private String nome;
    private ArrayList<Professor> professores;
    private String codigo;

    public Disciplina(String nome, ArrayList<Professor> professores, String codigo) {
        this.nome = nome;
        this.professores = professores;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(ArrayList<Professor> professores) {
        this.professores = professores;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
