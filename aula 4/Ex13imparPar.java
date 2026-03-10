//Ler um número inteiro de 3 dígitos e imprimir se o algarismo da casa das dezenas é par ou ímpar

import java.util.Scanner;

public class Ex13imparPar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número inteiro de 3 dígitos: ");
        int number = sc.nextInt();

        if (number < 100 || number > 999) {
            System.out.println("Número inválido! Digite um número de 3 dígitos.");
        } else {
            int tensDigit = (number / 10) % 10;

            if (tensDigit % 2 == 0) {
                System.out.println("O algarismo da casa das dezenas é PAR.");
            } else {
                System.out.println("O algarismo da casa das dezenas é IMPAR.");
            }
        }
        sc.close();
    }
}