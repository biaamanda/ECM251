//Entrar com um único número inteiro, com 5 dígitos, e imprimir o algarismo correspondente à casa da dezena

import java.util.Scanner;

public class Ex07numerosDezenas {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um numero inteiro com 5 digitos: ");
        int number = sc.nextInt();

        System.out.println("O algarismo correspondente à casa da dezena é: " + (number / 10) % 10);

        sc.close();
    }
}
