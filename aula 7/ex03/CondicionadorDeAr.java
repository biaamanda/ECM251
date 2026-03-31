package ex03;

/*Criar as classes CondicionadorDeAr e Termostato.
a) A classe CondicionadorDeAr tem um atributo termostato do tipo Termostato e um atributo boolean
chamado ligado. O construtor de CondicionadorDeAr não recebe nenhum parâmetro, mas instancia um termostato e o coloca ligado em false. Crie um método de acesso para ligado e outro para termostato. Não precisa fazer os métodos modificadores. Crie um método ligar(), que muda ligado para true, e um desligar(), que muda ligado para false;
Crie um método aumentarTemperatura(), que aumenta a temperatura do termostato em um grau cada vez que é chamado, até o limite de 28 graus. Crie um método reduzirTemperatura(), que reduz a temperatura em um grau cada vez que é chamado, até o limite de 15 graus. Crie um método imprimirTemperatura(), que imprime a temperatura atual. Não se esqueça de verificar se o condicionador está ligado antes de aumentar ou diminuir a temperatura ou imprimi-la;*/

public class CondicionadorDeAr {
    private Termostato termostato;
    private boolean on;

    public CondicionadorDeAr() {
        this.termostato = new Termostato();
        this.on = false;
    }

    public boolean isOn() {
        return on;
    }

    public Termostato getTermostato() {
        return termostato;
    }

    public void turnOn() {
        this.on = true;
    }

    public void turnOff() {
        this.on = false;
    }

    public void increaseTemperature() {
        if (termostato.getTemperature() < 28) {
            termostato.setTemperature(termostato.getTemperature() + 1);
        } else {
            System.out.println("Temperatura máxima atingida (28 graus)!");
        }
    }

    public void lowerTemperature() {
        if (termostato.getTemperature() > 15) {
            termostato.setTemperature(termostato.getTemperature() - 1);
        } else {
            System.out.println("Temperatura minima atingida");
        }
    }

    public void printTemperature() {
        System.out.println("Temperatura atual: " + termostato.getTemperature() + "°C");
    }

}
