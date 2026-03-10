//Entrar com dois números e imprimi-los em ordem crescente (suponha números diferentes);

import java.util.Scanner;

public class Ex18ordemCrescente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o primeiro numero: ");
        int num1 = sc.nextInt();
        System.out.println("Digite o segundo numero: ");
        int num2 = sc.nextInt();

        if (num1 < num2) {
            System.out.println(num1 + " " + num2);
        } else {
            System.out.println(num2 + " " + num1);
        }
        sc.close();
    }
}