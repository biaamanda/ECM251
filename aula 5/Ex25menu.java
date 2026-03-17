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
        System.out.println("1. Adição;");
        System.out.println("2. Subtração;");
        System.out.println("3. Multiplicação;");
        System.out.println("4. Divisão;");
    }

    public static void sum(){
        
    }

    public static void subtraction(){

    }

    public static void multiply(){

    }

    public static void divide(){

    }
    public static void main(String[] args) {
        while(true){
            Scanner sc = new Scanner(System.in);

            int userOption = 0;

            
            userOption = sc.nextInt();

            switch (userOption) {
                case 1:
                    // adicao
                    break;
                case 2:
                    // subtracao
                    break;
                case 3:
                    //multiplicacao
                    break;
                case 4: 
                    //divisao
                    break;
                case 9:
                    return;
                default:
                    break;
            }
            
        }
    }
}
