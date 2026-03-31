/*Crie a classe TesteAtribuicao com o método main() que instancia um Professor, uma Disciplina e uma Atribuicao. Imprima dos dados da Atribuicao */

import java.util.Scanner;

public class TesteAtribuicao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Cadastro de Professor ---");

        System.out.println("Nome do professor: ");
        String professorName = scanner.nextLine();
        System.out.println("Idade do professor: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite uma disciplina: ");
        String disciplineName = scanner.nextLine();
        System.out.println("A disciplina é prática? (true/false): ");
        boolean practice = scanner.nextBoolean();

        Professor professor = new Professor(professorName, age);
        Disciplina disciplina = new Disciplina(disciplineName, practice);

        Atribuicao atribuicao = new Atribuicao(professor, disciplina);
        
        System.out.println(atribuicao.getDados());

        scanner.close();
    }
}