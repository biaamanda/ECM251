package com.example;

/*Utilizando TDD, refazer o Exemplo 2, da página 28 do material da
Aula 18 da semana passada, utilizando o framework JUnit */

// Os testes ficam em src/test/java/RetanguloTest.java (rodar pelo ▶ do VS Code).
// O RetanguloTest do slide 29 retornava boolean e o RetanguloMain (slide 30)
// imprimia o resultado; com JUnit, quem executa e relata os testes é o runner.
// Ciclo seguido: testes escritos com os métodos retornando 0 -> VERMELHO;
// fórmulas de área e perímetro -> VERDE. Para ver o "erro" do slide 31, troque
// o perímetro por base + 2 * altura: o JUnit acusa a falha.

public class Main {
    public static void main(String[] args) {
        Retangulo retangulo = new Retangulo(10, 2);

        System.out.println("Área     : " + retangulo.calcularArea());
        System.out.println("Perímetro: " + retangulo.calcularPerimetro());
    }
}
