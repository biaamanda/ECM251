package com.example;

public class Checksum {

    // Soma e Complemento de 2 (slides 41-48 da Aula 18):
    // 1. soma o código ASCII de cada caractere (1 byte cada);
    // 2. descarta o bit excedente, mantendo só 8 bits (& 0xFF);
    // 3. inverte os bits e soma 1 (complemento de 2), de novo em 8 bits.
    public int calcularChecksum(char[] caracteres) {
        int soma = 0;

        for (char c : caracteres) {
            soma = (soma + (c & 0xFF)) & 0xFF;
        }

        return (~soma + 1) & 0xFF;
    }
}
