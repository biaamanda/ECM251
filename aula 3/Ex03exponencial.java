//Crie um algoritmo que leia um número inteiro e o eleve ao quadrado usando a classe Math, onde a base e o expoente são números reais

import java.util.Scanner;

public class Ex03exponencial {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um numero inteiro: ");
        int number = sc.nextInt();
        double result = Math.pow(number, 2);
        System.out.println("O numero " + number + " elevado ao quadrado é: " + result);

        sc.close();
    }
}
