/*Crie a classe Produto com seu construtor, métodos de acesso
e modificadores e os atributos privados nome, do tipo String,
preço, do tipo double, quantidade, do tipo int. Crie uma classe
TesteProduto com um método main, onde você vai ler valores
de um produto, instanciar um produto e exibir o produto
criado */

public class Produto {
    private String name;
    private Double price;
    private int quantity;

    public Produto (String name, Double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price){
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

}

