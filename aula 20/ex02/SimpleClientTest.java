/* Criar uma interface grafica para a classe SimpleClientTest.java, atraves dos modelos de layout
 * estudados anteriormente nesta disciplina (flowlayout, borderlayout, gridlayout ou os
 * automaticamente gerados pela IDE NetBeans), eliminando toda e qualquer forma de comunicacao com
 * o usuario atraves do console e/ou por interface Swing como foi fornecida, criando nessa nova
 * interface grafica os campos especificos para as mensagens a serem enviadas ao servidor e para as
 * mensagens de status da comunicacao, alem da inclusao dos botoes e suas funcionalidades para
 * Enviar, Limpar e Sair, na propria interface.
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

// Cliente com interface grafica propria (BorderLayout + GridLayout + FlowLayout).
// Toda a comunicacao com o usuario acontece na janela: nada no console, nada de JOptionPane.
public class SimpleClientTest extends JFrame {
    private Socket cliente;     // Comunicacao com o servidor
    private PrintStream saida;  // Canal de saida para o servidor

    private final JTextField campoMensagem = new JTextField(30);
    private final JTextArea areaMensagens = new JTextArea(12, 40);
    private final JTextField campoStatus = new JTextField();
    private final JButton botaoEnviar = new JButton("Enviar");
    private final JButton botaoLimpar = new JButton("Limpar");
    private final JButton botaoSair = new JButton("Sair");

    public SimpleClientTest() {
        super("Cliente - SimpleClientTest");
        setLayout(new BorderLayout(5, 5));

        // NORTE: campo para digitar a mensagem
        JPanel painelMensagem = new JPanel(new BorderLayout(5, 5));
        painelMensagem.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        painelMensagem.add(new JLabel("Mensagem:"), BorderLayout.WEST);
        painelMensagem.add(campoMensagem, BorderLayout.CENTER);
        add(painelMensagem, BorderLayout.NORTH);

        // CENTRO: historico das mensagens enviadas
        areaMensagens.setEditable(false);
        areaMensagens.setLineWrap(true);
        JScrollPane rolagem = new JScrollPane(areaMensagens);
        rolagem.setBorder(BorderFactory.createTitledBorder("Mensagens enviadas ao servidor"));
        add(rolagem, BorderLayout.CENTER);

        // SUL: status da comunicacao + botoes
        campoStatus.setEditable(false);
        JPanel painelStatus = new JPanel(new BorderLayout(5, 5));
        painelStatus.add(new JLabel("Status:"), BorderLayout.WEST);
        painelStatus.add(campoStatus, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.add(botaoEnviar);
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(botaoSair);

        JPanel painelSul = new JPanel(new GridLayout(2, 1, 5, 5));
        painelSul.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        painelSul.add(painelStatus);
        painelSul.add(painelBotoes);
        add(painelSul, BorderLayout.SOUTH);

        // Eventos
        botaoEnviar.addActionListener(e -> enviar());
        campoMensagem.addActionListener(e -> enviar()); // ENTER tambem envia
        botaoLimpar.addActionListener(e -> limpar());
        botaoSair.addActionListener(e -> sair());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sair();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        iniciaCliente();
    }

    // Cria um cliente com IP e Porta para a comunicacao com o servidor
    private boolean iniciaCliente() {
        try {
            cliente = new Socket(SimpleServerTest.ENDERECO, SimpleServerTest.PORTA);
            saida = new PrintStream(cliente.getOutputStream(), true);
            status("Cliente " + SimpleServerTest.ENDERECO + ":" + SimpleServerTest.PORTA
                    + " conectado ao servidor!");
            return true;
        } catch (IOException ex) {
            status("Falha na comunicacao: " + ex.getMessage() + " (clique em Enviar para tentar de novo)");
            return false;
        }
    }

    private void enviar() {
        String msg = campoMensagem.getText().trim();
        if (msg.isEmpty()) {
            status("Digite uma mensagem antes de enviar.");
            return;
        }
        if (cliente == null && !iniciaCliente()) {
            return;
        }
        saida.println(msg);
        if (saida.checkError()) { // PrintStream nao lanca excecao; verifica o erro manualmente 
            status("Falha ao enviar: conexao com o servidor perdida.");
            fechaConexao();
            return;
        }
        areaMensagens.append(msg + "\n");
        campoMensagem.setText("");
        campoMensagem.requestFocus();
        status("Mensagem enviada.");
    }

    private void limpar() {
        campoMensagem.setText("");
        areaMensagens.setText("");
        campoMensagem.requestFocus();
        status(cliente != null ? "Conectado ao servidor." : "Desconectado.");
    }

    private void sair() {
        fechaConexao();
        dispose();
        System.exit(0);
    }

    private void fechaConexao() {
        if (cliente != null) {
            try {
                cliente.close();
            } catch (IOException ex) {
                // ignorado: o cliente esta encerrando
            }
            cliente = null;
            saida = null;
        }
    }

    private void status(String texto) {
        campoStatus.setText(texto);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(SimpleClientTest::new);
    }
}
