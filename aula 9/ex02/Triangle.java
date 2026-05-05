package ex02;

public class Triangle extends TwoDimensionalShape {
    private double lado;

    public Triangle(double lado) {
        super("Triângulo");
        this.lado = lado;
    }

    public double calcularArea() {
        return (Math.sqrt(3) / 4) * lado * lado;
    }

    public double calcularPerimetro() {
        return 3 * lado;
    }

    public int calcularArea(int l) {
        return (int) ((Math.sqrt(3) / 4) * l * l);
    }

    public int calcularPerimetro(int l) {
        return 3 * l;
    }

    public double getLado() {
        return lado;
    }
}