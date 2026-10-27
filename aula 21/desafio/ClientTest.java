// Aula 21 - Exercicio Desafio: mesmo cliente do exercicio 2 (abra quantas instancias quiser)
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

public class ClientTest extends JFrame {
    public static final String ENDERECO = "127.0.0.1";
    public static final int PORTA = 3334;

    private Socket cliente;
    private PrintStream saida;

    private final JTextField campoMensagem = new JTextField(25);
    private final JTextArea areaRespostas = new JTextArea(10, 30);
    private final JLabel status = new JLabel("Desconectado");

    public ClientTest() {
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
        SwingUtilities.invokeLater(ClientTest::new);
    }
}
