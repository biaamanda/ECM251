//Crie e teste, em Java, um algoritmo que leia 10 (dez) caracteres, um a um, e imprima, de uma só vez, a palavra formada pela união deles

import java.util.Scanner;

public class Ex06palavra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = "";
        
        for (int i = 0; i < 10; i++){
            char character = sc.next().charAt(0);
            word = word + character;
        }
        System.out.println(word);
        sc.close();
    }
}
