import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.Ordena;

public class OrdenaTest {
    Ordena ordena = new Ordena();

    // caso1Test do slide: o vetor continua com o mesmo tamanho
    @Test
    @DisplayName("Ficou com o mesmo tamanho")
    public void mantemTamanho() {
        int[] proposto = {10, 9, 8};
        ordena.ordenaNumerosDecrescentes(proposto);
        assertEquals(3, proposto.length);
    }

    // caso2Test do slide, agora decrescente
    @Test
    @DisplayName("Ordena dois números")
    public void ordenaDoisNumeros() {
        int[] proposto = {9, 10};
        ordena.ordenaNumerosDecrescentes(proposto);
        assertArrayEquals(new int[] {10, 9}, proposto);
    }

    @Test
    @DisplayName("Ordena N números")
    public void ordenaNNumeros() {
        int[] proposto = {3, 10, 1, 7, 5};
        ordena.ordenaNumerosDecrescentes(proposto);
        assertArrayEquals(new int[] {10, 7, 5, 3, 1}, proposto);
    }

    @Test
    @DisplayName("Mantém um vetor já decrescente")
    public void vetorJaOrdenado() {
        int[] proposto = {5, 4, 3, 2, 1};
        ordena.ordenaNumerosDecrescentes(proposto);
        assertArrayEquals(new int[] {5, 4, 3, 2, 1}, proposto);
    }

    @Test
    @DisplayName("Inverte um vetor crescente")
    public void vetorCrescente() {
        int[] proposto = {1, 2, 3, 4, 5};
        ordena.ordenaNumerosDecrescentes(proposto);
        assertArrayEquals(new int[] {5, 4, 3, 2, 1}, proposto);
    }

    @Test
    @DisplayName("Números repetidos")
    public void numerosRepetidos() {
        int[] proposto = {2, 7, 2, 7, 5};
        ordena.ordenaNumerosDecrescentes(proposto);
        assertArrayEquals(new int[] {7, 7, 5, 2, 2}, proposto);
    }

    @Test
    @DisplayName("Números negativos")
    public void numerosNegativos() {
        int[] proposto = {-3, 0, -10, 4};
        ordena.ordenaNumerosDecrescentes(proposto);
        assertArrayEquals(new int[] {4, 0, -3, -10}, proposto);
    }

    @Test
    @DisplayName("Vetor com um elemento")
    public void umElemento() {
        int[] proposto = {42};
        ordena.ordenaNumerosDecrescentes(proposto);
        assertArrayEquals(new int[] {42}, proposto);
    }

    @Test
    @DisplayName("Vetor vazio")
    public void vetorVazio() {
        int[] proposto = {};
        ordena.ordenaNumerosDecrescentes(proposto);
        assertArrayEquals(new int[] {}, proposto);
    }
}
