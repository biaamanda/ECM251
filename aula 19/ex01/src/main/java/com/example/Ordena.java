package com.example;

public class Ordena {

    // Ordena o vetor em ordem decrescente (bubble sort do slide 28, com a
    // comparação invertida: troca quando o anterior é MENOR que o atual)
    public void ordenaNumerosDecrescentes(int[] vetor) {
        for (int i = 1; i < vetor.length; i++) {
            for (int j = vetor.length - 1; j >= i; j--) {
                if (vetor[j - 1] < vetor[j]) {
                    int rascunho = vetor[j - 1];
                    vetor[j - 1] = vetor[j];
                    vetor[j] = rascunho;
                }
            }
        }
    }
}
