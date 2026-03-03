//Crie um algoritmo para calcular o número de dias que você está vivo, com base em sua idade, que deverá ser digitada

import java.util.Scanner;

public class Ex01diasVivo {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite sua idade: ");
        int age = sc.nextInt();
        int daysAlive = age * 365;
        System.out.println("Você está vivo há " + daysAlive + " dias.");

        sc.close();
    }
}