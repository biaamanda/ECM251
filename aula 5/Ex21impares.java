// Criar um algoritmo que leia um número que será o limite superior de um intervalo e imprimir todos os números ímpares menores do que esse número. Exemplo: limite 15, imprime 1, 3,5, 7, 9, 11, 13

import java.util.Scanner;

public class Ex21impares {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o limite superior: ");
        int upperLimit = sc.nextInt();

        for (int i = 1; i <= upperLimit; i++){
            if (i % 2 != 0) {
                System.out.print(i + " ");
            }
        }
        sc.close();
    }
}
