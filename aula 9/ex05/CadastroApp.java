package ex05;

import java.util.Scanner;

/*- Crie a classe CadastroApp, com o método main(), e que
tenha um menu que insira um cliente, remova um cliente,
altere um cliente, liste os dados de um cliente e liste os dados
de todos os clientes */

public class CadastroApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BancoDeClientes banco = new BancoDeClientes();

        int opcao;

        do {
            System.out.println("===== MENU CADASTRO DE CLIENTES =====");
            System.out.println("1 - Inserir cliente");
            System.out.println("2 - Remover cliente");
            System.out.println("3 - Alterar cliente");
            System.out.println("4 - Listar dados de um cliente");
            System.out.println("5 - Listar todos os clientes");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    // Inserir cliente
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o telefone: ");
                    String fone = scanner.nextLine();

                    System.out.print("Digite o ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Cliente novoCliente = new Cliente(nome, fone, id);
                    banco.inserirCliente(novoCliente);

                    System.out.println("Cliente inserido com sucesso!\n");
                    break;

                case 2:
                    System.out.print("Digite o ID do cliente a remover: ");
                    int idRemover = scanner.nextInt();
                    scanner.nextLine();

                    Cliente clienteRemover = null;

                    for (Cliente c : banco.getClientes()) {
                        if (c.getId() == idRemover) {
                            clienteRemover = c;
                            break;
                        }
                    }

                    if (clienteRemover != null) {
                        banco.removerCliente(clienteRemover);
                        System.out.println("Cliente removido com sucesso!\n");
                    } else {
                        System.out.println("Cliente não encontrado!\n");
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do cliente a alterar: ");
                    int idAlterar = scanner.nextInt();
                    scanner.nextLine();

                    Cliente clienteAlterar = null;

                    for (Cliente c : banco.getClientes()) {
                        if (c.getId() == idAlterar) {
                            clienteAlterar = c;
                            break;
                        }
                    }

                    if (clienteAlterar != null) {
                        System.out.print("Novo nome: ");
                        String novoNome = scanner.nextLine();

                        System.out.print("Novo telefone: ");
                        String novoFone = scanner.nextLine();

                        System.out.print("Novo ID: ");
                        int novoId = scanner.nextInt();
                        scanner.nextLine();

                        banco.alterarCliente(clienteAlterar, novoNome, novoFone, novoId);

                        System.out.println("Cliente alterado com sucesso!\n");
                    } else {
                        System.out.println("Cliente não encontrado!\n");
                    }
                    break;

                case 4:
                    System.out.print("Digite o ID do cliente: ");
                    int idBuscar = scanner.nextInt();
                    scanner.nextLine();

                    Cliente clienteBuscar = null;

                    for (Cliente c : banco.getClientes()) {
                        if (c.getId() == idBuscar) {
                            clienteBuscar = c;
                            break;
                        }
                    }

                    if (clienteBuscar != null) {
                        banco.listarDadosDeUmCliente(clienteBuscar);
                        System.out.println();
                    } else {
                        System.out.println("Cliente não encontrado!\n");
                    }
                    break;

                case 5:
                    System.out.println("===== LISTA DE CLIENTES =====");
                    banco.listarDadosDeTodosOsClientes();
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida!\n");
            }

        } while (opcao != 0);

        scanner.close();
    }
}