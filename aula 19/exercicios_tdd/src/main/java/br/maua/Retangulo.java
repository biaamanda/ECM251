package br.maua;

// Exercício 2: classe do slide 28 da Aula 18, Exemplo 2 (enunciado em RetanguloTest.java)

public class Retangulo {
    private int base;
    private int altura;

    public Retangulo(int base, int altura) {
        this.base = base;
        this.altura = altura;
    }

    public int calcularArea() { return base * altura; }

    public int calcularPerimetro() { return 2 * base + 2 * altura; }
}
