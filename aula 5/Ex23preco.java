//Escrever um algoritmo que lê̂ repetidamente o valor do preço de uma mercadoria e a quantidade de itens comprados dessa mercadoria. Quando a quantidade lida for igual a zero, o algoritmo deve mostrar o total a ser pago. O algoritmo não deve computar valores negativos de preço ou de quantidade; neste caso, o algoritmo deve pedir que o usuário digite novamente o valor do preço ou da quantidade digitados indevidamente (sugestão: usar outro loop faça-enquanto para cada caso).
//OBS.: Considerar a quantidade de mercadorias compradas é desconhecida;

import java.util.Scanner;

public class Ex23preco {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double price;
        int quantity;
        double total = 0;

        do {
            do {
                System.out.print("Digite o preço da mercadoria: ");
                price = sc.nextDouble();

                if (price < 0) {
                    System.out.println("Preço inválido! Digite novamente.");
                }
            } while (price < 0);

            do {
                System.out.print("Digite a quantidade: ");
                quantity = sc.nextInt();

                if (quantity < 0) {
                    System.out.println("Quantidade inválida! Digite novamente.");
                }
            } while (quantity < 0);

            if (quantity == 0) {
                break;
            }

            total += price * quantity;

        } while (true);

        System.out.println("Total a pagar: " + total);
        sc.close();
    }
}