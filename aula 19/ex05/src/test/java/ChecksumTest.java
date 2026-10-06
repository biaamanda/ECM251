import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.example.Checksum;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;

public class ChecksumTest {
    Checksum checksum = new Checksum();

    // pasta temporária criada pelo JUnit e apagada ao final de cada teste
    @TempDir
    Path pasta;

    // cria o arquivo de entrada na pasta temporária
    private Path criarEntrada(String conteudo) throws IOException {
        Path entrada = pasta.resolve("entrada.txt");
        Files.writeString(entrada, conteudo, StandardCharsets.UTF_8);
        return entrada;
    }

    // ---------- calcularChecksum() (testes do ex03) ----------

    @Test
    @DisplayName("Exemplo do slide: Casa1")
    public void exemploDoSlide() {
        assertEquals(0x57, checksum.calcularChecksum("Casa1".toCharArray()));
    }

    @Test
    @DisplayName("Vetor vazio")
    public void vetorVazio() {
        assertEquals(0, checksum.calcularChecksum(new char[] {}));
    }

    @Test
    @DisplayName("Um caractere")
    public void umCaractere() {
        assertEquals(191, checksum.calcularChecksum("A".toCharArray()));
    }

    @Test
    @DisplayName("Dois caracteres")
    public void doisCaracteres() {
        assertEquals(125, checksum.calcularChecksum("AB".toCharArray()));
    }

    @Test
    @DisplayName("Descarta o bit excedente da soma")
    public void descartaBitExcedente() {
        assertEquals(146, checksum.calcularChecksum("zzz".toCharArray()));
    }

    @Test
    @DisplayName("Soma dos dados + checksum fecha em zero")
    public void somaComChecksumFechaEmZero() {
        char[] dados = "Soma e Complemento de 2".toCharArray();
        int soma = 0;
        for (char c : dados) {
            soma += c;
        }

        assertEquals(0, (soma + checksum.calcularChecksum(dados)) & 0xFF);
    }

    // ---------- calcularChecksumDoArquivoTexto() (testes do ex04) ----------

    @Test
    @DisplayName("Arquivo: retorna o checksum do conteúdo")
    public void arquivoRetornaChecksum() throws IOException {
        Path entrada = criarEntrada("Casa1");
        Path saida = pasta.resolve("saida.txt");

        assertEquals(0x57, checksum.calcularChecksumDoArquivoTexto(entrada.toString(), saida.toString()));
    }

    @Test
    @DisplayName("Arquivo de saída: caracteres originais seguidos do checksum")
    public void arquivoDeSaidaChecksum() throws IOException {
        Path entrada = criarEntrada("Casa1");
        Path saida = pasta.resolve("saida.txt");

        checksum.calcularChecksumDoArquivoTexto(entrada.toString(), saida.toString());

        assertEquals("Casa1" + System.lineSeparator() + "Checksum: 87",
                Files.readString(saida, StandardCharsets.UTF_8));
    }

    // ---------- calcularCRC() ----------

    // "123456789" é o valor de verificação padrão do CRC-32
    @Test
    @DisplayName("CRC-32 de referência: 123456789")
    public void crcDeReferencia() {
        assertEquals(0xCBF43926L, checksum.calcularCRC("123456789".toCharArray()));
    }

    @Test
    @DisplayName("CRC-32 do vetor vazio")
    public void crcVazio() {
        assertEquals(0L, checksum.calcularCRC(new char[] {}));
    }

    @Test
    @DisplayName("CRC-32 de um caractere")
    public void crcUmCaractere() {
        assertEquals(0xD3D99E8BL, checksum.calcularCRC("A".toCharArray()));
    }

    // confere contra a implementação do próprio Java
    @Test
    @DisplayName("CRC-32 igual ao java.util.zip.CRC32")
    public void crcIgualAoDoJava() {
        String texto = "Casa1 - Linguagens de Programação I";
        CRC32 referencia = new CRC32();
        for (char c : texto.toCharArray()) {
            referencia.update(c & 0xFF);
        }

        assertEquals(referencia.getValue(), checksum.calcularCRC(texto.toCharArray()));
    }

    // o CRC detecta a troca de ordem dos caracteres; a soma simples não
    @Test
    @DisplayName("CRC detecta troca de ordem que o checksum não detecta")
    public void crcDetectaTrocaDeOrdem() {
        char[] original = "ab".toCharArray();
        char[] trocado = "ba".toCharArray();

        assertEquals(checksum.calcularChecksum(original), checksum.calcularChecksum(trocado));
        assertNotEquals(checksum.calcularCRC(original), checksum.calcularCRC(trocado));
    }

    // ---------- calcularCRCDoArquivoTexto() ----------

    @Test
    @DisplayName("Arquivo: retorna o CRC-32 do conteúdo")
    public void arquivoRetornaCrc() throws IOException {
        Path entrada = criarEntrada("123456789");
        Path saida = pasta.resolve("saida.txt");

        assertEquals(0xCBF43926L, checksum.calcularCRCDoArquivoTexto(entrada.toString(), saida.toString()));
    }

    @Test
    @DisplayName("Arquivo de saída: caracteres originais seguidos do CRC-32")
    public void arquivoDeSaidaCrc() throws IOException {
        Path entrada = criarEntrada("123456789");
        Path saida = pasta.resolve("saida.txt");

        checksum.calcularCRCDoArquivoTexto(entrada.toString(), saida.toString());

        assertEquals("123456789" + System.lineSeparator() + "CRC-32: CBF43926",
                Files.readString(saida, StandardCharsets.UTF_8));
    }
}
