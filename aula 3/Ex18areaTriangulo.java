// Criar um algoritmo que calcule e imprime a área de um triângulo

import java.util.Scanner;

public class Ex18areaTriangulo {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite a base do triângulo: ");
        double base = sc.nextDouble();
        System.out.print("Digite a altura do triângulo: ");
        double height = sc.nextDouble();
        double area = (base * height) / 2;
        System.out.println("A área do triângulo é: " + area);

        sc.close();
    }
}
