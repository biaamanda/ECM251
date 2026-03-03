//Escrever um algoritmo que leia três números reais a, b e c, calcule e escreva o resultado da expressão:
//x = 2 * ( ( a – c ) / 8 ) – b * 5.

import java.util.Scanner;

public class Ex14calculoExpressao {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o valor de a: ");
        double a = sc.nextDouble();
        System.out.print("Digite o valor de b: ");
        double b = sc.nextDouble();
        System.out.print("Digite o valor de c: ");
        double c = sc.nextDouble();

        double x = 2 * ( ( a - c ) / 8 ) - b * 5;

        System.out.println("O resultado da expressao é: " + x);

        sc.close();
    }
}