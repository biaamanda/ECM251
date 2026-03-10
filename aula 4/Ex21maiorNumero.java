//Ler três números e escrever o maior número (suponha números diferentes);

import java.util.Scanner;

public class Ex21maiorNumero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o primeiro numero: ");
        int num1 = sc.nextInt();
        System.out.println("Digite o segundo numero: ");
        int num2 = sc.nextInt();
        System.out.println("Digite o terceiro numero: ");
        int num3 = sc.nextInt();

        if (num1 > num2 && num1 > num3) {
            System.out.println("O maior numero é: " + num1);
        } else if (num2 > num1 && num2 > num3) {
            System.out.println("O maior numero é: " + num2);
        } else {
            System.out.println("O maior numero é: " + num3);
        }
        sc.close();
    }
}
