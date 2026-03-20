// Ler vários números até entrar o número -999. Para cada número, imprimir seus divisores;

import java.util.Scanner;

public class Ex09divisores {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um numero: ");
        int number = sc.nextInt();

        while (number != -999){

            System.out.print("Os divisores de " + number + " sao: ");

            for (int i = 1; i <= number; i++){
                if (number % i == 0) {
                    System.out.print(i + " ");
                }
            }

            System.out.println();

            System.out.println("Digite um numero: ");
            number = sc.nextInt();
        }
    sc.close();
    }
}
