/*Utilizando TDD, refazer o Exercício 1, da página 49 do material
da Aula 18 da semana passada, utilizando o framework JUnit */

/*
 * ===================== ENUNCIADO ORIGINAL (Aula 18, Exercício 1, pág. 49) =====================
 * Baseado nos conceitos da programação OO, desenvolver, em Java, uma classe
 * denominada Checksum, que contenha, entre outras coisas, um método denominado
 * calcularChecksum(), recebendo um vetor de caracteres digitados pelo usuário
 * e retornando o cálculo do respectivo checksum, baseado no algoritmo da Soma
 * e Complemento de 2;
 * Desenvolver, baseado nos conceitos de Testes Unitários, uma classe de testes
 * unitários para a classe Checksum, capaz de realizar os testes unitários e
 * automatizados de todos os métodos da classe Checksum (exceto do construtor).
 *
 * ===================== ALGORITMO: SOMA E COMPLEMENTO DE 2 (slides 41-48) =====================
 * Exemplo do slide com os caracteres 'C', 'a', 's', 'a', '1':
 * 1. Dividir em grupos de 8 bits (código ASCII de cada caractere):
 *      0x43, 0x61, 0x73, 0x61, 0x31
 *      0100 0011, 0110 0001, 0111 0011, 0110 0001, 0011 0001
 * 2. Somar os grupos:                       1 1010 1001
 * 3. Descartar o bit excedente (MSB):         1010 1001
 * 4. Complemento de 2: inverter os bits      0101 0110
 *    e somar 1:                              0101 0111   (0x57 = 'W')
 *    (descartar bit excedente, se houver)
 * 5. Anexar ao final dos dados:
 *      0x43, 0x61, 0x73, 0x61, 0x31, 0x57  ->  'C', 'a', 's', 'a', '1', 'W'
 * Esse exemplo já serve como primeiro caso de teste: "Casa1" -> 0x57.
 *
 * ===================== O QUE VOCÊ PRECISA FAZER =====================
 * (Setup do JUnit e ciclo do TDD: ver comentário no ../ex01/Ex01.java)
 * 1. Criar um projeto Maven com JUnit 5.
 * 2. Começar do zero pelo teste: escrever ChecksumTest com @Test e
 *    assertEquals para calcularChecksum() ANTES de existir a implementação.
 * 3. Ver falhar, implementar o mínimo, refatorar, repetir.
 * Dica: calcule à mão outras entradas pequenas (ex.: "A", "AB", vazio) para
 * usar como valores esperados.
 *
 * Sua solução da semana passada (sem TDD/JUnit) para consulta:
 *   aula 18/Ex01.java, aula 18/Checksum.java, aula 18/ChecksumTest.java
 */

public class Ex03 {

}
