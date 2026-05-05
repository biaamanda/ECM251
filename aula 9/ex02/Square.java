package ex02;

public class Square extends TwoDimensionalShape {
    private double lado;

    public Square(double lado) {
        super("Quadrado");
        this.lado = lado;
    }

    public double calcularArea() {
        return lado * lado;
    }

    public double calcularPerimetro() {
        return 4 * lado;
    }

    public int calcularArea(int l) {
        return l * l;
    }

    public int calcularPerimetro(int l) {
        return 4 * l;
    }

    public double getLado() {
        return lado;
    }
}