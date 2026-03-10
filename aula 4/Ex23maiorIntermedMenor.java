//Ler três números e armazená-los em três variáveis com os seguintes nomes, de acordo com seus valores relativos: maior, intermediário e menor (suponha números diferentes);

import java.util.Scanner;

public class Ex23maiorIntermedMenor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o primeiro numero: ");
        int num1 = sc.nextInt();
        System.out.println("Digite o segundo numero: ");
        int num2 = sc.nextInt();
        System.out.println("Digite o terceiro numero: ");
        int num3 = sc.nextInt();

        int maior = Math.max(num1, Math.max(num2, num3));
        int menor = Math.min(num1, Math.min(num2, num3));
        int intermediario = num1 + num2 + num3 - maior - menor;

        System.out.println("O maior numero é: " + maior);
        System.out.println("O intermediario numero é: " + intermediario);
        System.out.println("O menor numero é: " + menor);

        sc.close();
    }
}
