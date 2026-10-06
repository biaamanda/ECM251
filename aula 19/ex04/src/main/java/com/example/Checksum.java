package com.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

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

    // Lê os caracteres do arquivo de entrada, calcula o checksum e grava no
    // arquivo de saída os caracteres lidos seguidos do checksum.
    public int calcularChecksumDoArquivoTexto(String arquivoEntrada, String arquivoSaida) throws IOException {
        String conteudo = Files.readString(Path.of(arquivoEntrada), StandardCharsets.UTF_8);
        int checksum = calcularChecksum(conteudo.toCharArray());

        String saida = conteudo + System.lineSeparator() + "Checksum: " + checksum;
        Files.writeString(Path.of(arquivoSaida), saida, StandardCharsets.UTF_8);

        return checksum;
    }
}
