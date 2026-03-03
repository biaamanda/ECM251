//Crie um algoritmo para calcular a área de um retângulo, com base nas medidas de sua base e de sua altura.

import java.util.Scanner;

public class Ex02area {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite a base do retangulo: ");
        double base = sc.nextDouble();
        System.out.print("Digite a altura do retangulo: ");
        double height = sc.nextDouble();
        double area = base * height;
        System.out.println("A área do retângulo e: " + area);

        sc.close();
    }
}