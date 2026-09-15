import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

// Classe "Checksum.java" -- Aula 18, Exercicios 1, 2 e 3
//
// Exercicio 1: calcularChecksum() recebe um vetor de caracteres e calcula o
//              checksum atraves do algoritmo da Soma e Complemento de 2:
//              soma-se (modulo 256) o valor de cada caractere e, ao final,
//              calcula-se o complemento de 2 dessa soma. Assim, somando o
//              checksum aos dados originais o resultado (modulo 256) e zero.
//
// Exercicio 2: calcularChecksumDoArquivoTexto() le os caracteres de um
//              arquivo texto de entrada, calcula o checksum da mesma forma
//              do exercicio 1 e grava, em um arquivo texto de saida, os
//              caracteres lidos seguidos do valor do checksum encontrado.
//
// Exercicio 3: calcularCRC() e calcularCRCDoArquivoTexto() repetem as
//              mesmas ideias dos exercicios 1 e 2, mas calculando o checksum
//              atraves do algoritmo de CRC-32 (padrao usado em ZIP, PNG e
//              Ethernet), implementado "bit a bit" a partir do polinomio
//              reverso 0xEDB88320.
public class Checksum {
    // Polinomio reverso do CRC-32 padrao (IEEE 802.3 / ZIP / PNG)
    private static final long POLINOMIO_CRC32 = 0xEDB88320L;

    public Checksum() {
        // construtor sem estado proprio -- nao faz parte dos testes unitarios
    }

    // ---------- Exercicio 1 ----------

    // Calcula o checksum de um vetor de caracteres pelo algoritmo da
    // Soma e Complemento de 2, considerando cada caractere como 1 byte.
    public int calcularChecksum(char[] caracteres) {
        int soma = 0;

        for (char c : caracteres) {
            soma = (soma + (c & 0xFF)) & 0xFF; // soma modulo 256 (1 byte)
        }

        // complemento de 2 da soma: inverte os bits e soma 1
        return (~soma + 1) & 0xFF;
    }

    // ---------- Exercicio 2 ----------

    // Le os caracteres do arquivo de entrada, calcula o checksum (exercicio 1)
    // e grava, no arquivo de saida, os caracteres lidos seguidos do checksum.
    public int calcularChecksumDoArquivoTexto(String arquivoEntrada, String arquivoSaida) throws IOException {
        String conteudo = Files.readString(Path.of(arquivoEntrada), StandardCharsets.UTF_8);
        int checksum = calcularChecksum(conteudo.toCharArray());

        String saida = conteudo + System.lineSeparator() + "Checksum: " + checksum;
        Files.writeString(Path.of(arquivoSaida), saida, StandardCharsets.UTF_8);

        return checksum;
    }

    // ---------- Exercicio 3 ----------

    // Calcula o CRC-32 de um vetor de caracteres (algoritmo bit a bit,
    // usando o polinomio reverso 0xEDB88320, sem depender de java.util.zip).
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

    // Le os caracteres do arquivo de entrada, calcula o CRC-32 (acima) e
    // grava, no arquivo de saida, os caracteres lidos seguidos do CRC (hexadecimal).
    public long calcularCRCDoArquivoTexto(String arquivoEntrada, String arquivoSaida) throws IOException {
        String conteudo = Files.readString(Path.of(arquivoEntrada), StandardCharsets.UTF_8);
        long crc = calcularCRC(conteudo.toCharArray());

        String saida = conteudo + System.lineSeparator() + "CRC-32: " + Long.toHexString(crc).toUpperCase();
        Files.writeString(Path.of(arquivoSaida), saida, StandardCharsets.UTF_8);

        return crc;
    }
}
