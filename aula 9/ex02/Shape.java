package ex02;

/*Dadas as classes Shape, TwoDimensionalShape, Circle,
Square, Triangle, ThreeDimensionalShape, Sphere, Cube e
Tetrahedron, apresentadas no diagrama de classes a seguir,
implementar o polimorfismo (Sobrecarga e Sobreposição)
para o cálculo de área e perímetro (duas dimensões) e de
volume e área total das faces (três dimensões), para entradas,
cálculos e apresentações com medidas em números inteiros,
apenas, e em ponto flutuante, distintamente.
Criar a classe de teste, com a entrada e apresentação dos
valores de todos os atributos envolvidos, respectivamente, via
teclado e via monitor */

public class Shape {
    private String nome;

    public Shape(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
