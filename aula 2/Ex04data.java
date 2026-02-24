//Crie e teste, em Java, um algoritmo que leia 3 (três) números, separadamente, que representem uma data,respectivamente, dia, mês e ano, e depois apresente a tela:
//“A data digitada foi <DD>/<MM>/<AAAA>”

import java.util.Scanner;

public class Ex04data {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um dia: ");
        int day = sc.nextInt();
        System.out.print("Digite um mes: ");
        int month = sc.nextInt();
        System.out.print("Digite um ano: ");
        int year = sc.nextInt();
        System.out.print("A data digitada foi: " + day + "/" + month + "/" + year);

        sc.close();
    }
}
