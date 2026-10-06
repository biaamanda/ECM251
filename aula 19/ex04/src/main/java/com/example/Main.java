package com.example;

/*Utilizando TDD, refazer o Exercício 2, da página 50 do material
da Aula 18 da semana passada, utilizando o framework JUnit */

/*
 * Enunciado original (Aula 18, Exercício 2):
 * Baseado na solução do exercício 1, anterior, acrescentar à classe Checksum o
 * método calcularChecksumDoArquivoTexto(), capaz de ler os caracteres de um
 * arquivo texto e executar o cálculo do checksum, baseado no algoritmo da Soma
 * e Complemento de 2, da mesma forma como feito no exercício anterior, quando
 * lia os caracteres via teclado;
 * Após o cálculo do checksum, gravar o valor encontrado ao final de um outro
 * arquivo texto, logo após os caracteres fornecidos;
 * Desenvolver, baseado nos conceitos de Testes Unitários, uma classe de testes
 * unitários para a classe Checksum, capaz de realizar os testes unitários e
 * automatizados de todos os métodos da classe Checksum (exceto do construtor).
 */

// Os testes ficam em src/test/java/ChecksumTest.java (rodar pelo ▶ do VS Code).
// Os testes do ex03 continuam lá (todos devem passar a cada mudança); os novos
// testes de arquivo foram escritos antes do método existir -> VERMELHO
// (nem compilava), depois o método foi implementado -> VERDE.

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);
        Checksum checksum = new Checksum();

        System.out.print("Digite o texto que será gravado em entrada.txt: ");
        Files.writeString(Path.of("entrada.txt"), teclado.nextLine(), StandardCharsets.UTF_8);

        int resultado = checksum.calcularChecksumDoArquivoTexto("entrada.txt", "saida.txt");
        System.out.printf("Checksum: %d (0x%02X), gravado em %s%n",
                resultado, resultado, Path.of("saida.txt").toAbsolutePath());

        teclado.close();
    }
}
