/*Crie a classe Animal com os atributos: espécie, nome e idade.
Na classe de teste, permita ao usuário criar dois animais e mostre uma comparação de idade
entre eles. */

public class Animal {
    String species;
    String name;
    int age;

    public Animal(String species, String name, int age) {
        this.species = species;
        this.name = name;
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
