//Crie um algoritmo capaz de ler dez palavras quaisquer, gravadas em um arquivo texto, e as apresente no console em ordem inversa do arquivo.

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Ex05palavrasGravadas {
    public static void main(String[] args){
        String[] words = new String[10];
        int count = 0;

        try {
            File file = new File("palavras.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine() && count < 10) {
                words[count] = sc.nextLine();
                count++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            return;
        }

        System.out.println("Palavras em ordem inversa:");
        for (int i = count - 1; i >= 0; i--) {
            System.out.println(words[i]);
        }
    }
}
