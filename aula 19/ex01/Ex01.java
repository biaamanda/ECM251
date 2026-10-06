/*Utilizando TDD, refazer o Exemplo, da página 19 deste material
(Aula 19), utilizando o framework JUnit e alterando a ordenação
dos números para decrescente */

/*
 * ===================== CICLO DO TDD (slides 15-18) =====================
 * 1. Escrever o teste da funcionalidade, sem escrever o código dela (test first);
 * 2. Executar o teste sem a funcionalidade -> deve FALHAR (VERMELHO);
 * 3. Implementar de forma simples e provisória (baby steps);
 * 4. Executar o teste de novo -> deve PASSAR (VERDE);
 * 5. Refatorar para um código confiável, limpo e definitivo (REFATORAR);
 * 6. Executar o teste de novo -> deve continuar passando;
 * 7. Escolher nova funcionalidade e reiniciar o ciclo;
 * 8. A cada avanço, rodar TODOS os testes anteriores novamente.
 *
 * ===================== O QUE VOCÊ PRECISA FAZER =====================
 * 1. Criar um projeto Maven com JUnit 5 (passo a passo abaixo).
 * 2. Transformar o OrdenaTest em uma classe de teste do JUnit: métodos @Test
 *    com asserções (assertEquals, assertArrayEquals...) no lugar dos
 *    println/boolean. Com JUnit este main deixa de ser necessário:
 *    o próprio runner executa os testes.
 * 3. Mudar o requisito para ordem DECRESCENTE (ex.: esperado {10, 9}).
 * 4. Seguir o ciclo: teste falhando -> código mínimo passando -> refatorar
 *    para N números.
 *
 * ===================== JUNIT NO VS CODE (slides 44-60) =====================
 * 1. Com o JDK e o Extension Pack for Java instalados, instalar a extensão
 *    "Test Runner for Java" (suporte ao JUnit 5).
 * 2. Explorador -> Create Java Project -> Maven -> No Archetype...
 * 3. Digitar o group id (ex.: exemplo) e o artifact id (ex.: calculadora).
 * 4. Escolher a pasta destino -> Select Destination Folder -> Open.
 * 5. No pom.xml, incluir (linhas 16-23 do slide 54):
 *      <dependencies>
 *          <dependency>
 *              <groupId>org.junit.jupiter</groupId>
 *              <artifactId>junit-jupiter</artifactId>
 *              <version>5.9.1</version>
 *              <scope>test</scope>
 *          </dependency>
 *      </dependencies>
 * 6. Código em src/main/java/<pacote>/, testes em src/test/java/.
 * 7. Clicar nos triângulos verdes (play) ao lado da classe/método de teste.
 *
 * Exemplo dos slides 55-56:
 *   // src/test/java/CalculadoraTest.java
 *   import static org.junit.jupiter.api.Assertions.assertEquals;
 *   import org.junit.jupiter.api.DisplayName;
 *   import org.junit.jupiter.api.Test;
 *   import exemplo.Calculadora;
 *
 *   public class CalculadoraTest
 *   {   Calculadora calc = new Calculadora();
 *
 *       @Test
 *       @DisplayName("Soma:")
 *
 *       public void deveSomarInteiros()
 *       {   assertEquals(2, calc.soma(1,1));
 *       }
 *   }
 *
 *   // src/main/java/exemplo/Calculadora.java
 *   package exemplo;
 *
 *   public class Calculadora
 *   {   public int soma(int p1, int p2)
 *       {   return p1 + p2;
 *       }
 *   }
 *
 * ===================== SPOILER: fim do exemplo crescente (slides 25-29) =====================
 * Slide 25: no OrdenaTest, a linha do caso1Test passa a usar esperado.length.
 * Slide 26 (VERDE, só funciona para 2 números):
 *   if(vetor[0] > vetor[1]) { troca vetor[0] com vetor[1] usando uma variável rascunho }
 * Slide 28 (REFATORAR, bubble sort para N números):
 *   for(iA = 1; iA < iVet.length; iA++)
 *      for(iB = iVet.length-1; iB >= iA; iB--)
 *         if(iVet[iB-1] > iVet[iB]) { troca iVet[iB-1] com iVet[iB] }
 * Saída nos slides 27 e 29: true / true.
 */

public class Ex01 {
    public static void main(String args[]) {
        OrdenaTest tdd = new OrdenaTest();
    }
}
