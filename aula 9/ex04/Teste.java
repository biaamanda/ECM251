package ex04;

import javax.swing.JOptionPane;

/*- Para testar seu sistema fez uma classe Teste com o método
main() que, usando o JOptionPane, possui um laço com as opções:
1. adiciona caixa;
2. remove caixa;
3. procura caixa;
4. muda caixa;
5. lista mais pesadas que 10.0; e
6. sair */

public class Teste {
    public static void main(String[] args) {
        Deposito deposito = new Deposito();
        int opcao;

        do {
            String menu = """
                    1. Adicionar caixa
                    2. Remover caixa
                    3. Procurar caixa
                    4. Mudar caixa
                    5. Listar mais pesadas que 10.0
                    6. Sair
                    """;

            opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcao) {

                case 1:
                    String corredor = JOptionPane.showInputDialog("Digite o corredor:");
                    int posicao = Integer.parseInt(
                            JOptionPane.showInputDialog("Digite a posição:")
                    );
                    double peso = Double.parseDouble(
                            JOptionPane.showInputDialog("Digite o peso:")
                    );
                    String dono = JOptionPane.showInputDialog("Digite o nome do dono:");

                    deposito.adicionarCaixa(
                            new Caixa(corredor, posicao, peso, dono)
                    );

                    JOptionPane.showMessageDialog(null, "Caixa adicionada!");
                    break;

                case 2:
                    String donoRemover = JOptionPane.showInputDialog("Digite o dono da caixa:");

                    if (deposito.removerCaixa(donoRemover)) {
                        JOptionPane.showMessageDialog(null, "Caixa removida!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Caixa não encontrada.");
                    }
                    break;

                case 3:
                    String donoBuscar = JOptionPane.showInputDialog("Digite o dono:");
                    JOptionPane.showMessageDialog(
                            null,
                            deposito.dadosCaixa(donoBuscar)
                    );
                    break;

                case 4:
                    String donoMudar = JOptionPane.showInputDialog("Digite o dono:");
                    String novoCorredor = JOptionPane.showInputDialog("Novo corredor:");
                    int novaPosicao = Integer.parseInt(
                            JOptionPane.showInputDialog("Nova posição:")
                    );

                    if (deposito.mudarCaixa(donoMudar, novoCorredor, novaPosicao)) {
                        JOptionPane.showMessageDialog(null, "Caixa alterada!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Caixa não encontrada.");
                    }
                    break;

                case 5:
                    Caixa[] pesadas = deposito.caixasMaisPesadasQue(10.0);

                    if (pesadas.length == 0) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Nenhuma caixa pesa mais que 10.0"
                        );
                    } else {
                        StringBuilder lista = new StringBuilder("Caixas mais pesadas:\n\n");

                        for (Caixa c : pesadas) {
                            lista.append("Dono: ").append(c.getDono())
                                 .append(" | Corredor: ").append(c.getCorredor())
                                 .append(" | Posição: ").append(c.getPosicao())
                                 .append(" | Peso: ").append(c.getPeso())
                                 .append("\n");
                        }

                        JOptionPane.showMessageDialog(null, lista.toString());
                    }
                    break;

                case 6:
                    JOptionPane.showMessageDialog(null, "Encerrando sistema...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }

        } while (opcao != 6);
    }
}