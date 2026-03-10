//Ler cinco números e identificar o maior e o menor de todos (NÃO suponha números diferentes)

import java.util.Scanner;

public class Ex24maiorMenor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o primeiro numero: ");
        int num1 = sc.nextInt();
        System.out.println("Digite o segundo numero: ");
        int num2 = sc.nextInt();
        System.out.println("Digite o terceiro numero: ");
        int num3 = sc.nextInt();
        System.out.println("Digite o quarto numero: ");
        int num4 = sc.nextInt();
        System.out.println("Digite o quinto numero: ");
        int num5 = sc.nextInt();

        int maior = Math.max(num1, Math.max(num2, Math.max(num3, Math.max(num4, num5))));
        int menor = Math.min(num1, Math.min(num2, Math.min(num3, Math.min(num4, num5))));

        System.out.println("O maior numero é: " + maior);
        System.out.println("O menor numero é: " + menor);

        sc.close();
    }
}
