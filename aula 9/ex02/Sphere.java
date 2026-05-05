package ex02;

public class Sphere extends ThreeDimensionalShape {
    private double raio;

    public Sphere(double raio) {
        super("Esfera");
        this.raio = raio;
    }

    public double calcularVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(raio, 3);
    }

    public double calcularAreaTotal() {
        return 4 * Math.PI * raio * raio;
    }

    public int calcularVolume(int r) {
        return (int) ((4.0 / 3.0) * Math.PI * Math.pow(r, 3));
    }

    public int calcularAreaTotal(int r) {
        return (int) (4 * Math.PI * r * r);
    }
}