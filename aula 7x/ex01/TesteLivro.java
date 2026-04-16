import java.util.Scanner;

public class TesteLivro {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Título do livro: ");
    String title = sc.nextLine();
    System.out.print("Autor: ");
    String author = sc.nextLine();
    System.out.print("Número de páginas: ");
    int pageNumber = sc.nextInt();

    Livro livro = new Livro(title, author, pageNumber);

    System.out.println("\n--- Livro Criado ---");
    System.out.println("Livro: " + livro.getTitle());
    System.out.println("Autor: " + livro.getAuthor());
    System.out.println("Numero de paginas: " + livro.getPageNumber());

    sc.close();
    }
}
