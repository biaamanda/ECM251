//Entrar com um número e informar se ele é ou não divisível por 5;

import java.util.Scanner;  
public class Ex10divisivel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int number = sc.nextInt();

        if (number % 5 == 0) {
            System.out.println("O número é DIVISIVEL por 5");
        } else {
            System.out.println("O número NAO é divisível por 5");
        }
        sc.close();
    }
}