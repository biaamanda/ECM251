/*Crie a classe Funcionario com os atributos: nome, cargo e salário.
Adicione um método que retorne o salário anual.
Na classe de teste, leia os dados, calcule e exiba o salário anual. */

public class Funcionario {
    String name;
    String position;
    double salary;

    public Funcionario(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position){
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }

    public double getAnnualSalary() {
        return salary * 12;
    }
}
