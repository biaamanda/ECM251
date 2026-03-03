//Entrar com um ângulo em graus e imprimir seu seno, cosseno, tangente, secante, cossecante e cotangente.

import java.util.Scanner;

public class Ex08calculoAngulos {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um ângulo em graus: ");
        double degrees = sc.nextDouble();

        double radians = Math.toRadians(degrees);

        System.out.println("Seno: " + Math.sin(radians));
        System.out.println("Cosseno: " + Math.cos(radians));
        System.out.println("Tangente: " + Math.tan(radians));
        System.out.println("Secante: " + 1 / Math.cos(radians));
        System.out.println("Cossecante: " + 1 / Math.sin(radians));
        System.out.println("Cotangente: " + 1 / Math.tan(radians));

        sc.close();
    }
}