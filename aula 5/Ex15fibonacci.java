// Faça um algoritmo que peça para o usuário digitar um número e mostre na tela a sequência de Fibonacci de 1 até este número

import java.util.Scanner;

public class Ex15fibonacci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Digite um numero: ");
        int typedOption = sc.nextInt();
        int a = 1, b = 1;

        System.out.print("Sequencia Fibonacci: ");

        while (a <= typedOption) {
            System.out.print(a + " ");

            int next = a + b;
            a = b;
            b = next;
        }
        sc.close();
   }
}
