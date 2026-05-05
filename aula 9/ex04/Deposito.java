package ex04;

import java.util.ArrayList;

/*- Depois criou a classe Deposito, que contém um ArrayList de
caixas. Fez um método para adicionar caixas e um para
remover (pelo dono). Fez um método que encontra uma caixa
pelo dono, retornando sua posição no Arraylist (ou -1 se não
achar). E um método para mudar o corredor e a posição de
uma caixa, que encontra a caixa pelo dono e altera seu
atributos. Ele fez também um método que retorna um vetor
com a(s) caixa(s) que pesam mais do que um valor passado
por parâmetro; */

public class Deposito {
    private ArrayList<Caixa> caixas;

    public Deposito() {
        this.caixas = new ArrayList<>();
    }

    // 1. Adicionar caixa
    public void adicionarCaixa(Caixa caixa) {
        caixas.add(caixa);
    }

    // 2. Encontrar caixa pelo dono (retorna posição ou -1)
    public int encontrarCaixa(String dono) {
        for (int i = 0; i < caixas.size(); i++) {
            if (caixas.get(i).getDono().equalsIgnoreCase(dono)) {
                return i;
            }
        }
        return -1;
    }

    // 3. Remover caixa pelo dono
    public boolean removerCaixa(String dono) {
        int pos = encontrarCaixa(dono);

        if (pos != -1) {
            caixas.remove(pos);
            return true;
        }

        return false;
    }

    // 4. Mudar corredor e posição
    public boolean mudarCaixa(String dono, String novoCorredor, int novaPosicao) {
        int pos = encontrarCaixa(dono);

        if (pos != -1) {
            caixas.get(pos).setCorredor(novoCorredor);
            caixas.get(pos).setPosicao(novaPosicao);
            return true;
        }

        return false;
    }

    // 5. Retornar caixas mais pesadas que valor
    public Caixa[] caixasMaisPesadasQue(double pesoMinimo) {
        ArrayList<Caixa> pesadas = new ArrayList<>();

        for (Caixa caixa : caixas) {
            if (caixa.getPeso() > pesoMinimo) {
                pesadas.add(caixa);
            }
        }

        return pesadas.toArray(new Caixa[0]);
    }

    // Método auxiliar para mostrar dados
    public String dadosCaixa(String dono) {
        int pos = encontrarCaixa(dono);

        if (pos != -1) {
            Caixa c = caixas.get(pos);

            return "Dono: " + c.getDono() +
                   "\nCorredor: " + c.getCorredor() +
                   "\nPosição: " + c.getPosicao() +
                   "\nPeso: " + c.getPeso();
        }

        return "Caixa não encontrada.";
    }
}