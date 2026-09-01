/*Desenvolver, utilizando Layouts como interface com o usuário,
um programa que receba RA, NOME, SOBRENOME, P1, P2, P3
e P4 por pessoa cadastrada, digitados pelo usuário, e que,
posteriormente, sejam, pelo aplicativo Java, gravados em um
arquivo de texto */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ex03 extends JFrame implements ActionListener {
    // arquivo de texto com o cadastro dos alunos
    private static final String ARQUIVO = "alunos.txt";

    private final JTextField txtRa = new JTextField(10);
    private final JTextField txtNome = new JTextField(10);
    private final JTextField txtSobrenome = new JTextField(10);
    private final JTextField txtP1 = new JTextField(10);
    private final JTextField txtP2 = new JTextField(10);
    private final JTextField txtP3 = new JTextField(10);
    private final JTextField txtP4 = new JTextField(10);
    private final JButton btGravar = new JButton("Gravar");

    public Ex03() {
        super("Cadastro de Alunos");
        setLayout(new BorderLayout(5, 5));

        // painel central: rotulos e campos organizados em grade
        JPanel campos = new JPanel(new GridLayout(7, 2, 5, 5));
        campos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); 
        campos.add(new JLabel("RA: "));          campos.add(txtRa);
        campos.add(new JLabel("Nome: "));        campos.add(txtNome);
        campos.add(new JLabel("Sobrenome: "));   campos.add(txtSobrenome);
        campos.add(new JLabel("P1: "));          campos.add(txtP1);
        campos.add(new JLabel("P2: "));          campos.add(txtP2);
        campos.add(new JLabel("P3: "));          campos.add(txtP3);
        campos.add(new JLabel("P4: "));          campos.add(txtP4);
        add(campos, BorderLayout.CENTER);

        // painel inferior: botao centralizado
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.add(btGravar);
        add(painelBotao, BorderLayout.SOUTH);

        btGravar.addActionListener(this);

        setSize(300, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ra = txtRa.getText().trim();
        String nome = txtNome.getText().trim();
        String sobrenome = txtSobrenome.getText().trim();

        if (ra.isEmpty() || nome.isEmpty() || sobrenome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Preencha RA, Nome e Sobrenome.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double p1, p2, p3, p4;
        try {
            // converte as notas; se o texto nao for numerico cai no catch
            p1 = Double.parseDouble(txtP1.getText().trim().replace(",", "."));
            p2 = Double.parseDouble(txtP2.getText().trim().replace(",", "."));
            p3 = Double.parseDouble(txtP3.getText().trim().replace(",", "."));
            p4 = Double.parseDouble(txtP4.getText().trim().replace(",", "."));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "As notas P1, P2, P3 e P4 devem ser numeros.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // grava em modo append: uma pessoa por linha, campos separados por ";"
        try (PrintWriter saida = new PrintWriter(new FileWriter(ARQUIVO, true))) {
            saida.printf("%s;%s;%s;%.2f;%.2f;%.2f;%.2f%n",
                ra, nome, sobrenome, p1, p2, p3, p4);
            JOptionPane.showMessageDialog(this,
                "Aluno gravado em " + ARQUIVO);
            limparCampos();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Erro ao gravar o arquivo: " + ex.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtRa.setText("");
        txtNome.setText("");
        txtSobrenome.setText("");
        txtP1.setText("");
        txtP2.setText("");
        txtP3.setText("");
        txtP4.setText("");
        txtRa.requestFocus();
    }

    public static void main(String[] args) {
        Ex03 tela = new Ex03();
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
