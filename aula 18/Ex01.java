/*Baseado nos conceitos da programação OO, desenvolver, em
Java, uma classe denominada Checksum, que contenha, entre
outras coisas, um método denominado calcularChecksum(),
recebendo um vetor de caracteres digitados pelo usuário e
retornando o cálculo do respectivo checksum, baseado no
algoritmo da Soma e Complemento de 2;
• Desenvolver, baseado nos conceitos de Testes Unitários, uma
classe de testes unitários para a classe Checksum, capaz de
realizar os testes unitários e automatizados de todos os métodos
da classe Checksum (exceto do construtor) */

import java.util.Scanner;

public class Ex01 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Checksum checksum = new Checksum();

        System.out.print("Digite o texto para calcular o checksum: ");
        char[] caracteres = teclado.nextLine().toCharArray();

        int resultado = checksum.calcularChecksum(caracteres);
        System.out.println("Checksum (decimal): " + resultado);
        System.out.println("Checksum (hexadecimal): " + Integer.toHexString(resultado).toUpperCase());

        teclado.close();
    }
}