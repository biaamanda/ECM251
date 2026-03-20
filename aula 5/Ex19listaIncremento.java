//Criar um algoritmo que leia um número que será o limite superior de um intervalo e o incremento. Imprimir todos os números do intervalo de 0 até esse número, de incremento em incremento. Ex.: limite 20, incremento 5; vai imprimir 0, 5, 10, 15, 20

import java.util.Scanner;

public class Ex19listaIncremento {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Digite o limite superior: ");
        int upperLimit = sc.nextInt();
        System.out.printf("Digite o incremento: ");
        int increment = sc.nextInt();

        for (int i = 0; i <= upperLimit; i+=increment){
            System.out.printf(i  + " ");
        }
        sc.close();
    }
}
