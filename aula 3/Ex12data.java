//Entre com uma data em uma variável do tipo inteiro no formato ddmmaa e imprimir dia, mês e ano separados.

import java.util.Scanner;

public class Ex12data {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite uma data no formato ddmmaa: ");
        int date = sc.nextInt();

        System.out.println("Dia: " + (date / 10000));
        System.out.println("Mês: " + ((date % 1000 / 100)));
        System.out.println("Ano: " + (date % 100));
        
        sc.close();
    }
}
