//Entrar com um número e verificar se ele é um número primo;

import java.util.Scanner;

public class Ex12numeroPrimo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um numero: ");
        int number = sc.nextInt();

        int dividers = 0;

        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                dividers++;
            }
        }

        if (dividers == 2) {
            System.out.println("O numero é primo");
        } else {
            System.out.println("O numero nao é primo");
        }

        sc.close();
    }
}
