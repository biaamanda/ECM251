package ex01;
public class Funcionario extends Pessoa {
    private float salario;
    private String cargo;

    public Funcionario(String nome, int idade, float salario, String cargo) {
        super(nome, idade);
        this.salario = salario;
        this.cargo = cargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Método categoria()
    public String categoria() {
        if (salario <= 2000) {
            return "Categoria Júnior";
        } else if (salario <= 5000) {
            return "Categoria Pleno";
        } else {
            return "Categoria Sênior";
        }
    }

    // Sobrecarga (Polimorfismo)
    public String categoria(float bonus) {
        float salarioFinal = salario + bonus;

        if (salarioFinal <= 2000) {
            return "Categoria Júnior";
        } else if (salarioFinal <= 5000) {
            return "Categoria Pleno";
        } else {
            return "Categoria Sênior";
        }
    }

    public void exibirDados() {
        super.exibirDados();
        System.out.println("Cargo: " + cargo);
        System.out.println("Salário: R$ " + salario);
        System.out.println("Categoria: " + categoria());
    }
}