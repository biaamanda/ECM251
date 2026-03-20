//Entrar com nomes enquanto forem diferentes de FIM e imprimir cada nome digitado

import java.util.Scanner;

public class Ex04nomes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Digite um nome: ");
        String name = sc.next();

        while (!name.equals("FIM")) {
            System.out.println("Nome digitado: " + name);
            
            System.out.println("Digite um nome: ");
            name = sc.next();
        }
        sc.close();
    }
}