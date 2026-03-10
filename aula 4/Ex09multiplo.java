//Entrar com um número e imprimir uma das mensagens: é múltiplo de 3 ou não é;

import java.util.Scanner;

public class Ex09multiplo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int number = sc.nextInt();

        if (number % 3 == 0) {
            System.out.println("O número é MULTIPLO de 3");
        } else {
            System.out.println("O número NAO é múltiplo de 3");
        }
        sc.close();
    }
}
