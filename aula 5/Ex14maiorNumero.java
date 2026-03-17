//Entrar com vários números e imprimir o maior. O algoritmo para quando se digita -9999

import java.util.Scanner;

public class Ex14maiorNumero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um numero: ");
        int number = sc.nextInt();

        int max = number;

        while(number != -9999){
            if (number > max){
                max = number;
            }

            System.out.println("Digite um numero: ");
            number = sc.nextInt();
        }
        System.out.println("O maior numero eh: " + max);

        sc.close();
    }
}
