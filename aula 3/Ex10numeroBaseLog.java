//Entrar com um número e a base em que se deseja calcular o logaritmo desse número. Após isto, calcular tal logaritmo e imprimir o resultado.

import java.util.Scanner;

public class Ex10numeroBaseLog {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número: ");
        double number = sc.nextDouble();

        System.out.print("Digite uma base: ");
        double base = sc.nextDouble();

        System.out.println("O logaritmo de " + number + " na base " + base + " é: " + (Math.log(number) / Math.log(base)));

        sc.close();
    }
}
