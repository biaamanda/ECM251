/*Crie a classe Produto com os atributos: nome, código e preço.
Crie uma classe de teste que leia os dados de um produto e mostre uma “etiqueta” do produto
com seus dados */

public class Produto {
    String name;
    String code;
    double price;

    public Produto(String name, String code, double price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
