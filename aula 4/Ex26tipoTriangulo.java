//Construir um algoritmo que leia três números que representam os lados de um triângulo e que imprima se o triângulo é equilátero (três lados iguais), isósceles (dois lados iguais) ou escaleno (três lados diferentes). Antes, não se esqueça de testar se os três lados realmente formam um triângulo. Informe o usuário caso não formem;

import java.util.Scanner;

public class Ex26tipoTriangulo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o primeiro lado do triângulo: ");
        int side1 = sc.nextInt();
        System.out.print("Digite o segundo lado do triângulo: ");
        int side2 = sc.nextInt();
        System.out.print("Digite o terceiro lado do triângulo: ");
        int side3 = sc.nextInt();

        if (side1 == side2 && side1 == side3) {
            System.out.println("O triângulo é equilátero.");
        } else if (side1 == side2 || side1 == side3 || side2 == side3) {
            System.out.println("O triângulo é isósceles.");
        } else {
            System.out.println("O triângulo é escaleno.");
        }
        sc.close();
    }
}
