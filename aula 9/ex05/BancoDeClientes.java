package ex05;

import java.util.ArrayList;

/*Crie a classe BancoDeClientes com um atributo privado do
tipo ArrayList<Cliente> chamado clientes. Crie métodos para
inserir um cliente, remover um cliente, alterar um cliente,
listar os dados de um cliente e listar os dados de todos os clientes; */
public class BancoDeClientes {
    private ArrayList<Cliente> clientes;

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public BancoDeClientes() {
        this.clientes = new ArrayList<>();
    }

    public void inserirCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void removerCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    public void alterarCliente(Cliente cliente, String novoNome, String novoFone, int novoId) {
        int index = clientes.indexOf(cliente);
        if (index != -1) {
            clientes.remove(index);
            clientes.add(index, new Cliente(novoNome, novoFone, novoId));
        }
    }

    public void listarDadosDeUmCliente(Cliente cliente) {
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Telefone: " + cliente.getFone());
        System.out.println("ID: " + cliente.getId());
    }

    public void listarDadosDeTodosOsClientes() {
        for (Cliente cliente : clientes) {
            listarDadosDeUmCliente(cliente);
            System.out.println();
        }
    }
}
