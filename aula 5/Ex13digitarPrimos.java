//Escrever um algoritmo que receba vários números inteiros positivos e imprima a quantidade de números primos dentre os números que foram digitados. Parar quando for digitado um número não positivo;

import java.util.Scanner;

public class Ex13digitarPrimos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int primeNumbers = 0;

        System.out.println("Digite um numero: ");
        int number = sc.nextInt();

        while (number > 0){
            boolean primo = true;

            if (number == 1) {
                primo = false;
            } else {
                for (int i = 2; i <= number / 2; i++) {
                    if (number % i == 0) {
                        primo = false;
                        break;
                    }
                }
            }

            if (primo) {
                primeNumbers++;
            }

            System.out.println("Digite um numero: ");
            number = sc.nextInt();
        }

        System.out.println("Quantidade de numeros primos: " + primeNumbers);
        sc.close();
    }
}