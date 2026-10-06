import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.Retangulo;

public class RetanguloTest {
    Retangulo retangulo;

    // roda antes de cada @Test (no slide, cada teste criava o próprio Retangulo)
    @BeforeEach
    public void criarRetangulo() {
        retangulo = new Retangulo(10, 2);
    }

    // testCalcularArea do slide 29
    @Test
    @DisplayName("Área: ")
    public void testCalcularArea() {
        assertEquals(20, retangulo.calcularArea());
    }

    // testCalcularPerimetro do slide 29
    @Test
    @DisplayName("Perímetro: ")
    public void testCalcularPerimetro() {
        assertEquals(24, retangulo.calcularPerimetro());
    }

    @Test
    @DisplayName("Área do quadrado")
    public void areaQuadrado() {
        assertEquals(25, new Retangulo(5, 5).calcularArea());
    }

    @Test
    @DisplayName("Perímetro do quadrado")
    public void perimetroQuadrado() {
        assertEquals(20, new Retangulo(5, 5).calcularPerimetro());
    }

    @Test
    @DisplayName("Lado zero tem área zero")
    public void ladoZero() {
        Retangulo semAltura = new Retangulo(7, 0);
        assertEquals(0, semAltura.calcularArea());
        assertEquals(14, semAltura.calcularPerimetro());
    }
}
