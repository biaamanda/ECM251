//Criar um algoritmo que deixe entrar com dois números e imprimir o quadrado do menor número e a raiz quadrada do maior número, se for possível (suponha números diferentes)

import java.util.Scanner;   

public class Ex20quadradoERaiz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o primeiro numero: ");
        double number1 = sc.nextDouble();
        System.out.println("Digite o segundo numero: ");
        double number2 = sc.nextDouble();

        if (number1 > number2)  {
            double square = Math.pow(number2, 2);
            System.out.println("O quadrado do menor numero e: " + square);
            double squareRoot = Math.sqrt(number1);
            System.out.println("A raiz quadrada do maior numero e: " + squareRoot);
        } else {
            double square = Math.pow(number1, 2);
            System.out.println("O quadrado do menor numero e: " + square);
            double squareRoot = Math.sqrt(number2);
            System.out.println("A raiz quadrada do maior numero e: " + squareRoot);
        }
            
        sc.close();
    }
}
