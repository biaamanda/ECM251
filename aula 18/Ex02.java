/*Baseado na solução do exercício 1, anterior, acrescentar à classe
Checksum o método calcularChecksumDoArquivoTexto(), capaz
de ler os caracteres de um arquivo texto e executar o cálculo do
checksum, baseado no algoritmo da Soma e Complemento de 2,
da mesma forma como feito no exercício anterior, quando lia os
caracteres via teclado;
• Após o cálculo do checksum, gravar o valor encontrado ao final
de um outro arquivo texto, logo após os caracteres fornecidos;
• Desenvolver, baseado nos conceitos de Testes Unitários, uma
classe de testes unitários para a classe Checksum, capaz de
realizar os testes unitários e automatizados de todos os métodos
da classe Checksum (exceto do construtor). */

// O metodo calcularChecksumDoArquivoTexto() foi acrescentado a Checksum.java
// e os testes unitarios continuam em ChecksumTest.java (agora cobrindo
// tambem este metodo). Este arquivo e o programa de demonstracao via arquivos.

import java.io.IOException;
import java.util.Scanner;

public class Ex02 {
    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);
        Checksum checksum = new Checksum();

        System.out.print("Nome do arquivo texto de entrada: ");
        String arquivoEntrada = teclado.nextLine().trim();

        System.out.print("Nome do arquivo texto de saida: ");
        String arquivoSaida = teclado.nextLine().trim();

        int resultado = checksum.calcularChecksumDoArquivoTexto(arquivoEntrada, arquivoSaida);
        System.out.println("Checksum calculado: " + resultado);
        System.out.println("Caracteres lidos de " + arquivoEntrada + " e checksum gravado ao final de " + arquivoSaida);

        teclado.close();
    }
}