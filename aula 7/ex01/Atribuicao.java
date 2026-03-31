/*c. Crie a classe Atribuicao com seu construtor, métodos de acesso e modificadores e os atributos privados professor, do tipo Professor, e disciplina, do tipo Disciplina. Crie o método getDados() que retorna os valores dos atributos; */

public class Atribuicao {
    private Professor professor;
    private Disciplina disciplina;

    public Atribuicao(Professor professor, Disciplina disciplina) {
        this.professor = professor;
        this.disciplina = disciplina;
    }
    public String getDados(){
        return professor.getDados() + "\n" + disciplina.getDados();
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}
