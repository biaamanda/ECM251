package ex02;

public class Tetrahedron extends ThreeDimensionalShape {
    private double lado;

    public Tetrahedron(double lado) {
        super("Tetraedro");
        this.lado = lado;
    }

    public double calcularVolume() {
        return (Math.pow(lado, 3)) / (6 * Math.sqrt(2));
    }

    public double calcularAreaTotal() {
        return Math.sqrt(3) * lado * lado;
    }

    public int calcularVolume(int l) {
        return (int) ((Math.pow(l, 3)) / (6 * Math.sqrt(2)));
    }

    public int calcularAreaTotal(int l) {
        return (int) (Math.sqrt(3) * l * l);
    }
}
