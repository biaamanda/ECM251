//Crie um algoritmo capaz de ler três Strings quaisquer, digitadas por meio do console, e apresente a soma do comprimento destas Strings

import java.util.Scanner;

public class Ex04strings {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite a primeira palavra: ");
        String firstString = sc.nextLine();
        System.out.print("Digite a segunda palavra: ");
        String secondString = sc.nextLine();
        System.out.print("Digite a terceira palavra: ");
        String thirdString = sc.nextLine();
        int totalLength = firstString.length() + secondString.length() + thirdString.length();
        System.out.println("A soma do comprimento das três palavras é: " + totalLength);

        sc.close();
    }   
}