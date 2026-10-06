import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.Calculadora;

public class CalculadoraTest {
    Calculadora calculadora = new Calculadora();

    // verificar se um caso esperado (uma soma que eu sei o resultado) é verdadeiro
    @Test 
    @DisplayName("Soma: ")
    public void somarInteiros() {
        assertEquals(2, calculadora.soma(1, 1));

    }
}
