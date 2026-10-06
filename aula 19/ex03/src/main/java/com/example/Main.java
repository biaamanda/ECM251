package com.example;

/*Utilizando TDD, refazer o Exercício 1, da página 49 do material
da Aula 18 da semana passada, utilizando o framework JUnit */

/*
 * Enunciado original (Aula 18, Exercício 1):
 * Baseado nos conceitos da programação OO, desenvolver, em Java, uma classe
 * denominada Checksum, que contenha, entre outras coisas, um método denominado
 * calcularChecksum(), recebendo um vetor de caracteres digitados pelo usuário
 * e retornando o cálculo do respectivo checksum, baseado no algoritmo da Soma
 * e Complemento de 2;
 * Desenvolver, baseado nos conceitos de Testes Unitários, uma classe de testes
 * unitários para a classe Checksum, capaz de realizar os testes unitários e
 * automatizados de todos os métodos da classe Checksum (exceto do construtor).
 */

// Os testes ficam em src/test/java/ChecksumTest.java (rodar pelo ▶ do VS Code).
// Ciclo seguido: o 1º teste foi o exemplo do slide ("Casa1" -> 0x57) com o
// método retornando 0 -> VERMELHO; depois a soma e o complemento de 2 -> VERDE;
// os demais casos (vazio, "A", "AB", soma + checksum == 0) vieram em seguida.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Checksum checksum = new Checksum();

        System.out.print("Digite os caracteres para calcular o checksum: ");
        char[] caracteres = teclado.nextLine().toCharArray();

        int resultado = checksum.calcularChecksum(caracteres);
        System.out.printf("Checksum: %d (0x%02X)%n", resultado, resultado);

        teclado.close();
    }
}
