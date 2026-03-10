//Entrar com o ano de nascimento de uma pessoa e o ano atual. Imprimir a idade da pessoa. Não se esqueça de verificar se o ano de nascimento é um ano válido;

import java.util.Scanner;

public class Ex15idade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o ano de nascimento: ");
        int birthYear = sc.nextInt();

        System.out.print("Digite o ano atual: ");
        int currentYear = sc.nextInt();

        int age = currentYear - birthYear;

        if (birthYear > currentYear) {
            System.out.println("Ano de nascimento inválido.");
        } else {
            System.out.println("A idade da pessoa é: " + age + " anos.");
        }
        sc.close();
    }
}
