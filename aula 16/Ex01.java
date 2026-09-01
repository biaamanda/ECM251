/*Desenvolver, utilizando o console como interface com o
usuário, um programa que receba NOME e SENHA por pessoa
cadastrada, digitados pelo usuário, e que, posteriormente,
sejam, pelo aplicativo Java, gravados em um arquivo de texto. */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ex01 {
    private static final String ARQUIVO = "usuarios.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // abre o arquivo em modo "append" (true) para nao apagar cadastros anteriores
        try (PrintWriter saida = new PrintWriter(new FileWriter(ARQUIVO, true))) {
            String continuar;

            do {
                System.out.print("Nome: ");
                String name = scanner.nextLine().trim();

                System.out.print("Senha: ");
                String password = scanner.nextLine().trim();

                if (name.isEmpty() || password.isEmpty()) {
                    System.out.println("Nome e senha nao podem ser vazios. Registro ignorado.\n");
                } else {
                    // grava uma pessoa por linha, separando os campos por ";"
                    saida.printf("%s;%s%n", name, password);
                    System.out.println("Cadastro gravado em " + ARQUIVO + "\n");
                }

                System.out.print("Cadastrar outra pessoa? (S/N): ");
                continuar = scanner.nextLine().trim();
                System.out.println();
            } while (continuar.equalsIgnoreCase("S"));

            System.out.println("Encerrado. Dados salvos em " + ARQUIVO);
        } catch (IOException e) {
            System.out.printf("Erro ao gravar o arquivo: %s%n", e.getMessage());
        }

        scanner.close();
    }
}
