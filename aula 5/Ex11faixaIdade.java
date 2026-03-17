//Entrar com a idade de várias pessoas e imprimir: total de pessoas com menos de 21 anos e total de pessoas com mais de 50 anos. Parar quando for digitada uma idade fora da faixa 0-120 anos;

import java.util.Scanner;

public class Ex11faixaIdade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int under21 = 0;
        int over50 = 0;

        System.out.println("Digite sua idade: ");
        int age = sc.nextInt();

        while (age > 0 && age < 120){
            if (age < 21){
                under21++;
            }
            if (age > 50) {
                over50++;
            }

            System.out.println("Digite sua idade: ");
            age = sc.nextInt();
        }
        System.out.println("Total de pessoas menor de 21 anos: " + under21);
        System.out.println("Total de pessoas maiores de 50 anos: " + over50);

        sc.close();
    }
}
