import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

// Classe "ChecksumTest.java" -- Aula 18, Exercicios 1, 2 e 3
//
// Testes unitarios e automatizados de todos os metodos da classe Checksum
// (exceto o construtor). Nao depende de bibliotecas externas (JUnit) -- os
// casos de teste rodam sozinhos (sem entrada do usuario) e cada um imprime
// "OK" ou "FALHOU", com um resumo ao final.
public class ChecksumTest {
    private static int totalTestes = 0;
    private static int testesOk = 0;

    public static void main(String[] args) throws IOException {
        testarCalcularChecksum();
        testarCalcularChecksumDoArquivoTexto();
        testarCalcularCRC();
        testarCalcularCRCDoArquivoTexto();

        System.out.printf("%n%d de %d testes passaram.%n", testesOk, totalTestes);
    }

    // ----- Exercicio 1: calcularChecksum(char[]) -----
    private static void testarCalcularChecksum() {
        Checksum checksum = new Checksum();

        // vetor vazio: soma = 0, complemento de 2 de 0 e 0
        verificar("calcularChecksum(\"\")", 0, checksum.calcularChecksum("".toCharArray()));

        // "A" = 65 -> soma = 65 -> checksum = (256 - 65) & 0xFF = 191
        verificar("calcularChecksum(\"A\")", 191, checksum.calcularChecksum("A".toCharArray()));

        // "AB" = 65 + 66 = 131 -> checksum = (256 - 131) & 0xFF = 125
        verificar("calcularChecksum(\"AB\")", 125, checksum.calcularChecksum("AB".toCharArray()));

        // soma + checksum deve sempre "fechar" em zero, modulo 256
        char[] dados = "Soma e Complemento de 2".toCharArray();
        int soma = 0;
        for (char c : dados) {
            soma = (soma + (c & 0xFF)) & 0xFF;
        }
        int chk = checksum.calcularChecksum(dados);
        verificar("soma + checksum == 0 (mod 256)", 0, (soma + chk) & 0xFF);
    }

    // ----- Exercicio 2: calcularChecksumDoArquivoTexto(String, String) -----
    private static void testarCalcularChecksumDoArquivoTexto() throws IOException {
        Checksum checksum = new Checksum();
        String conteudo = "Teste de checksum em arquivo";

        File entrada = File.createTempFile("checksum_entrada", ".txt");
        File saida = File.createTempFile("checksum_saida", ".txt");
        entrada.deleteOnExit();
        saida.deleteOnExit();

        try {
            Files.writeString(entrada.toPath(), conteudo, StandardCharsets.UTF_8);

            int esperado = checksum.calcularChecksum(conteudo.toCharArray());
            int obtido = checksum.calcularChecksumDoArquivoTexto(entrada.getAbsolutePath(), saida.getAbsolutePath());
            verificar("calcularChecksumDoArquivoTexto() retorna o mesmo valor de calcularChecksum()", esperado, obtido);

            String saidaGravada = Files.readString(saida.toPath(), StandardCharsets.UTF_8);
            boolean contemCaracteresOriginais = saidaGravada.startsWith(conteudo);
            boolean contemChecksum = saidaGravada.contains(String.valueOf(esperado));
            verificar("arquivo de saida contem os caracteres originais", true, contemCaracteresOriginais);
            verificar("arquivo de saida contem o checksum apos os caracteres", true, contemChecksum);
        } finally {
            entrada.delete();
            saida.delete();
        }
    }

    // ----- Exercicio 3: calcularCRC(char[]) -----
    private static void testarCalcularCRC() {
        Checksum checksum = new Checksum();

        // vetor vazio -> CRC-32 = 0x00000000
        verificar("calcularCRC(\"\")", 0x00000000L, checksum.calcularCRC("".toCharArray()));

        // "123456789" e o vetor de teste padrao (check value) do CRC-32: 0xCBF43926
        verificar("calcularCRC(\"123456789\")", 0xCBF43926L, checksum.calcularCRC("123456789".toCharArray()));

        // "A" -> valor de referencia do CRC-32 (conferido com java.util.zip.CRC32)
        verificar("calcularCRC(\"A\")", 0xD3D99E8BL, checksum.calcularCRC("A".toCharArray()));
    }

    // ----- Exercicio 3: calcularCRCDoArquivoTexto(String, String) -----
    private static void testarCalcularCRCDoArquivoTexto() throws IOException {
        Checksum checksum = new Checksum();
        String conteudo = "Teste de CRC em arquivo";

        File entrada = File.createTempFile("crc_entrada", ".txt");
        File saida = File.createTempFile("crc_saida", ".txt");
        entrada.deleteOnExit();
        saida.deleteOnExit();

        try {
            Files.writeString(entrada.toPath(), conteudo, StandardCharsets.UTF_8);

            long esperado = checksum.calcularCRC(conteudo.toCharArray());
            long obtido = checksum.calcularCRCDoArquivoTexto(entrada.getAbsolutePath(), saida.getAbsolutePath());
            verificar("calcularCRCDoArquivoTexto() retorna o mesmo valor de calcularCRC()", esperado, obtido);

            String saidaGravada = Files.readString(saida.toPath(), StandardCharsets.UTF_8);
            boolean contemCaracteresOriginais = saidaGravada.startsWith(conteudo);
            boolean contemCrc = saidaGravada.contains(Long.toHexString(esperado).toUpperCase());
            verificar("arquivo de saida contem os caracteres originais", true, contemCaracteresOriginais);
            verificar("arquivo de saida contem o CRC apos os caracteres", true, contemCrc);
        } finally {
            entrada.delete();
            saida.delete();
        }
    }

    // ----- utilitario de verificacao (substitui o assertEquals do JUnit) -----
    private static void verificar(String nomeDoTeste, long esperado, long obtido) {
        totalTestes++;
        boolean ok = esperado == obtido;
        if (ok) {
            testesOk++;
        }
        System.out.printf("[%s] %s (esperado=%d, obtido=%d)%n", ok ? "OK" : "FALHOU", nomeDoTeste, esperado, obtido);
    }

    private static void verificar(String nomeDoTeste, boolean esperado, boolean obtido) {
        totalTestes++;
        boolean ok = esperado == obtido;
        if (ok) {
            testesOk++;
        }
        System.out.printf("[%s] %s%n", ok ? "OK" : "FALHOU", nomeDoTeste);
    }
}
