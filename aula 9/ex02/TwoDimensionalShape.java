package ex02;

public abstract class TwoDimensionalShape extends Shape {

    public TwoDimensionalShape(String nome) {
        super(nome);
    }

    public abstract double calcularArea();
    public abstract double calcularPerimetro();

    public abstract int calcularArea(int medida);
    public abstract int calcularPerimetro(int medida);
}
