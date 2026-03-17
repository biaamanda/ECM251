//Entrar com vários números positivos e imprimir a média dos números digitados

import java.util.Scanner;

public class Ex02media {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite seus numeros: ");
        int number = sc.nextInt();

        int count = 0;
        int sum = 0;

        while (number >= 0){
            sum = sum + number;
            count = count + 1;

            System.out.println("Digite seus numeros: ");
            number = sc.nextInt();
        }

        System.out.println("Media: " + sum/count);

        sc.close();
    }
}