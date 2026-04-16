/*Crie a classe Cidade com os atributos: nome da cidade, estado e população.
Na classe de teste, leia os dados e exiba uma frase como:
"A cidade de Campinas, localizada em SP, possui 1.200.000 habitantes." */

public class Cidade {
    String name;
    String state;
    int population;

    public Cidade(String name, String state, int population) {
        this.name = name;
        this.state = state;
        this.population = population;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
}
