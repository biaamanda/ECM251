/*Faça uma calculadora com as 4 operações básicas, com botões
de 0 a 9 para a entrada dos dígitos e um único mostrador que
mostra os números digitados e o resultado das operações.
Aceite apenas inteiros e faça as operações com inteiros. Use a
imagem abaixo como exemplo de tela: */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaCalculadora extends JFrame implements ActionListener {

    private JTextField display;
    private int valorAnterior = 0;
    private String operacao = "";
    private boolean novaEntrada = true;

    public TelaCalculadora() {
        super("Calculadora");

        // DISPLAY
        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 28));

        // PAINEL PRINCIPAL
        JPanel painel =
                new JPanel(new BorderLayout(5, 5));

        painel.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        painel.add(display, BorderLayout.NORTH);

        // GRADE DOS BOTÕES
        JPanel grade =
                new JPanel(new GridLayout(4, 4, 5, 5));

        // Linha 1
        grade.add(criarBotaoDigito("7"));
        grade.add(criarBotaoDigito("8"));
        grade.add(criarBotaoDigito("9"));
        grade.add(criarBotaoOperacao("+"));

        // Linha 2
        grade.add(criarBotaoDigito("4"));
        grade.add(criarBotaoDigito("5"));
        grade.add(criarBotaoDigito("6"));
        grade.add(criarBotaoOperacao("-"));

        // Linha 3
        grade.add(criarBotaoDigito("1"));
        grade.add(criarBotaoDigito("2"));
        grade.add(criarBotaoDigito("3"));
        grade.add(criarBotaoOperacao("*"));

        // Linha 4
        grade.add(criarBotaoDigito("0"));

        JButton botaoC = new JButton("C");
        botaoC.addActionListener(this);
        grade.add(botaoC);

        grade.add(criarBotaoOperacao("="));

        grade.add(criarBotaoOperacao("/"));

        painel.add(grade, BorderLayout.CENTER);

        add(painel);

        // JANELA
        setSize(300, 400);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        setVisible(true);
    }

    // BOTÃO DE NÚMERO
    private JButton criarBotaoDigito(String texto) {

        JButton botao = new JButton(texto);

        botao.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        botao.addActionListener(this);
        return botao;
    }

    // BOTÃO DE OPERAÇÃO
    private JButton criarBotaoOperacao(String texto) {

        JButton botao = new JButton(texto);

        botao.setFont(new Font("Arial", Font.BOLD, 22));

        botao.addActionListener(this);
        return botao;
    }

    // calculos
    private int calcular(
            int a,
            int b,
            String operador
    ) {

        switch (operador) {

            case "+":
                return a + b;

            case "-":
                return a - b;

            case "*":
                return a * b;

            case "/":
                if (b != 0) {
                    return a / b;
                } else {
                    JOptionPane.showMessageDialog(this,"Divisão por zero");
                    return 0;
                }

            default:
                return b;
        }
    }

    // AÇÕES DOS BOTÕES
    public void actionPerformed(ActionEvent e) {

        String comando =
                e.getActionCommand();

        // NÚMEROS
        if (comando.matches("[0-9]")) {

            if (novaEntrada) {
                display.setText(comando);
                novaEntrada = false;

            } else {
                display.setText(display.getText() + comando);
            }
        }

        else if (comando.equals("C")) {
            display.setText("0");
            valorAnterior = 0;
            operacao = "";
            novaEntrada = true;
        }

        else if (comando.equals("=")) {

            int valorAtual = Integer.parseInt(display.getText());

            int resultado =
                    calcular(
                            valorAnterior,
                            valorAtual,
                            operacao
                    );

            display.setText(String.valueOf(resultado));

            novaEntrada = true;
        }

        // operacoes
        else {
            valorAnterior = Integer.parseInt(display.getText());

            operacao = comando;
            novaEntrada = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCalculadora());
    }
}