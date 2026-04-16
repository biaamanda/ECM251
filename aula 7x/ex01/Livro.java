/*Crie a classe Livro com os atributos: título, autor e número de páginas.
Implemente construtores, getters e setters.
Na classe TesteLivro, peça ao usuário os dados do livro, crie um objeto Livro e mostre suas
informações. */

public class Livro {
    private String title;
    private String author;
    private int pageNumber;

    public Livro(String title, String author, int pageNumber) {
        this.title = title;
        this.author = author;
        this.pageNumber = pageNumber;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
