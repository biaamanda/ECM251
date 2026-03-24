/*Crie a classe Disciplina com seu construtor, métodos de acesso
e modificadores e os atributos privados nome, do tipo String,
professor, do tipo String, semestre, do tipo int, ofertada, do
tipo boolean. Crie a classe TesteDisciplina com o método
main, leia dos dados necessários para criar uma disciplina,
crie uma disciplina e exiba a disciplina criada; */

public class Disciplina {
    private String name;
    private String professor;
    private int semester;
    private boolean offered;

    public Disciplina(String name, String professor, int semester, boolean offered) {
        this.name = name;
        this.professor = professor;
        this.semester = semester;
        this.offered = offered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public boolean isOffered() {
        return offered;
    }

    public void setOffered(boolean offered) {
        this.offered = offered;
    }
}