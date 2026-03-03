//Criar um algoritmo que calcule e imprime a área de um losango.

import java.util.Scanner;

public class Ex19areaLosango {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Digite a diagonal maior do losango: ");
        double D = sc.nextDouble();
        System.out.print("Digite a diagonal menor do losango: ");   
        double d = sc.nextDouble();

        System.out.println("A área do losango é: " + (D * d) / 2);

        sc.close();
    }
}
