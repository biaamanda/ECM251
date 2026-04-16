/*Crie a classe Carro com os atributos: marca, modelo e ano de fabricação.
Implemente os métodos necessários.
Na classe de teste, instancie um carro com dados fornecidos via JOptionPane e exiba-os ao
final */
public class Carro {
    String brand;
    String model;
    int fabricationYear;

    public Carro(String brand, String model, int fabricationYear) {
        this.brand = brand;
        this.model = model;
        this.fabricationYear = fabricationYear;
    }

    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel(){
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getFabricationYear(){
        return fabricationYear;
    }
    public void setFabricationYear(int fabricationYear) {
        this.fabricationYear = fabricationYear;
    }
}
