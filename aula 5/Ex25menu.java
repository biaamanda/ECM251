//Escrever um algoritmo que lê̂ 2 números reais. A seguir, é apresentado, para o usuário, o menu a seguir:
//“Operações Disponíveis:
//1. Adição;
//2. Subtração;
//3. Multiplicação;
//4. Divisão;
//9. Sair do Programa.
//Digite o número de ordem da opção desejada: “
//Se a opção for 1, o algoritmo deve somar os dois valores lidos; 
// se for 2, o algoritmo deve fazer o primeiro valor menos o segundo; 
// se for 3, o algoritmo deve multiplicar os valores lidos; 
// se for 4, o algoritmo deve dividir o primeiro pelo segundo valor lido, desde que este não seja zero (o algoritmo deve ter tratamento especial para este caso).
//O algoritmo deve escrever o resultado da operação escolhida. Se o usuário digitar 9, o algoritmo deve ser encerrado. Enquanto o valor da opção 9 não for digitado, o menu deve ser apresentado novamente.

import java.util.Scanner;

public class Ex25menu {

    public static void showMenuOptions() {
        System.out.println("Operações Disponíveis:");
        System.out.println("1. Adiçao;");
        System.out.println("2. Subtraçao;");
        System.out.println("3. Multiplicaçao;");
        System.out.println("4. Divisao;");
        System.out.println("9. Sair do Programa\n");
    }

    public static double sum(double firstNumber,double secondNumber){
        return firstNumber + secondNumber;
    }

    public static double subtraction(double firstNumber,double secondNumber){
        return firstNumber - secondNumber;
    }

    public static double multiply(double firstNumber,double secondNumber){
        return firstNumber * secondNumber;
    }

    public static double divide(double firstNumber,double secondNumber){
        return firstNumber / secondNumber;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int userOption = 0;

        while(true){

            System.out.println("Digite o primeiro numero: ");
            double  firstNumber = sc.nextInt();
            System.out.println("Digite o segundo numero: ");
            double secondNumber = sc.nextInt();

            showMenuOptions();
            System.out.print("Digite a opção desejada: ");
            userOption = sc.nextInt();
            
            switch (userOption) {

                case 1:
                    System.out.println("Resultado: " + sum(firstNumber, secondNumber));
                    break;
                case 2:
                    System.out.println("Resultado: " + subtraction(firstNumber, secondNumber));
                    break;
                case 3:
                    System.out.println("Resultado: " + multiply(firstNumber, secondNumber));
                    break;
                case 4: 
                    if (secondNumber == 0){
                        System.out.println("Erro na divisao");
                    } else {
                        System.out.println("Resultado: " + divide(firstNumber, secondNumber));
                    }
                    break;
                case 9:
                    System.out.println("Encerrando o programa...");
                    sc.close();
                    return;
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
            
        }
    }
}
