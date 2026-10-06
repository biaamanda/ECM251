package com.example;

/*Utilizando TDD, refazer o Exemplo, da página 19 deste material
(Aula 19), utilizando o framework JUnit e alterando a ordenação
dos números para decrescente */

// Os testes ficam em src/test/java/OrdenaTest.java (rodar pelo ▶ do VS Code).
// Ciclo seguido: 1) teste de 2 números com o método vazio -> VERMELHO;
// 2) troca simples de vetor[0] e vetor[1] -> VERDE; 3) refatorado para o
// bubble sort de N números -> todos os testes VERDES de novo.

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] numeros = {3, 10, -2, 7, 7, 0};

        System.out.println("Antes : " + Arrays.toString(numeros));
        new Ordena().ordenaNumerosDecrescentes(numeros);
        System.out.println("Depois: " + Arrays.toString(numeros));
    }
}
