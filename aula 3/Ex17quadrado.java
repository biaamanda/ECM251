//Entrar com um número e imprimir o número, seu quadrado e sua raiz quadrada

import java.util.Scanner;

public class Ex17quadrado {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número: ");
        double number = sc.nextDouble();

        System.out.println("A raiz do seu numero e: " + Math.sqrt(number));

        sc.close();
    }
}
