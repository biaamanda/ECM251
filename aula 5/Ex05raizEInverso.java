//Ler vários números até entrar o número -999. Para cada número, imprimir sua raiz quadrada e seu inverso

import java.util.Scanner;

public class Ex05raizEInverso {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um numero: ");
        double number = sc.nextDouble();

        while(number != -999){
            double squareRoot = Math.sqrt(number);
            System.out.println("Raiz do numero: " + squareRoot);

            double inverse = Math.pow(number, 2);
            System.out.println("Numero elevado: " + inverse);

            System.out.println("Digite um numero");
            number = sc.nextDouble();
        }
        sc.close();
    }
}