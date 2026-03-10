//Construir um algoritmo que leia dois números e efetue a adição. Caso o valor somado seja menor ou igual a 20, este deverá ser apresentado subtraindo-se 5

import java.util.Scanner;

public class Ex02adicao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o primeiro numero: ");
        int num1 = sc.nextInt();
        System.out.println("Digite o segundo numero: ");
        int num2 = sc.nextInt();

        int sum = num1 + num2;

        if (sum <= 20)  {
            sum = sum - 5;
            System.out.println("A soma dos numeros e: " + sum);
        } else {
            System.out.println("A soma dos numeros e: " + sum);
        }
        sc.close();
    }
}
