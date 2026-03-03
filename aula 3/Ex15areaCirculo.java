//Crie um algoritmo para calcular a área de um círculo, com base no seu raio (Área = PI*raio*raio, onde PI = 3.14159). Use variáveis reais

import java.util.Scanner;

public class Ex15areaCirculo {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o raio do circulo: ");
        double radians = sc.nextDouble();

        double area = 3.14159 * radians * radians;

        System.out.printf("A area do circulo eh: %.2f%n", area);

        sc.close();
    }
}