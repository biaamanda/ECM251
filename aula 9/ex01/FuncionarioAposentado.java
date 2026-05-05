package ex01;
public class FuncionarioAposentado extends Funcionario {
    private float salarioAposentadoria;

    public FuncionarioAposentado(String nome, int idade, float salario, String cargo, float salarioAposentadoria) {
        super(nome, idade, salario, cargo);
        this.salarioAposentadoria = salarioAposentadoria;
    }

    public float getSalarioAposentadoria() {
        return salarioAposentadoria;
    }

    public void setSalarioAposentadoria(float salarioAposentadoria) {
        this.salarioAposentadoria = salarioAposentadoria;
    }

    public void exibirDados() {
        super.exibirDados();
        System.out.println("Salário Aposentadoria: R$ " + salarioAposentadoria);
    }

}