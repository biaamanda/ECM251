//Construir um algoritmo que indique se o número digitado está compreendido entre 20 e 90 ou não

import java.util.Scanner;

public class Ex05numeroCompreendido {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um numero: ");
        int number = sc.nextInt();

        if (number > 20 && number < 90) {
            System.out.println("O numero esta compreendido entre 20 e 90");
        } else {
            System.out.println("O numero NAO esta compreendido entre 20 e 90");
        }
        sc.close();
    }
}
