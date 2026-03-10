//Entrar com um verbo no infinitivo e imprimir se o verbo é da 1ª conjugação (terminados em ar), da 2ª conjugação (terminados em er), da 3ª conjugação (terminados em ir), se não está no infinitivo (não termina em r) ou se provavelmente nem é verbo no infinitivo (termina em or ou em ur);

import java.util.Scanner;

public class Ex27tipoVerbo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um verbo no infinitivo: ");
        String verbo = sc.nextLine();

        if (verbo.endsWith("ar")) {
            System.out.println("O verbo é da 1ª conjugaçao");
        } else if (verbo.endsWith("er")) {
            System.out.println("O verbo é da 2ª conjugaçao");
        } else if (verbo.endsWith("ir")) {
            System.out.println("O verbo é da 3ª conjugaçao");
        } else if (!verbo.endsWith("r")) {
            System.out.println("O verbo não está no infinitivo");
        } else if (verbo.endsWith("or") || verbo.endsWith("ur")) {
            System.out.println("Provavelmente nem é verbo no infinitivo");
        }
        sc.close();
    }
}
