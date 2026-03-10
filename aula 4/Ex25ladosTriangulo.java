// Construir um algoritmo que leia três números e imprima se eles podem ou não ser lados de um triângulo. Lembrando que, para ser um triângulo, a soma de dois lados quaisquer deve ser sempre maior que o lado que ficou de fora da soma

import java.util.Scanner;

public class Ex25ladosTriangulo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o primeiro lado do triângulo: ");
        int side1 = sc.nextInt();
        System.out.print("Digite o segundo lado do triângulo: ");
        int side2 = sc.nextInt();
        System.out.print("Digite o terceiro lado do triângulo: ");
        int side3 = sc.nextInt();

        if (side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1) {
            System.out.println("Os lados formam um triângulo.");
        } else {
            System.out.println("Os lados NAO formam um triângulo.");
        }
        sc.close();
    }
}