package br.maua;

/*Exercício 2
Utilizando TDD, refazer o Exemplo 2, da página 28 do material da
Aula 18 da semana passada, utilizando o framework JUnit 

RetanguloTest retornava boolean e o RetanguloMain imprimia o resultado; 
com JUnit, quem executa e relata é o próprio runner.
Ciclo seguido: testes escritos com os métodos retornando 0 -> VERMELHO;
fórmulas de área e perímetro -> VERDE.
Para ver o "erro" do slide 31, troque o perímetro por base + 2 * altura: o JUnit acusa a falha.
*/

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RetanguloTest {
    private Retangulo retangulo;

    @BeforeEach
    public void setUp() { retangulo = new Retangulo(10, 2); }

    // testCalcularArea do slide 29
    @Test
    @DisplayName("Área")
    public void testCalcularArea() { assertEquals(20, retangulo.calcularArea()); }

    // testCalcularPerimetro do slide 29
    @Test
    @DisplayName("Perímetro")
    public void testCalcularPerimetro() { assertEquals(24, retangulo.calcularPerimetro()); }

    @Test
    @DisplayName("Quadrado")
    public void testQuadrado() {
        Retangulo quadrado = new Retangulo(5, 5);
        assertEquals(25, quadrado.calcularArea());
        assertEquals(20, quadrado.calcularPerimetro());
    }

    @Test
    @DisplayName("Lado zero")
    public void testLadoZero() {
        Retangulo semAltura = new Retangulo(7, 0);
        assertEquals(0, semAltura.calcularArea());
        assertEquals(14, semAltura.calcularPerimetro());
    }
}
