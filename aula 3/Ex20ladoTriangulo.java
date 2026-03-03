//Criar um programa capaz de calcular o 3º lado de um triângulo, digitados os outros dois lados e o ângulo entre eles esses dois lados.

import java.util.Scanner;

public class Ex20ladoTriangulo {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o valor do lado A: ");
        double a = sc.nextDouble();
        System.out.print("Digite o valor do lado B: ");
        double b = sc.nextDouble();
        System.out.print("Digite o valor do ângulo entre os lados A e B (em graus): ");
        double angleDegrees = sc.nextDouble();

        double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) - 2 * a * b * Math.cos(Math.toRadians(angleDegrees)));
        System.out.println("O valor do lado C é: " + c);

        sc.close();
    }
}
