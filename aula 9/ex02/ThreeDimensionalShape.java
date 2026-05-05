package ex02;

public abstract class ThreeDimensionalShape extends Shape {
    
    public ThreeDimensionalShape(String nome) {
        super(nome);
    }

    // SOBRESCRIÇÃO
    public abstract double calcularVolume();
    public abstract double calcularAreaTotal();

    // SOBRECARGA (int)
    public abstract int calcularVolume(int medida);
    public abstract int calcularAreaTotal(int medida);
}
