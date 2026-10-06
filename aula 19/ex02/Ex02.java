/*Utilizando TDD, refazer o Exemplo 2, da página 28 do material da
Aula 18 da semana passada, utilizando o framework JUnit */

/*
 * ===================== O QUE TEM NESTA PASTA =====================
 * Transcrição do Exemplo 2 da Aula 18 (slides 28-31), sem JUnit:
 *   - Retangulo.java     -> slide 28 (diagrama: -base, -altura,
 *                           +calcularArea(), +calcularPerimetro())
 *   - RetanguloTest.java -> slide 29 (diagrama: +retangulo,
 *                           +testCalcularArea(), +testCalcularPerimetro())
 *   - Ex02.java          -> slide 30 (no slide a classe se chama RetanguloMain)
 *
 * Para rodar:  javac -encoding UTF-8 *.java  e depois  java Ex02
 * Saída esperada (slide 30):
 *   testCalcularArea: true
 *   testCalcularPerimetro: true
 *
 * No slide 31 ("com erro") o main é o mesmo, mas a saída fica
 *   testCalcularArea: true
 *   testCalcularPerimetro: false
 * ou seja, o teste pega um erro introduzido no cálculo do perímetro.
 *
 * ===================== O QUE VOCÊ PRECISA FAZER =====================
 * (Setup do JUnit e ciclo do TDD: ver comentário no ../ex01/Ex01.java)
 * 1. Criar um projeto Maven com JUnit 5.
 * 2. Começar pelo teste: reescrever o RetanguloTest com métodos @Test
 *    (void) e assertEquals, no lugar de retornar boolean. Com JUnit este
 *    main deixa de ser necessário.
 * 3. Ver os testes falharem (VERMELHO) com um Retangulo ainda vazio/errado,
 *    implementar o mínimo para passar (VERDE) e refatorar.
 * 4. Reproduzir o "erro" do slide 31 (ex.: trocar 2*base + 2*altura por
 *    base + 2*altura) e ver o JUnit acusar a falha.
 * Dica: teste mais casos (quadrado, lados 0, valores grandes) e use
 * @BeforeEach para criar o retangulo antes de cada teste.
 */

public class Ex02
{
    public static void main(String[] args)
    {
        RetanguloTest teste = new RetanguloTest();
        boolean resultado;

        resultado = teste.testCalcularArea();
        System.out.println("testCalcularArea: " + resultado);

        resultado = teste.testCalcularPerimetro();
        System.out.println("testCalcularPerimetro: " + resultado);
    }
}
