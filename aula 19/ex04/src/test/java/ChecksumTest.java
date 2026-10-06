import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.example.Checksum;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChecksumTest {
    Checksum checksum = new Checksum();

    // pasta temporária criada pelo JUnit e apagada ao final de cada teste
    @TempDir
    Path pasta;

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

    // ---------- calcularChecksumDoArquivoTexto() ----------

    // cria o arquivo de entrada na pasta temporária
    private Path criarEntrada(String conteudo) throws IOException {
        Path entrada = pasta.resolve("entrada.txt");
        Files.writeString(entrada, conteudo, StandardCharsets.UTF_8);
        return entrada;
    }

    @Test
    @DisplayName("Arquivo: retorna o checksum do conteúdo")
    public void arquivoRetornaChecksum() throws IOException {
        Path entrada = criarEntrada("Casa1");
        Path saida = pasta.resolve("saida.txt");

        assertEquals(0x57, checksum.calcularChecksumDoArquivoTexto(entrada.toString(), saida.toString()));
    }

    @Test
    @DisplayName("Arquivo: mesmo valor que calcularChecksum()")
    public void arquivoIgualAoVetor() throws IOException {
        String conteudo = "Teste de checksum em arquivo";
        Path entrada = criarEntrada(conteudo);
        Path saida = pasta.resolve("saida.txt");

        assertEquals(checksum.calcularChecksum(conteudo.toCharArray()),
                checksum.calcularChecksumDoArquivoTexto(entrada.toString(), saida.toString()));
    }

    @Test
    @DisplayName("Arquivo de saída: caracteres originais seguidos do checksum")
    public void arquivoDeSaida() throws IOException {
        Path entrada = criarEntrada("Casa1");
        Path saida = pasta.resolve("saida.txt");

        checksum.calcularChecksumDoArquivoTexto(entrada.toString(), saida.toString());

        String gravado = Files.readString(saida, StandardCharsets.UTF_8);
        assertEquals("Casa1" + System.lineSeparator() + "Checksum: 87", gravado);
    }

    @Test
    @DisplayName("Arquivo de entrada não é alterado")
    public void entradaNaoAlterada() throws IOException {
        Path entrada = criarEntrada("Casa1");
        Path saida = pasta.resolve("saida.txt");

        checksum.calcularChecksumDoArquivoTexto(entrada.toString(), saida.toString());

        assertEquals("Casa1", Files.readString(entrada, StandardCharsets.UTF_8));
        assertTrue(Files.exists(saida));
    }
}
