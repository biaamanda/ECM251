package br.maua;

// Exercícios 3, 4 e 5 (enunciados em ChecksumTest.java)

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Checksum {
    // 1 caractere = 1 byte ao ler e gravar arquivos (o checksum pode passar de 127)
    private static final Charset UM_BYTE = StandardCharsets.ISO_8859_1;

    // Polinômio reverso do CRC-32 padrão (o mesmo usado em ZIP, PNG e Ethernet)
    private static final long POLINOMIO_CRC32 = 0xEDB88320L;

    // ---------- Exercício 3: Soma e Complemento de 2 (slides 41-48 da Aula 18) ----------

    // 1. soma o código ASCII de cada caractere;
    // 2. descarta o bit excedente, mantendo só 8 bits (& 0xFF);
    // 3. inverte os bits e soma 1 (complemento de 2), de novo em 8 bits.
    public char calcularChecksum(char[] dados) {
        int soma = 0;
        for (char c : dados) {
            soma = (soma + (c & 0xFF)) & 0xFF;
        }
        return (char) ((~soma + 1) & 0xFF);
    }

    // ---------- Exercício 4: checksum de arquivo texto ----------

    // Lê os caracteres do arquivo de entrada e grava no de saída os mesmos
    // caracteres seguidos do checksum (como no slide 48: "Casa1" -> "Casa1W").
    public char calcularChecksumDoArquivoTexto(String arquivoEntrada, String arquivoSaida) throws IOException {
        String conteudo = lerArquivo(arquivoEntrada);
        char checksum = calcularChecksum(conteudo.toCharArray());
        gravarArquivo(arquivoSaida, conteudo + checksum);
        return checksum;
    }

    // ---------- Exercício 5: CRC-32 ----------

    // CRC-32 calculado bit a bit: para cada byte, faz XOR no registrador e,
    // 8 vezes, desloca para a direita aplicando o polinômio quando sai um bit 1.
    public long calcularCRC(char[] dados) {
        long crc = 0xFFFFFFFFL;
        for (char c : dados) {
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

    // Lê os caracteres do arquivo de entrada e grava no de saída os mesmos
    // caracteres seguidos do CRC-32 em hexadecimal (8 dígitos).
    public long calcularCRCDoArquivoTexto(String arquivoEntrada, String arquivoSaida) throws IOException {
        String conteudo = lerArquivo(arquivoEntrada);
        long crc = calcularCRC(conteudo.toCharArray());
        gravarArquivo(arquivoSaida, conteudo + String.format("%08X", crc));
        return crc;
    }

    // ---------- auxiliares ----------

    private String lerArquivo(String caminho) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminho)), UM_BYTE);
    }

    private void gravarArquivo(String caminho, String conteudo) throws IOException {
        Files.write(Paths.get(caminho), conteudo.getBytes(UM_BYTE));
    }
}
