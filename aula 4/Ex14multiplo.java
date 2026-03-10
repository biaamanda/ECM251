//Ler um número inteiro de 4 dígitos e imprimir se é ou não múltiplo de quatro o número formado pelos algarismos que estão nas casas das unidades de milhar e das centenas;

import java.util.Scanner;

public class Ex14multiplo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um número inteiro de 4 dígitos: ");
        int number = sc.nextInt();
        
        if (number < 1000 || number > 9999) {
            System.out.println("Número inválido.");
        } else {
            int thousandsDigit = number / 1000;
            int hundredsDigit = (number / 100) % 10;
            int combinedNumber = thousandsDigit * 10 + hundredsDigit;

            if (combinedNumber % 4 == 0) {
                System.out.println("O número formado é MULTIPLO de 4.");
            } else {
                System.out.println("O número formado NAO é múltiplo de 4.");
            }
        }
        sc.close();
    }
}
