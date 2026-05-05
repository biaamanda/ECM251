package ex03;

import java.util.Scanner;

public class AppBloco {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BlocoDeNotas bloco = new BlocoDeNotas();

        int opcao;

        do {
            System.out.println("\n===== MENU BLOCO DE NOTAS =====");
            System.out.println("1) Inserir uma nota");
            System.out.println("2) Remover uma nota");
            System.out.println("3) Alterar uma nota");
            System.out.println("4) Listar todas as notas");
            System.out.println("5) Sair do sistema");
            System.out.print("Escolha uma opçao: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {

                case 1:
                    System.out.print("Digite a nova nota: ");
                    String novaNota = scanner.nextLine();

                    bloco.inserirNota(novaNota);

                    System.out.println("Nota adicionada com sucesso!");
                    break;

                case 2:
                    System.out.print("Digite a nota que deseja remover: ");
                    String notaRemover = scanner.nextLine();

                    if (bloco.removerNota(notaRemover)) {
                        System.out.println("Nota removida com sucesso!");
                    } else {
                        System.out.println("Nota não encontrada.");
                    }
                    break;

                case 3:
                    System.out.print("Digite a nota antiga: ");
                    String notaAntiga = scanner.nextLine();

                    System.out.print("Digite a nova nota: ");
                    String notaNova = scanner.nextLine();

                    if (bloco.alterarNota(notaAntiga, notaNova)) {
                        System.out.println("Nota alterada com sucesso!");
                    } else {
                        System.out.println("Nota não encontrada.");
                    }
                    break;

                case 4:
                    bloco.imprimirNotas();
                    break;

                case 5:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 5);

        scanner.close();
    }
}