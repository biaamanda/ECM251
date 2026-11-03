/* Criar uma interface grafica para a classe SimpleClientTest.java, atraves dos modelos de layout
 * estudados anteriormente nesta disciplina (flowlayout, borderlayout, gridlayout ou os
 * automaticamente gerados pela IDE NetBeans), eliminando toda e qualquer forma de comunicacao com
 * o usuario atraves do console e/ou por interface Swing como foi fornecida, criando nessa nova
 * interface grafica os campos especificos para as mensagens a serem enviadas ao servidor,
 * respondidas ao cliente e para as mensagens de status da comunicacao, alem da inclusao dos botoes
 * e suas funcionalidades para Enviar, Limpar e Sair, na propria interface.
 */

// Cliente com interface grafica (mensagem, respostas, status e botoes)
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SimpleClientTest extends JFrame {
    public static final String ENDERECO = "127.0.0.1";
    public static final int PORTA = 3334;

    private Socket cliente;
    private PrintStream saida;

    private final JTextField campoMensagem = new JTextField(25);
    private final JTextArea areaRespostas = new JTextArea(10, 30);
    private final JLabel status = new JLabel("Desconectado");

    public SimpleClientTest() {
        super("Cliente");

        JPanel topo = new JPanel(new FlowLayout());
        topo.add(new JLabel("Mensagem:"));
        topo.add(campoMensagem);

        areaRespostas.setEditable(false);
        JPanel centro = new JPanel(new BorderLayout());
        centro.add(new JLabel(" Respostas do servidor:"), BorderLayout.NORTH);
        centro.add(new JScrollPane(areaRespostas), BorderLayout.CENTER);

        JButton enviar = new JButton("Enviar");
        JButton limpar = new JButton("Limpar");
        JButton sair = new JButton("Sair");
        JPanel botoes = new JPanel(new FlowLayout());
        botoes.add(enviar);
        botoes.add(limpar);
        botoes.add(sair);

        JPanel sul = new JPanel(new GridLayout(2, 1));
        sul.add(botoes);
        sul.add(status);

        add(topo, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(sul, BorderLayout.SOUTH);

        enviar.addActionListener(e -> enviar());
        campoMensagem.addActionListener(e -> enviar()); // ENTER tambem envia
        limpar.addActionListener(e -> limpar());
        sair.addActionListener(e -> sair());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        conecta();
    }

    private void conecta() {
        try {
            cliente = new Socket(ENDERECO, PORTA);
            saida = new PrintStream(cliente.getOutputStream(), true);
            status.setText(" Conectado a " + ENDERECO + ":" + PORTA);
            Thread receptor = new Thread(this::recebe); // leitura bloqueante fora da interface
            receptor.setDaemon(true);
            receptor.start();
        } catch (IOException ex) {
            status.setText(" Falha na comunicacao: " + ex.getMessage());
        }
    }

    private void recebe() {
        try {
            Scanner entrada = new Scanner(cliente.getInputStream());
            while (entrada.hasNextLine()) {
                String resposta = entrada.nextLine();
                SwingUtilities.invokeLater(() -> areaRespostas.append(resposta + "\n"));
            }
        } catch (IOException ex) {
            // a conexao terminou
        }
        SwingUtilities.invokeLater(() -> status.setText(" Conexao encerrada"));
    }

    private void enviar() {
        String msg = campoMensagem.getText().trim();
        if (msg.isEmpty() || saida == null) {
            return;
        }
        saida.println(msg);
        campoMensagem.setText("");
        status.setText(" Mensagem enviada: " + msg);
    }

    private void limpar() {
        campoMensagem.setText("");
        areaRespostas.setText("");
    }

    private void sair() {
        try {
            if (cliente != null) {
                cliente.close();
            }
        } catch (IOException ex) {
            // encerrando de qualquer forma
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleClientTest::new);
    }
}
