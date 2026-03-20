//Crie um algoritmo que calcule o fatorial de um número.
//Exemplo: 0! = 1; 1! = 1; 2! = 1*2 = 2; 3! = 1*2*3 = 6; 4! = 1*2*3*4 = 24; 5! = 1*2*3*4*5 = 120;

import java.util.Scanner;

public class Ex18fatorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Digite um numero: ");
        int typedNumber = sc.nextInt();

        long factorial = 1;
        
        if (factorial == 0){
            factorial = 1;
        } else {
            for (int i = 1; i <= typedNumber; i++){
                factorial = factorial * i;
            }
        }
        System.out.printf("Fatorial e: " + factorial);

        sc.close();
    }
}
