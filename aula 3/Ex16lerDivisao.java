// Ler dois números inteiros e imprimir dividendo, divisor, quociente e resto

import java.util.Scanner;

public class Ex16lerDivisao {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um numero: ");
        double dividend = sc.nextDouble();
        System.out.print("Digite outro numero: ");
        double divisor = sc.nextDouble();

        System.out.println("Dividendo: " + dividend);
        System.out.println("Divisor: " + divisor);
        System.out.println("Quociente: " + (dividend/divisor));
        System.out.println("Resto: " + (dividend%divisor));
        sc.close();
    }
}
