//Criar um algoritmo que leia um número que servirá para controlar os números pares que serão impressos a partir de 2
//Exemplo: quantos 4, imprime 2, 4, 6, 8;

import java.util.Scanner;

public class Ex22controleDePares {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um numero: ");
        int typedNumber = sc.nextInt();

        for (int i = 2; i <= typedNumber; i+=2){
            System.out.print(i + " ");
        }
        sc.close();
    }
}
