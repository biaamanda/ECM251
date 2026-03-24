/*Crie a classe TesteTurma com o método main. De modo
análogo ao exemplo, peça para o usuário entrar com os
valores necessários para criar uma turma, instancie um objeto
Turma e depois exiba os dados da turma criada. Depois, peça
para o usuário uma nova quantidade de alunos, altere o valor
do atributo e exiba os dados novamente; */

import java.util.Scanner;

public class ex02testeTurma {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um nome da turma: ");
        String name = sc.nextLine();

        System.out.println("Digite o curso: ");
        String course = sc.nextLine();

        System.out.println("Digite a quantidade de alunos: ");
        int studentCount = sc.nextInt();

        System.out.println("Digite a serie: ");
        int year = sc.nextInt();

        Turma testeClass = new Turma(name, course, studentCount, year);

        System.out.println("\n--- Turma Criada ---");
        System.out.println("Nome: " + testeClass.getName());
        System.out.println("Curso: " + testeClass.getCourse());
        System.out.println("Quantidade de alunos: " + testeClass.getStudentCount());

        System.out.print("\nDigite uma nova quantidade de alunos: ");
        int newStudentCount = sc.nextInt();
        testeClass.setStudentCount(newStudentCount);

        System.out.println("\n--- Turma Atualizada ---");
        System.out.println("Nome: " + testeClass.getName());
        System.out.println("Curso: " + testeClass.getCourse());
        System.out.println("Quantidade de alunos: " + testeClass.getStudentCount());

        sc.close();
    }
}
