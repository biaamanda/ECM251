/*1. Utilizando-se de JMenu e JMenuItem, desenvolver um
aplicativo em Java capaz de atender às seguintes solicitações:
a. Criar um menu denominado Vetor. Ao ser selecionado,
apresente os itens denominados Dimensiona, Digita e Apresenta.
• No item Dimensiona será solicitado o tamanho de um vetor do tipo double;
• No item Digita serão solicitados todos os elementos deste vetor;
• No item Apresenta serão apresentados todos os elementos deste vetor.
b. Criar um menu denominado PROBEST. Ao ser selecionado,
apresente os itens denominados Média, Desvio Padrão,
Variância, Mediana, Coef. Assimetria e Coef. Variação.
• Ao ser selecionado cada um destes itens, deverá ser
apresentado o seu respectivo cálculo estatístico, sobre o vetor
digitado no menu Vetor;
c. Criar um menu denominado CDI. Ao ser selecionado,
apresente os itens denominados Função e Derivada.
• Ao ser selecionado cada um destes itens, deverá ser
apresentado o seu respectivo cálculo, sobre o vetor digitado no menu Vetor;
d. Criar um menu denominado ALGESD. Ao ser selecionado,
apresente os itens denominados Ordenação e Busca.
• Caso seja selecionado o item Ordenação, deverão ser
apresentados os itens Trocas, Seleção, Inserção, Quick e Merge.
• Ao ser selecionado cada um destes itens, deverá ser realizada
a ordenação do vetor digitado no menu Vetor, através do
respectivo algoritmo de ordenação escolhido.
• Caso seja selecionado o item Busca, deverão ser apresentados
os itens Linear Iterativa, Linear Recursiva, Binária Iterativa e Binária Recursiva;
• Ao ser selecionado cada um destes itens, deverá ser solicitado
um valor chave para a busca e, a seguir, realizada a busca
desse valor chave no vetor digitado no menu Vetor, através
do respectivo algoritmo de busca;
• Lembrar que para realizar qualquer uma das buscas binárias
oferecidas, o vetor deverá estar ordenado antecipadamente
(solicitar a escolha do algoritmo para realizar a prévia ordenação). */

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class Ex01 extends JFrame {

    private double vetor[];
    private int size;

    public Ex01() {

        JMenuBar barra = new JMenuBar();
        //menu VETOR
        JMenu menuVetor = new JMenu("Vetor");

        JMenuItem dimensiona = new JMenuItem("Dimensiona");
        JMenuItem digita = new JMenuItem("Digita");
        JMenuItem apresenta = new JMenuItem("Apresenta");

        //menu PROBEST
        JMenu probest = new JMenu("PROBEST");

        JMenuItem media = new JMenuItem("Média");
        JMenuItem desvioPadrao = new JMenuItem("Desvio Padrão");
        JMenuItem variancia = new JMenuItem("Variância");
        JMenuItem mediana = new JMenuItem("Mediana");
        JMenuItem coefAssimetria = new JMenuItem("Coef. Assimetria");
        JMenuItem coefVariacao = new JMenuItem("Coef. Variação");

        //menu CDI
        JMenu cdi = new JMenu("CDI");

        JMenuItem funcao = new JMenuItem("Função");
        JMenuItem derivada = new JMenuItem("Derivada");

        //menu ALGESD
        JMenu algesd = new JMenu("ALGESD");

        JMenu ordenacao = new JMenu("Ordenação");

        JMenuItem trocas = new JMenuItem("Trocas");
        JMenuItem selecao = new JMenuItem("Seleção");
        JMenuItem insercao = new JMenuItem("Inserção");
        JMenuItem quick = new JMenuItem("Quick");
        JMenuItem merge = new JMenuItem("Merge");

        JMenu busca = new JMenu("Busca");

        JMenuItem linear = new JMenuItem("Linear");
        JMenuItem binaria = new JMenuItem("Binária");

        setTitle("Exercício 01");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setJMenuBar(barra);

        barra.add(menuVetor);
        barra.add(probest);
        barra.add(cdi);
        barra.add(algesd);

        //adicionando menus
        menuVetor.add(dimensiona);
        menuVetor.add(digita);
        menuVetor.add(apresenta);

        probest.add(media);
        probest.add(desvioPadrao);
        probest.add(variancia);
        probest.add(mediana);
        probest.add(coefAssimetria);
        probest.add(coefVariacao);

        cdi.add(funcao);
        cdi.add(derivada);

        algesd.add(ordenacao);
        algesd.add(busca);

        ordenacao.add(trocas);
        ordenacao.add(selecao);
        ordenacao.add(insercao);
        ordenacao.add(quick);
        ordenacao.add(merge);

        busca.add(linear);
        busca.add(binaria);
        

        //dimensionando vetor
        dimensiona.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                size = Integer.parseInt(JOptionPane.showInputDialog("Digite o tamanho do vetor:"));

                vetor = new double[size];

                JOptionPane.showMessageDialog(null, "Vetor dimensionado com sucesso!");
            }
        });

        digita.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (vetor == null) {
                    JOptionPane.showMessageDialog(null, "Primeiro dimensione o vetor");
                    return;
                }
                for (int i = 0; i < vetor.length; i++) {
                    vetor[i] = Double.parseDouble(JOptionPane.showInputDialog("Digite o elemento " + (i + 1) + ":"));
                }

                JOptionPane.showMessageDialog(null, "Vetor digitado com sucesso!");
            };

        });

        apresenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (vetor == null) {
                    JOptionPane.showMessageDialog(null, "Primeiro dimensione o vetor");
                    return;
                }

                String saida = "";
                
                for (int i = 0; i < vetor.length; i++) {
                    saida += vetor[i] + "\n";
                }
                JOptionPane.showMessageDialog(null, saida);
            }
        });

        media.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double soma = 0;

                for (double v : vetor) {
                    soma += v;
                }

                double media = soma / vetor.length;
                JOptionPane.showMessageDialog(null, "A média é: " + media);
            }
        });

         variancia.addActionListener(e -> {
            double soma = 0;

            for (double v : vetor)
                soma += v;

            double mediaVet =
                    soma / vetor.length;

            double var = 0;

            for (double v : vetor)
                var += Math.pow(
                        v - mediaVet,
                        2
                );

            var /= vetor.length;

            JOptionPane.showMessageDialog(null, "Variância = " + var);

        });

        desvioPadrao.addActionListener(e -> {
            double soma = 0;

            for (double v : vetor)
                soma += v;

            double mediaVet =
                    soma / vetor.length;

            double var = 0;

            for (double v : vetor)
                var += Math.pow(
                        v - mediaVet,
                        2
                );

            var /= vetor.length;

            JOptionPane.showMessageDialog( null, "Desvio = " + Math.sqrt(var));
        });

        mediana.addActionListener(e -> {
            double[] copia =
                    vetor.clone();

            Arrays.sort(copia);

            double med;

            if (copia.length % 2 == 0) {

                med = (copia[copia.length / 2]
                    + copia[copia.length / 2 - 1])
                    / 2;

            } else {
                med = copia[copia.length / 2];
            }

            JOptionPane.showMessageDialog(null, "Mediana = " + med);
        });

        funcao.addActionListener(e -> {
            String s = "";

            for (double x : vetor)
                s += "f(" + x + ") = "
                        + (x * x) + "\n";

            JOptionPane.showMessageDialog(null, s);
        });

        derivada.addActionListener(e -> {
            String s = "";

            for (double x : vetor)
                s += "f'(" + x + ") = "
                        + (2 * x) + "\n";

            JOptionPane.showMessageDialog(null, s);
        });

        trocas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < vetor.length - 1; i++) {
                    for (int j = i + 1; j < vetor.length - 1 - i; j++) {
                        if (vetor[i] > vetor[j]) {
                            double aux = vetor[i];
                            vetor[i] = vetor[j];
                            vetor[j] = aux;
                        }
                    }
                }

                JOptionPane.showMessageDialog(null, "Vetor ordenado!");
            }

        });

        selecao.addActionListener(e -> {
            for (int i = 0;
                 i < vetor.length - 1;
                 i++) {

                int menor = i;

                for (int j = i + 1;
                     j < vetor.length;
                     j++) {

                    if (vetor[j]
                            < vetor[menor])
                        menor = j;
                }

                double aux = vetor[i];
                vetor[i] = vetor[menor];
                vetor[menor] = aux;
            }

            JOptionPane.showMessageDialog( null, "Ordenado!");
        });

        // INSERTION
        insercao.addActionListener(e -> {

            for (int i = 1;
                 i < vetor.length;
                 i++) {

                double chave =
                        vetor[i];

                int j = i - 1;

                while (j >= 0 &&
                        vetor[j] > chave) {

                    vetor[j + 1] =
                            vetor[j];

                    j--;
                }

                vetor[j + 1] =
                        chave;
            }

            JOptionPane.showMessageDialog(null,  "Ordenado!");
        });

        // BUSCA LINEAR
        linear.addActionListener(e -> {
            double chave =
                    Double.parseDouble(
                            JOptionPane.showInputDialog("Valor:")
                    );

            for (int i = 0;
                 i < vetor.length;
                 i++) {

                if (vetor[i]
                        == chave) {
                    JOptionPane.showMessageDialog( null, "Encontrado na posição " + i);

                    return;
                }
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Não encontrado"
            );
        });

        // BUSCA BINÁRIA
        binaria.addActionListener(e -> {

            double chave =
                    Double.parseDouble(
                            JOptionPane
                                    .showInputDialog(
                                            "Valor:"
                                    )
                    );

            int ini = 0;
            int fim = vetor.length - 1;

            while (ini <= fim) {

                int meio =
                        (ini + fim) / 2;

                if (vetor[meio]
                        == chave) {

                    JOptionPane.showMessageDialog( null, "Encontrado na posição " + meio);

                    return;
                }

                if (chave <
                        vetor[meio])
                    fim = meio - 1;

                else
                    ini = meio + 1;
            }

            JOptionPane.showMessageDialog(null, "Não encontrado");
        });

        setTitle("Exercício 01");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ex01());
    }
}