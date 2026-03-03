//Crie um algoritmo que embaralhe mensagens fazendo o seguinte: leia três frases, separe cada uma delas ao meio. Então junte nesta ordem: primeira metade da segunda, segunda metade da terceira, segunda metade da segunda, primeira metade da primeira, primeira metade da terceira, segunda metade da primeira. Concatene então as três frases originais e imprima o resultado. Na linha de baixo, escreva a frase embaralhada e compare o resultado.

import java.util.Scanner;

public class Ex11embaralhaMensagem {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a primeira frase: ");
        String phrase1 = sc.nextLine();
        System.out.println("Digite a segunda frase: ");
        String phrase2 = sc.nextLine();
        System.out.println("Digite a terceira frase: ");
        String phrase3 = sc.nextLine();

        String firstHalf1 = phrase1.substring(0, phrase1.length() / 2);
        String secondHalf1 = phrase1.substring(phrase1.length() / 2);
    
        String firstHalf2 = phrase2.substring(0, phrase2.length() / 2);
        String secondHalf2 = phrase2.substring(phrase2.length() / 2);
    
        String firstHalf3 = phrase3.substring(0, phrase3.length() / 2);
        String secondHalf3 = phrase3.substring(phrase3.length() / 2);
    
        String jumbledMessage = firstHalf2 + secondHalf3 + secondHalf2 + firstHalf1 + firstHalf3 + secondHalf1;
    
        System.out.println("\nFrases originais:");
        System.out.println(phrase1);
        System.out.println(phrase2);
        System.out.println(phrase3);
    
        System.out.println("\nMensagem embaralhada:");
        System.out.println(jumbledMessage);

        sc.close();
    }
}
