/* Use as classes Relogio e Mostrador das aulas anteriores. Crie
uma tela que apresenta o mostrador do relógio (hora e
minuto) em um JLabel e que tenha três botões: TicTac, que
aumenta um minuto a cada clique, Hora, que acerta a hora (0
a 23) e Minuto, que acerta o minuto (0 a 59). Use sempre o
método mostra() da classe Relogio para atualizar o mostrador
da tela. Consulte a documentação do Java para aprender a
aumentar o tamanho e a cor da fonte */

import javax.swing.*;
import java.awt.*;

class Mostrador {

    private int valor;
    private int limite;

    public Mostrador(int limite) {
        this.limite = limite;
        valor = 0;
    }

    public void setValor(int valor) {

        if (valor >= 0 && valor < limite) {
            this.valor = valor;
        }
    }

    public int getValor() {
        return valor;
    }

    public void incrementar() {

        valor++;

        if (valor >= limite) {
            valor = 0;
        }
    }

    public String mostra() {

        if (valor < 10) {
            return "0" + valor;
        }

        return "" + valor;
    }
}

class Relogio {

    private Mostrador horas;
    private Mostrador minutos;

    public Relogio() {

        horas = new Mostrador(24);
        minutos = new Mostrador(60);
    }

    public void ticTac() {

        minutos.incrementar();

        if (minutos.getValor() == 0) {
            horas.incrementar();
        }
    }

    public void setHora(int hora) {
        horas.setValor(hora);
    }

    public void setMinuto(int minuto) {
        minutos.setValor(minuto);
    }

    public String mostra() {

        return horas.mostra() +
               ":" +
               minutos.mostra();
    }
}

public class ex02 extends JFrame {

    private Relogio relogio;
    private JLabel mostrador;

    public ex02() {

        // relógio
        relogio = new Relogio();

        // janela
        setTitle("Exercício 2 - Relógio");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // mostrador
        mostrador = new JLabel(
                relogio.mostra(),
                SwingConstants.CENTER
        );

        mostrador.setFont(
                new Font("Arial", Font.BOLD, 40)
        );

        mostrador.setForeground(Color.BLUE);

        // botões
        JButton botaoTicTac =
                new JButton("TicTac");

        JButton botaoHora =
                new JButton("Hora");

        JButton botaoMinuto =
                new JButton("Minuto");

        // TIC TAC
        botaoTicTac.addActionListener(e -> {

            relogio.ticTac();

            atualizarMostrador();
        });

        // HORA
        botaoHora.addActionListener(e -> {

            String entrada =
                    JOptionPane.showInputDialog(
                            this,
                            "Digite a hora (0-23):"
                    );

            try {

                int hora =
                        Integer.parseInt(entrada);

                if (hora >= 0 && hora <= 23) {

                    relogio.setHora(hora);

                    atualizarMostrador();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Hora inválida!"
                    );
                }

            } catch (Exception erro) {

                JOptionPane.showMessageDialog(
                        this,
                        "Entrada inválida!"
                );
            }
        });

        // MINUTO
        botaoMinuto.addActionListener(e -> {

            String entrada =
                    JOptionPane.showInputDialog(
                            this,
                            "Digite o minuto (0-59):"
                    );

            try {

                int minuto =
                        Integer.parseInt(entrada);

                if (minuto >= 0 && minuto <= 59) {

                    relogio.setMinuto(minuto);

                    atualizarMostrador();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Minuto inválido!"
                    );
                }

            } catch (Exception erro) {

                JOptionPane.showMessageDialog(
                        this,
                        "Entrada inválida!"
                );
            }
        });

        // painel botões
        JPanel painelBotoes =
                new JPanel();

        painelBotoes.add(botaoTicTac);
        painelBotoes.add(botaoHora);
        painelBotoes.add(botaoMinuto);

        // layout
        setLayout(new BorderLayout());

        add(mostrador, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    // atualiza mostrador
    private void atualizarMostrador() {

        mostrador.setText(
                relogio.mostra()
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new ex02()
        );
    }
}