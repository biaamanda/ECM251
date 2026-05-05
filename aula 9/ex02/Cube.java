package ex02;

public class Cube extends ThreeDimensionalShape {
    private double lado;

    public Cube(double lado) {
        super("Cubo");
        this.lado = lado;
    }

    public double calcularVolume() {
        return lado * lado * lado;
    }

    public double calcularAreaTotal() {
        return 6 * lado * lado;
    }

    public int calcularVolume(int l) {
        return l * l * l;
    }

    public int calcularAreaTotal(int l) {
        return 6 * l * l;
    }
}