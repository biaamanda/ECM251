/**
 * Crie a classe TesteDisciplina com o método main, leia dos dados
 * necessários para criar uma disciplina, crie uma disciplina e
 * exiba a disciplina criada.
 */

import java.util.Scanner;

public class TestSubject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da disciplina: ");
        String name = scanner.nextLine();

        System.out.print("Digite o professor da disciplina: ");
        String professor = scanner.nextLine();

        System.out.print("Digite o semestre da disciplina: ");
        int semester = scanner.nextInt();

        System.out.print("A disciplina é ofertada? (true/false): ");
        boolean offered = scanner.nextBoolean();

        Subject subject = new Subject(name, professor, semester, offered);

        System.out.println("\n--- Disciplina Criada ---");
        System.out.println("Nome: " + subject.getName());
        System.out.println("Professor: " + subject.getProfessor());
        System.out.println("Semestre: " + subject.getSemester());
        System.out.println("Ofertada: " + subject.isOffered());

        scanner.close();
    }
}