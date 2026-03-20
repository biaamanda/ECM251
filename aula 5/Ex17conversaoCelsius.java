//Faça um algoritmo para imprimir na tela uma tabela de conversão de graus Celsius para graus Fahrenheit. Deseja-se que o mesmo solicite ao usuário o limite inferior, o superior e o incremento. Lembre-se que C = 5.(F-32)/9;

import java.util.Scanner;

public class Ex17conversaoCelsius {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o limite inferior: ");
        float lowerLimit = sc.nextFloat();

        System.out.println("Digite o limite superior: ");
        float higherLimit = sc.nextFloat();

        System.out.println("Digite o incremento: ");
        float increment = sc.nextFloat();

        for (float i = lowerLimit; i <= higherLimit; i+=increment){
            double Fahrenheit = (i*9/5) + 32;
            
            System.out.println(i + " C -> " + Fahrenheit + " F");
        }
        sc.close();
    }
}
