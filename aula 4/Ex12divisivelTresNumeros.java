//Entrar com um número e informar se ele é divisível por 10, por 5, por 2 ou se não é divisível por nenhum destes;

import java.util.Scanner;

public class Ex12divisivelTresNumeros {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int number = sc.nextInt();

        if (number % 10 == 0) {
            System.out.println("O número é DIVISIVEL por 10");
        } else if (number % 5 == 0) {
            System.out.println("O número é DIVISIVEL por 5");
        } else if (number % 2 == 0) {
            System.out.println("O número é DIVISIVEL por 2");
        } else {
            System.out.println("O número NAO é divisível por 10, por 5 ou por 2");
        }
        sc.close();
    }
}
