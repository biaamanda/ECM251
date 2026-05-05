package ex01;

public class Teste {
    public static void main(String[] args) {
        Funcionario f1 = new Funcionario("Carlos", 35, 4500, "Analista");

        FuncionarioAposentado fa1 = new FuncionarioAposentado(
                "Maria", 65, 7000, "Gerente", 3500);

        System.out.println("=== FUNCIONÁRIO ===");
        f1.exibirDados();

        System.out.println("\n=== FUNCIONARIO APOSENTADO ===");
        fa1.exibirDados();
    }
}