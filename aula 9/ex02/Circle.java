package ex02;

public class Circle extends TwoDimensionalShape {
    private double raio;

    public Circle(double raio) {
        super("Círculo");
        this.raio = raio;
    }

    public double calcularArea() {
        return Math.PI * raio * raio;
    }

    public double calcularPerimetro() {
        return 2 * Math.PI * raio;
    }

    public int calcularArea(int r) {
        return (int) (Math.PI * r * r);
    }

    public int calcularPerimetro(int r) {
        return (int) (2 * Math.PI * r);
    }

    public double getRaio() {
        return raio;
    }
}
