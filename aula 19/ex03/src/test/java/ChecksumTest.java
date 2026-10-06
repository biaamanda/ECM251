import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.Checksum;

public class ChecksumTest {
    Checksum checksum = new Checksum();

    // exemplo dos slides 42-48: 'C','a','s','a','1' -> 0x57 ('W')
    @Test
    @DisplayName("Exemplo do slide: Casa1")
    public void exemploDoSlide() {
        assertEquals(0x57, checksum.calcularChecksum("Casa1".toCharArray()));
    }

    // soma 0 -> complemento de 2 de 0 é 0
    @Test
    @DisplayName("Vetor vazio")
    public void vetorVazio() {
        assertEquals(0, checksum.calcularChecksum(new char[] {}));
    }

    // 'A' = 65 -> 256 - 65 = 191
    @Test
    @DisplayName("Um caractere")
    public void umCaractere() {
        assertEquals(191, checksum.calcularChecksum("A".toCharArray()));
    }

    // 'A' + 'B' = 65 + 66 = 131 -> 256 - 131 = 125
    @Test
    @DisplayName("Dois caracteres")
    public void doisCaracteres() {
        assertEquals(125, checksum.calcularChecksum("AB".toCharArray()));
    }

    // a soma passa de 255 e o bit excedente é descartado
    @Test
    @DisplayName("Descarta o bit excedente da soma")
    public void descartaBitExcedente() {
        // 'z' = 122 -> 122 * 3 = 366 -> 366 - 256 = 110 -> 256 - 110 = 146
        assertEquals(146, checksum.calcularChecksum("zzz".toCharArray()));
    }

    // propriedade do algoritmo: dados + checksum somam 0 (mod 256)
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
}
