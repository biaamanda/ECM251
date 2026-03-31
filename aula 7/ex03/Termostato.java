package ex03;

/*b) A classe Termostato tem um atributo temperatura. Seu construtor não recebe parâmetros, mas instancia a temperatura em 20 graus. Crie um método de acesso e outro modificador. Estes métodos devem respeitar os limites estabelecidos no item anterior; */

public class Termostato {
    private int temperature;

    public Termostato() {
        this.temperature = 20;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
