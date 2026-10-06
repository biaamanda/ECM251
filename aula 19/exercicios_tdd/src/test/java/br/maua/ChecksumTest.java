package br.maua;

/*Exercício 3
Utilizando TDD, refazer o Exercício 1, da página 49 do material
da Aula 18 da semana passada, utilizando o framework JUnit

Enunciado original:
Baseado nos conceitos da programação OO, desenvolver, em Java, uma classe
denominada Checksum, que contenha, entre outras coisas, um método denominado
calcularChecksum(), recebendo um vetor de caracteres digitados pelo usuário
e retornando o cálculo do respectivo checksum, baseado no algoritmo da Soma
e Complemento de 2;
Desenvolver, baseado nos conceitos de Testes Unitários, uma classe de testes
unitários para a classe Checksum, capaz de realizar os testes unitários e
automatizados de todos os métodos da classe Checksum (exceto do construtor). */

/*Exercício 4
Utilizando TDD, refazer o Exercício 2, da página 50 do material
da Aula 18 da semana passada, utilizando o framework JUnit

Enunciado original:
Baseado na solução do exercício 1, anterior, acrescentar à classe Checksum o
método calcularChecksumDoArquivoTexto(), capaz de ler os caracteres de um
arquivo texto e executar o cálculo do checksum, baseado no algoritmo da Soma
e Complemento de 2, da mesma forma como feito no exercício anterior, quando
lia os caracteres via teclado;
Após o cálculo do checksum, gravar o valor encontrado ao final de um outro
arquivo texto, logo após os caracteres fornecidos;
Desenvolver, baseado nos conceitos de Testes Unitários, uma classe de testes
unitários para a classe Checksum, capaz de realizar os testes unitários e
automatizados de todos os métodos da classe Checksum (exceto do construtor). */

/*Exercício 5
Utilizando TDD, refazer o Exercício 3 – Desafio, da página 51 do
material da Aula 18 da semana passada, utilizando o framework
JUnit.

Enunciado original:
Pesquisar o método de checksum através do cálculo de CRC e implementar o
método calcularCRC(), adicionando-o à classe Checksum do exercício anterior
e executando todas as atividades solicitadas nos exercícios 1 e 2. */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;

public class ChecksumTest {
    private Checksum checksumObj;

    // pasta temporária criada pelo JUnit e apagada depois de cada teste
    @TempDir
    Path pasta;

    @BeforeEach
    public void setUp() { checksumObj = new Checksum(); }

    private String criarArquivo(String nome, String conteudo) throws IOException {
        Path arquivo = pasta.resolve(nome);
        Files.write(arquivo, conteudo.getBytes(StandardCharsets.ISO_8859_1));
        return arquivo.toString();
    }

    private String lerArquivo(String caminho) throws IOException {
        return new String(Files.readAllBytes(pasta.resolve(caminho)), StandardCharsets.ISO_8859_1);
    }

    // ==================== Exercício 3: calcularChecksum() ====================
    // Ciclo seguido: o 1º teste foi o exemplo do slide com o método retornando 0
    // -> VERMELHO; depois a soma e o complemento de 2 -> VERDE; os demais casos
    // vieram em seguida.

    // exemplo dos slides 42-48: 'C','a','s','a','1' -> 0x57 ('W')
    @Test
    @DisplayName("Ex3: exemplo do slide (Casa1 -> W)")
    public void testCalcularChecksumExemploAula() {
        assertEquals('W', checksumObj.calcularChecksum(new char[] {'C', 'a', 's', 'a', '1'}));
    }

    // soma 0 -> complemento de 2 de 0 é 0
    @Test
    @DisplayName("Ex3: vetor vazio")
    public void testCalcularChecksumVazio() {
        assertEquals(0, checksumObj.calcularChecksum(new char[] {}));
    }

    // 'A' = 65 -> 256 - 65 = 191;  'A' + 'B' = 131 -> 256 - 131 = 125
    @Test
    @DisplayName("Ex3: um e dois caracteres")
    public void testCalcularChecksumPoucosCaracteres() {
        assertEquals(191, checksumObj.calcularChecksum("A".toCharArray()));
        assertEquals(125, checksumObj.calcularChecksum("AB".toCharArray()));
    }

    // 'z' = 122 -> 122 * 3 = 366 -> descarta o bit excedente: 110 -> 256 - 110 = 146
    @Test
    @DisplayName("Ex3: descarta o bit excedente da soma")
    public void testCalcularChecksumBitExcedente() {
        assertEquals(146, checksumObj.calcularChecksum("zzz".toCharArray()));
    }

    // propriedade do algoritmo: dados + checksum somam 0 (mod 256)
    @Test
    @DisplayName("Ex3: soma dos dados + checksum fecha em zero")
    public void testCalcularChecksumFechaEmZero() {
        char[] dados = "Soma e Complemento de 2".toCharArray();
        int soma = 0;
        for (char c : dados) { soma += c; }
        assertEquals(0, (soma + checksumObj.calcularChecksum(dados)) & 0xFF);
    }

    // ==================== Exercício 4: calcularChecksumDoArquivoTexto() ====================
    // Testes escritos antes do método existir -> VERMELHO (nem compilava);
    // método implementado -> VERDE; todos os testes do Ex3 continuam passando.

    @Test
    @DisplayName("Ex4: retorna o checksum do conteúdo do arquivo")
    public void testCalcularChecksumDoArquivoTextoRetorno() throws IOException {
        String entrada = criarArquivo("entrada.txt", "Casa1");
        assertEquals('W', checksumObj.calcularChecksumDoArquivoTexto(entrada, pasta.resolve("saida.txt").toString()));
    }

    // slide 48: os dados transmitidos ficam 'C','a','s','a','1','W'
    @Test
    @DisplayName("Ex4: saída tem os caracteres seguidos do checksum")
    public void testCalcularChecksumDoArquivoTexto() throws IOException {
        String entrada = criarArquivo("entrada.txt", "Casa1");
        checksumObj.calcularChecksumDoArquivoTexto(entrada, pasta.resolve("saida.txt").toString());
        assertEquals("Casa1W", lerArquivo("saida.txt"));
    }

    @Test
    @DisplayName("Ex4: arquivo de entrada não é alterado")
    public void testCalcularChecksumDoArquivoTextoEntradaIntacta() throws IOException {
        String entrada = criarArquivo("entrada.txt", "Casa1");
        checksumObj.calcularChecksumDoArquivoTexto(entrada, pasta.resolve("saida.txt").toString());
        assertEquals("Casa1", lerArquivo("entrada.txt"));
    }

    // ==================== Exercício 5: calcularCRC() e calcularCRCDoArquivoTexto() ====================
    // Testes escritos primeiro com o valor de referência "123456789" -> CBF43926
    // -> VERMELHO; CRC-32 bit a bit -> VERDE; por último a comparação com o
    // java.util.zip.CRC32. Os testes dos Ex3 e Ex4 continuam passando.

    // "123456789" é o valor de verificação padrão do CRC-32
    @Test
    @DisplayName("Ex5: CRC-32 de referência (123456789 -> CBF43926)")
    public void testCalcularCRCReferencia() {
        assertEquals(0xCBF43926L, checksumObj.calcularCRC("123456789".toCharArray()));
    }

    @Test
    @DisplayName("Ex5: CRC-32 do exemplo do slide e do vetor vazio")
    public void testCalcularCRC() {
        assertEquals(560253239L, checksumObj.calcularCRC(new char[] {'C', 'a', 's', 'a', '1'}));
        assertEquals(0L, checksumObj.calcularCRC(new char[] {}));
    }

    // confere contra a implementação do próprio Java
    @Test
    @DisplayName("Ex5: igual ao java.util.zip.CRC32")
    public void testCalcularCRCIgualAoJava() {
        String texto = "Linguagens de Programação I";
        CRC32 referencia = new CRC32();
        for (char c : texto.toCharArray()) { referencia.update(c & 0xFF); }
        assertEquals(referencia.getValue(), checksumObj.calcularCRC(texto.toCharArray()));
    }

    // o CRC detecta a troca de ordem dos caracteres; a soma simples não
    @Test
    @DisplayName("Ex5: CRC detecta troca de ordem que o checksum não detecta")
    public void testCalcularCRCTrocaDeOrdem() {
        char[] original = "ab".toCharArray();
        char[] trocado = "ba".toCharArray();
        assertEquals(checksumObj.calcularChecksum(original), checksumObj.calcularChecksum(trocado));
        assertNotEquals(checksumObj.calcularCRC(original), checksumObj.calcularCRC(trocado));
    }

    @Test
    @DisplayName("Ex5: saída tem os caracteres seguidos do CRC-32")
    public void testCalcularCRCDoArquivoTexto() throws IOException {
        String entrada = criarArquivo("entrada.txt", "123456789");
        long crc = checksumObj.calcularCRCDoArquivoTexto(entrada, pasta.resolve("saida.txt").toString());
        assertEquals(0xCBF43926L, crc);
        assertEquals("123456789CBF43926", lerArquivo("saida.txt"));
    }
}
