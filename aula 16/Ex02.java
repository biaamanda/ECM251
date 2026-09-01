/*Desenvolver, utilizando o console como interface com o
usuário, um programa que receba um NOME e uma SENHA,
digitados pelo usuário, para que sejam validados com o NOME
e a SENHA lidos no arquivo de texto gerado pelo aplicativo do
item 1, anterior.
• Caso os Nomes e as Senhas sejam iguais, o aplicativo
deverá apresentar a resposta “LOGIN REALIZADO COM
SUCESSO”;
• Caso as informações não coincidam, o aplicativo deverá
apresentar a resposta “LOGIN E/OU SENHA ERRADO(S)”. */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ex02 {
    // mesmo arquivo gravado pelo Ex01
    private static final String ARQUIVO = "usuarios.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome: ");
        String name = scanner.nextLine().trim();

        System.out.print("Senha: ");
        String password = scanner.nextLine().trim();

        scanner.close();

        System.out.println();
        System.out.println(validarLogin(name, password)
            ? "LOGIN REALIZADO COM SUCESSO"
            : "LOGIN E/OU SENHA ERRADO(S)");
    }

    private static boolean validarLogin(String name, String password) {
        try (Scanner arquivo = new Scanner(new File(ARQUIVO))) {
            while (arquivo.hasNextLine()) {
                String linha = arquivo.nextLine();
                String[] campos = linha.split(";");

                if (campos.length >= 2
                        && campos[0].equals(name)
                        && campos[1].equals(password)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Arquivo %s nao encontrado. Cadastre usuarios no Ex01 primeiro.%n", ARQUIVO);
        }
        return false;
    }
}
