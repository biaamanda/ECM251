package com.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Checksum {
    // Polinômio reverso do CRC-32 padrão (o mesmo usado em ZIP, PNG e Ethernet)
    private static final long POLINOMIO_CRC32 = 0xEDB88320L;

    // ---------- Soma e Complemento de 2 (ex03) ----------

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

    // Lê o arquivo de entrada, calcula o checksum e grava no arquivo de saída
    // os caracteres lidos seguidos do checksum (ex04).
    public int calcularChecksumDoArquivoTexto(String arquivoEntrada, String arquivoSaida) throws IOException {
        String conteudo = Files.readString(Path.of(arquivoEntrada), StandardCharsets.UTF_8);
        int checksum = calcularChecksum(conteudo.toCharArray());

        String saida = conteudo + System.lineSeparator() + "Checksum: " + checksum;
        Files.writeString(Path.of(arquivoSaida), saida, StandardCharsets.UTF_8);

        return checksum;
    }

    // ---------- CRC-32 (ex05) ----------

    // CRC-32 calculado bit a bit: para cada byte, faz XOR no registrador e,
    // 8 vezes, desloca para a direita aplicando o polinômio quando sai um bit 1.
    public long calcularCRC(char[] caracteres) {
        long crc = 0xFFFFFFFFL;

        for (char c : caracteres) {
            crc ^= (c & 0xFF);

            for (int i = 0; i < 8; i++) {
                if ((crc & 1L) != 0L) {
                    crc = (crc >>> 1) ^ POLINOMIO_CRC32;
                } else {
                    crc = crc >>> 1;
                }
            }
        }

        return (crc ^ 0xFFFFFFFFL) & 0xFFFFFFFFL;
    }

    // Lê o arquivo de entrada, calcula o CRC-32 e grava no arquivo de saída os
    // caracteres lidos seguidos do CRC em hexadecimal.
    public long calcularCRCDoArquivoTexto(String arquivoEntrada, String arquivoSaida) throws IOException {
        String conteudo = Files.readString(Path.of(arquivoEntrada), StandardCharsets.UTF_8);
        long crc = calcularCRC(conteudo.toCharArray());

        String saida = conteudo + System.lineSeparator() + "CRC-32: " + String.format("%08X", crc);
        Files.writeString(Path.of(arquivoSaida), saida, StandardCharsets.UTF_8);

        return crc;
    }
}
