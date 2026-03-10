// Entrar com um número e imprimir uma das mensagens: maior do que 20, igual a 20 ou menor do que 20;

import java.util.Scanner;

public class Ex06comparar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int number = sc.nextInt();

        if (number > 20) {
            System.out.println("O número é MAIOR do que 20");
        } else if (number == 20) {
            System.out.println("O número é IGUAL a 20");
        } else {
            System.out.println("O número é MENOR do que 20");
        }
        sc.close();
    }
}