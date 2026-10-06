package com.example;

/*Utilizando TDD, refazer o Exercício 3 – Desafio, da página 51 do
material da Aula 18 da semana passada, utilizando o framework
JUnit. */

/*
 * Enunciado original (Aula 18, Exercício 3 - Desafio):
 * Pesquisar o método de checksum através do cálculo de CRC e implementar o
 * método calcularCRC(), adicionando-o à classe Checksum do exercício anterior
 * e executando todas as atividades solicitadas nos exercícios 1 e 2.
 */

// Os testes ficam em src/test/java/ChecksumTest.java (rodar pelo ▶ do VS Code).
// Os testes do ex03 e ex04 continuam lá. Os de CRC foram escritos primeiro, com
// o valor de referência "123456789" -> 0xCBF43926 -> VERMELHO; depois o CRC-32
// bit a bit -> VERDE; por último o teste que compara com java.util.zip.CRC32.

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);
        Checksum checksum = new Checksum();

        // atividade do exercício 1: via teclado
        System.out.print("Digite o texto: ");
        String texto = teclado.nextLine();
        char[] caracteres = texto.toCharArray();

        System.out.printf("Checksum: %d (0x%02X)%n", checksum.calcularChecksum(caracteres), checksum.calcularChecksum(caracteres));
        System.out.printf("CRC-32  : %08X%n", checksum.calcularCRC(caracteres));

        // atividade do exercício 2: via arquivo
        Files.writeString(Path.of("entrada.txt"), texto, StandardCharsets.UTF_8);
        checksum.calcularChecksumDoArquivoTexto("entrada.txt", "saida_checksum.txt");
        checksum.calcularCRCDoArquivoTexto("entrada.txt", "saida_crc.txt");
        System.out.println("Gravados saida_checksum.txt e saida_crc.txt em " + Path.of("").toAbsolutePath());

        teclado.close();
    }
}
