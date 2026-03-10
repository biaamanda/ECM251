//Entrar com um número e informar se ele é divisível por 3 e por 7;

import java.util.Scanner;   

public class Ex11divisivelDoisNumeros {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int number = sc.nextInt();

        if (number % 3 == 0 && number % 7 == 0) {
            System.out.println("O número é DIVISIVEL por 3 e por 7");
        } else {
            System.out.println("O número NAO é divisível por 3 e por 7");
        }
        sc.close();
    }
}
