//Entrar com um número e imprimir a raiz quadrada do número caso ele seja positivo e o quadrado do número caso seja negativo;

import java.util.Scanner;

public class Ex03raizQuadrada {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um numero: ");
        double number = sc.nextDouble();

        if (number >= 0)  {
            double squareRoot = Math.sqrt(number);
            System.out.println("A raiz quadrada do numero e: " + squareRoot);
        } else {
            double square = Math.pow(number, 2);
            System.out.println("O quadrado do numero e: " + square);
        }
        sc.close();
    }
}
