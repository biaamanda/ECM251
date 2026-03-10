// Crie um algoritmo que leia dois números e imprimir uma mensagem dizendo se são iguais ou diferentes;

import java.util.Scanner;

public class Ex16igualDiferente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o primeiro número: ");
        int number1 = sc.nextInt();
        System.out.print("Digite o segundo número: ");
        int number2 = sc.nextInt();

        if (number1 == number2) {
            System.out.println("Os números sao IGUAIS");
        } else {
            System.out.println("Os números sao DIFERENTES");
        }
        sc.close();
    }
}
