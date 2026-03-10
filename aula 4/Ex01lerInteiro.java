//Construir um algoritmo que leia dois valores numéricos inteiros e efetue a adição deles; caso o resultado seja maior que 10, apresentá-lo

import java.util.Scanner;

public class Ex01lerInteiro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o primeiro numero: ");
        int num1 = sc.nextInt();
        System.out.println("Digite o segundo numero: ");
        int num2 = sc.nextInt();

        int sum = num1 + num2;

        if (sum > 10)  {
            System.out.println("A soma dos numeros e: " + sum);
        }
        sc.close();
    }
}