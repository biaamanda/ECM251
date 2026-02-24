//Crie e teste, em Java, um algoritmo que leia 4 (quatro) números inteiros, um a um, de um único dígito cada, representando, na ordem, milhar, centena, dezena e unidade. Use o tipo caractere, para garantir que não seja digitado mais de um número por vez. Após a concatenação desses números,armazene-os em uma variável do tipo String. Apresente em tela o valor final dessa String

import java.util.Scanner;

public class Ex07numeroString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String total = " ";
        
        System.out.print("Digite o milhar: ");
        char thousands = sc.next().charAt(0);
        total += thousands;

        System.out.print("Digite a centena: ");
        char hundred = sc.next().charAt(0);
        total += hundred;

        System.out.print("Digite a dezena: ");
        char ten = sc.next().charAt(0);
        total += ten;

        System.out.print("Digite a unidade: ");
        char unit = sc.next().charAt(0);
        total += unit;

        System.out.println("Sua string eh: " + total);

        sc.close();
    }
}