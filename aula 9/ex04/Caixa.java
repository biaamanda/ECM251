package ex04;

/*Você̂ vai gerenciar um depósito e resolveu criar um sistema
para isso. Para isso criou uma classe chamada Caixa, com os
atributos corredor (String), posicao (int), peso (double) e
dono (String), que armazena o nome do dono da caixa.
Respeitou o encapsulamento e criou os métodos de acesso e
os modificadores;
 */

public class Caixa {
    private String corredor;
    private int posicao;
    private double peso;
    private String dono;

    public Caixa(String corredor, int posicao, double peso, String dono) {
        this.corredor = corredor;
        this.posicao = posicao;
        this.peso = peso;
        this.dono = dono;
    }

    public String getCorredor() {
        return corredor;
    }

    public void setCorredor(String corredor) {
        this.corredor = corredor;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }
}
