//Crie e teste, em Java, um algoritmo que leia, no formato real, o peso e a altura do usuário, e depois apresente a tela:
//“Seus dados digitados foram: <peso>kg e <altura>m”

import java.util.Scanner;

public class Ex05pesoAltura {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Qual seu peso? ");
        double weight = sc.nextDouble();
        System.out.print("Qual sua altura? ");
        double height = sc.nextDouble();

        System.out.print("Seus dados digitados foram: " + weight + "kg e " + height + "m");

        sc.close();
    }
}
