/*Pesquisar o método de checksum através do cálculo de CRC e
implementar o método calcularCRC(), adicionando-o à classe
Checksum do exercício anterior e executando todas as
atividades solicitadas nos exercícios 1 e 2 */

// calcularCRC() e calcularCRCDoArquivoTexto() foram acrescentados a
// Checksum.java (algoritmo CRC-32, calculado bit a bit) e os testes
// unitarios continuam em ChecksumTest.java. Este arquivo repete, com CRC,
// as mesmas atividades dos exercicios 1 (teclado) e 2 (arquivo texto).

import java.io.IOException;
import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);
        Checksum checksum = new Checksum();

        // ----- Atividade do exercicio 1: le o texto via teclado -----
        System.out.print("Digite o texto para calcular o checksum e o CRC: ");
        char[] caracteres = teclado.nextLine().toCharArray();

        int chkTeclado = checksum.calcularChecksum(caracteres);
        long crcTeclado = checksum.calcularCRC(caracteres);
        System.out.println("Checksum (decimal): " + chkTeclado);
        System.out.println("CRC-32 (hexadecimal): " + Long.toHexString(crcTeclado).toUpperCase());

        // ----- Atividade do exercicio 2: le/grava em arquivos texto -----
        System.out.print("Nome do arquivo texto de entrada: ");
        String arquivoEntrada = teclado.nextLine().trim();

        System.out.print("Nome do arquivo texto de saida do checksum: ");
        String arquivoSaidaChecksum = teclado.nextLine().trim();

        System.out.print("Nome do arquivo texto de saida do CRC: ");
        String arquivoSaidaCrc = teclado.nextLine().trim();

        int chkArquivo = checksum.calcularChecksumDoArquivoTexto(arquivoEntrada, arquivoSaidaChecksum);
        long crcArquivo = checksum.calcularCRCDoArquivoTexto(arquivoEntrada, arquivoSaidaCrc);

        System.out.println("Checksum do arquivo: " + chkArquivo + " (gravado em " + arquivoSaidaChecksum + ")");
        System.out.println("CRC-32 do arquivo: " + Long.toHexString(crcArquivo).toUpperCase() + " (gravado em " + arquivoSaidaCrc + ")");

        teclado.close();
    }
}