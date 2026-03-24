/*Crie a classe Turma com seu construtor, métodos de acesso e
modificadores e os atributos privados nome, do tipo String,
curso, do tipo String, quantidadeDeAlunos, do tipo int, serie,
do tipo int */

public class Turma {
    private String name;
    private String course;
    private int studentCount;
    private int year;

    public Turma(String name, String course, int studentCount, int year) {
        this.name = name;
        this.course = course;
        this.studentCount = studentCount;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
}
