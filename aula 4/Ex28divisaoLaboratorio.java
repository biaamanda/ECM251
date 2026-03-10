//Ajude uma escola a montar as divisões do laboratório de programação. Para isso, escreva um algoritmo que leia o nome do aluno e diga em qual divisão ele está respeitando a regra abaixo (dica: use o método charAt(posição):
// - alunos cujo nome começa com as letras de A a K estão na D1;
// - alunos cujo nome começa com as letras de L a N estão na D2;
// - alunos cujo nome começa com as letras de O a Z estão na D3

import java.util.Scanner;

public class Ex28divisaoLaboratorio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o nome do aluno: ");
        String name = sc.nextLine();
        char firstLetter = name.charAt(0);

        if (firstLetter >= 'A' && firstLetter <= 'K') {
            System.out.println(name + " está na D1");
        } else if (firstLetter >= 'L' && firstLetter <= 'N') {
            System.out.println(name + " está na D2");
        } else if (firstLetter >= 'O' && firstLetter <= 'Z') {
            System.out.println(name + " está na D3");
        } else {
            System.out.println("Nome inválido");
        }


        sc.close();
    }
}
