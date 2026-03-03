//Crie um algoritmo que calcule o valor de uma dívida, submetida à juros compostos:
// ValorFinal = ValorInicial * (1 + J / 100) ^ N
// E seja capaz de responder, se você deve para o cartão de crédito R$ 100,00, a uma taxa de juros de 10%, quanto deverá ser pago depois de 8 meses.
// Onde:
// • J representa os juros (em %); e
// • N representa o número de meses.

import java.util.Scanner;

public class Ex06dividas {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);    

        System.out.print("Digite o valor inicial da divida: ");
        double inicialValue = sc.nextDouble();
        System.out.print("Digite a taxa de juros (em %): ");
        double interestRate = sc.nextDouble();
        System.out.print("Digite o numero de meses: ");
        int months = sc.nextInt();

        double finalValue = inicialValue * Math.pow(1 + interestRate / 100, months);
        System.out.println("Valor final da divida: " + finalValue);

        sc.close();
    }   
}