/*b. Crie a classe Disciplina com seu construtor, métodos de acesso e modificadores e os atributos privados nome, do tipo String, pratica, do tipo boolean. Crie o método getDados() que retorna os valores dos atributos */

public class Disciplina {
    private String name;
    private boolean practice;

    public Disciplina(String name, boolean practice) {
        this.name = name;
        this.practice = practice;
    }
    public String getDados(){
        return "Nome: " + name + "\nPratica: " + practice;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isPractice(){
        return practice;
    }
}
