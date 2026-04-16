/*Crie a classe Filme com os atributos: nome, diretor e ano de lançamento.
Implemente os métodos e teste a criação de um filme com dados do usuário */

public class Filme {
    String name;
    String director;
    int releaseYear;

    public Filme(String name, String director, int releaseYear) {
        this.name = name;
        this.director = director;
        this.releaseYear = releaseYear;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
