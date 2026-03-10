//Ler três números e armazenar o maior número na variável de nome maior (suponha números diferentes);

import java.util.Scanner;   

public class Ex22variavelMaior {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int maior;

        System.out.println("Digite o primeiro numero: ");
        int num1 = sc.nextInt();
        System.out.println("Digite o segundo numero: ");
        int num2 = sc.nextInt();
        System.out.println("Digite o terceiro numero: ");
        int num3 = sc.nextInt();

        // maior = Math.max(num1, Math.max(num2, num3));

        if (num1 > num2 && num1 > num3) {
            maior = num1;
            System.out.println("O maior numero é: " + maior);
            sc.close();
            return;
        }
        
        if (num2 > num1 && num2 > num3) {
            maior = num2;
            System.out.println("O maior numero é: " + maior);
            sc.close();
            return;
        }

        maior = num3;
        System.out.println("O maior numero é: " + maior);

        sc.close();
        return;
    
    }
}
