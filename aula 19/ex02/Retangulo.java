// Transcrição do slide 28 (Aula 18, Exemplo 2)

public class Retangulo {
    private int base;
    private int altura;

    public Retangulo(int base, int altura){
        this.base = base;
        this.altura = altura;
    }

    public int calcularArea(){
        return base * altura;
    }

    public int calcularPerimetro(){
        return 2*base + 2*altura;
    }

}
