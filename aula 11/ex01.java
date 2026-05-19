/* Crie uma tela com um campo texto, contendo a etiqueta
Texto, um botão Mostrar, um botão Limpar e um botão Sair.
O botão Mostrar, ao ser clicado, mostra o conteúdo do campo
Texto em um JOptionPane(). O botão Limpar limpa o campo
Texto e o botão Sair sai do programa. */

import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;

public class ex01 extends JFrame {

    private JTextField campoTexto;

    public ex01() {
        setTitle("Exercício 1");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Configuração do layout
        setLayout(new FlowLayout());
        //painel principal
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        //add(painel);

        // etiqueta texto
        JLabel etiqueta = new JLabel("Texto:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        painel.add(etiqueta, gbc);

        // campo texto

        // botao mostrar
        JButton botaoMostrar = new JButton("Mostrar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        painel.add(botaoMostrar, gbc);

        // botao limpar
        JButton botaoLimpar = new JButton("Limpar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        painel.add(botaoLimpar, gbc);

        // botao sair
        JButton botaoSair = new JButton("Sair");
        gbc.gridx = 2;
        gbc.gridy = 1;
        painel.add(botaoSair, gbc);

        // acoes dos botoes
        botaoMostrar.addActionListener(e -> {
            String texto = campoTexto.getText();
            JOptionPane.showMessageDialog(this, texto, "Conteúdo do Campo Texto", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoLimpar.addActionListener(e -> {
            campoTexto.setText("");
            campoTexto.requestFocus();
        });

        botaoSair.addActionListener(e -> System.exit(0));

        add(painel);
        setVisible(true);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ex01());
    }

}

