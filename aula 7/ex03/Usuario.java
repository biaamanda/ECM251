package ex03;
/*c) Crie a classe Usuario, com o método main(). Neste método você deve instanciar um CondicionadorDeAr, aumentar a temperatura para 30 graus (receber mensagem de erro), reduzir a temperatura para 10 graus (receber mensagem de erro). Aumentar a temperatura para 25 graus e imprimir a temperatura. */

public class Usuario {
    public static void main(String[] args) {

        CondicionadorDeAr ar = new CondicionadorDeAr();

        ar.turnOn();

        for (int i = 0; i < 10; i++) {
            ar.increaseTemperature();
        }

        for (int i = 0; i < 20; i++) {
            ar.lowerTemperature();
        }

        while (ar.getTermostato().getTemperature() < 25) {
            ar.increaseTemperature();
        }

        ar.printTemperature();
    }
}
