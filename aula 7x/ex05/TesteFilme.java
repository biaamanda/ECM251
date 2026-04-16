import java.util.Scanner;

public class TesteFilme {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Filme: ");
        String film = scanner.nextLine();
        System.out.print("Diretor: ");
        String director = scanner.nextLine();
        System.out.print("Ano da estreia: ");
        int releaseYear = scanner.nextInt();

        Filme filme = new Filme(film, director, releaseYear);

        System.out.println("\n--- Filme Criado ---");
        System.out.println("Filme: " + filme.getName());
        System.out.println("Diretor: " + filme.getDirector());
        System.out.println("Ano da estreia: " + filme.getReleaseYear());

        scanner.close();
    }
}
