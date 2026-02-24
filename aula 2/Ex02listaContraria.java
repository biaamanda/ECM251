//Crie e teste, em Java, um algoritmo que leia 3 (três) palavras, uma a uma, separadamente, e as apresente e tela, na ordem contrária em que foram digitadas, numa única linha,separadas por espaços

import java.util.Scanner;

public class Ex02listaContraria {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Primeira palavra: ");
        String FirstName = sc.nextLine();
        System.out.print("Segunda palavra: ");
        String SecondName = sc.nextLine();
        System.out.print("Terceira palavra: ");
        String ThirdName = sc.nextLine();

        System.out.print(ThirdName + " " + SecondName + " " + FirstName);

        sc.close();
    }
}