package br.maua;

/*Exercício 1
Utilizando TDD, refazer o Exemplo, da página 19 deste material
(Aula 19), utilizando o framework JUnit e alterando a ordenação
dos números para decrescente 

Ciclo seguido: 
1) teste de 2 números com o método vazio -> VERMELHO;
2) troca simples de vetor[0] e vetor[1] -> VERDE; 
3) refatorado para o bubble sort de N números -> todos os testes VERDES de novo.
*/

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrdenaTest {
    private Ordena teste;

    @BeforeEach
    public void setUp() { teste = new Ordena(); }

    // caso1Test do slide: o vetor continua com o mesmo tamanho
    @Test
    @DisplayName("Ficou com o mesmo tamanho")
    public void testaMesmoTamanho() {
        int[] proposto = {10, 9, 8};
        teste.ordenaNumerosDecrescentes(proposto);
        assertEquals(3, proposto.length);
    }

    // caso2Test do slide, agora decrescente
    @Test
    @DisplayName("Ordena dois números")
    public void testaDoisNumeros() {
        int[] proposto = {9, 10};
        teste.ordenaNumerosDecrescentes(proposto);
        assertArrayEquals(new int[] {10, 9}, proposto);
    }

    @Test
    @DisplayName("Ordena N números")
    public void testaOrdenacaoDecrescente() {
        int[] proposto = {10, 9, 20, 5};
        teste.ordenaNumerosDecrescentes(proposto);
        assertArrayEquals(new int[] {20, 10, 9, 5}, proposto);
    }

    @Test
    @DisplayName("Inverte um vetor crescente")
    public void testaVetorCrescente() {
        int[] proposto = {1, 2, 3, 4, 5};
        teste.ordenaNumerosDecrescentes(proposto);
        assertArrayEquals(new int[] {5, 4, 3, 2, 1}, proposto);
    }

    @Test
    @DisplayName("Mantém um vetor já decrescente")
    public void testaVetorJaOrdenado() {
        int[] proposto = {5, 4, 3, 2, 1};
        teste.ordenaNumerosDecrescentes(proposto);
        assertArrayEquals(new int[] {5, 4, 3, 2, 1}, proposto);
    }

    @Test
    @DisplayName("Números repetidos")
    public void testaRepetidos() {
        int[] proposto = {2, 7, 2, 7, 5};
        teste.ordenaNumerosDecrescentes(proposto);
        assertArrayEquals(new int[] {7, 7, 5, 2, 2}, proposto);
    }

    @Test
    @DisplayName("Números negativos")
    public void testaNegativos() {
        int[] proposto = {-3, 0, -10, 4};
        teste.ordenaNumerosDecrescentes(proposto);
        assertArrayEquals(new int[] {4, 0, -3, -10}, proposto);
    }

    @Test
    @DisplayName("Vetor com um elemento e vetor vazio")
    public void testaUmElementoEVazio() {
        int[] um = {42};
        int[] vazio = {};
        teste.ordenaNumerosDecrescentes(um);
        teste.ordenaNumerosDecrescentes(vazio);
        assertArrayEquals(new int[] {42}, um);
        assertArrayEquals(new int[] {}, vazio);
    }
}
