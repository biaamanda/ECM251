package ex03;

import java.util.ArrayList;

/*Crie a classe BlocoDeNotas que possui como atributo um
ArrayList<String> chamado notas. Crie métodos para inserir,
remover e buscar notas. Crie um método que imprima todas as notas;
Crie a classe AppBloco, com o método main() e um menu que:
1) Insira uma nota;
2) Remova uma nota;
3) Altere uma nota;
4) Listar todas as notas;
5) Saia do sistema */

public class BlocoDeNotas {
    ArrayList<String> notas;

    public BlocoDeNotas() {
        this.notas = new ArrayList<>();
    }

    public void inserirNota(String nota) {
        notas.add(nota);
    }

    public boolean removerNota(String nota) {
        return notas.remove(nota);
    }

    public int buscarNota(String nota) {
        return notas.indexOf(nota);
    }

    public boolean alterarNota(String notaAntiga, String notaNova) {
        int posicao = buscarNota(notaAntiga);

        if (posicao != -1) {
            notas.set(posicao, notaNova);
            return true;
        }

        return false;
    }

    public void imprimirNotas() {
        if (notas.isEmpty()) {
            System.out.println("Nenhuma nota cadastrada.");
            return;
        }

        System.out.println("\n===== BLOCO DE NOTAS =====");

        for (int i = 0; i < notas.size(); i++) {
            System.out.println((i + 1) + ". " + notas.get(i));
        }
    }
}
