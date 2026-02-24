//Crie e teste, em Java, um algoritmo que leia 1 (um) número inteiro, armazene-o em uma variável do tipo inteiro, e depois apresente em tela:
//“O valor do número digitado é: <número digitado>”

import java.util.Scanner;

public class Ex03numero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite numero: ");
        int number = sc.nextInt();
    
        System.out.print("O valor do numero digitado eh: " + number);

        sc.close();
    }
}
