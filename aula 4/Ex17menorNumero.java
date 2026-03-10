//Entrar com dois números e imprimir o menor número (suponha números diferentes)

import java.util.Scanner;
public class Ex17menorNumero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o primeiro número: ");
        int number1 = sc.nextInt();
        System.out.print("Digite o segundo número: "); 
        int number2 = sc.nextInt();

        if (number1 < number2){
            System.out.println("O menor número é: " + number1);
        } else {
            System.out.println("O menor número é: " + number2);
        }
        sc.close();
    }
}
