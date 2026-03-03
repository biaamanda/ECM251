//Entrar com um número e imprimir o seu logaritmo na base 10.

import java.util.Scanner;
public class Ex09logaritmo {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um número: ");
        double number = sc.nextDouble();
        
        double log10 = Math.log10(number);
        System.out.println("O logaritmo na base 10 de " + number + " é: " + log10);

        sc.close();
    }
}
