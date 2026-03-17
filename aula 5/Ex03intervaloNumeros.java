//Ler vários números e informar quantos números entre 100 e 200 foram digitados. Quando o valor 0 (zero) for lido o algoritmo deverá cessar sua execução

import java.util.Scanner;

public class Ex03intervaloNumeros {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = 0;

        System.out.println("Digite um numero: ");
        int number = sc.nextInt();
        
        while(number != 0){
            if (number > 100 && number < 200){
                count = count + 1;
            }

            System.out.println("Digite um numero: ");
            number = sc.nextInt();
        }

        System.out.println("Os numeros digitados entre 100 e 200 sao: " + count);
        sc.close();
    }
}
