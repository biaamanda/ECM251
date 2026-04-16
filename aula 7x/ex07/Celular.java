/*Crie a classe Celular com os atributos: marca, modelo e capacidade de armazenamento (em
GB).
Na classe de teste, leia os dados via JOptionPane e mostre-os formatados como uma ficha
técnica. */

public class Celular {
    String brand;
    String model;
    int storageCapacity;

    public Celular(String brand, String model, int storageCapacity) {
        this.brand = brand;
        this.model = model;
        this.storageCapacity = storageCapacity;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }
    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public String fichaTecnica() {
        return "╔══════════════════════════════════╗\n"
                + "       FICHA TÉCNICA — CELULAR     \n"
                + "║══════════════════════════════════║\n"
                + " Marca:        " + String.format("%-18s", brand) + "\n"
                + " Modelo:       " + String.format("%-18s", model) + "\n"
                + " Armazenamento:" + String.format("%-18s", storageCapacity + " GB") + "\n"
                + "╚══════════════════════════════════╝";
    }
}
