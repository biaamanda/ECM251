import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
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
import javax.swing.JOptionPane;

public class Cliente extends JFrame implements Runnable {

    private static final String ENDERECO = "127.0.0.1";
    private static final int PORT = 5000;

    private SocketCliente conexao;

    private final JTextArea areaConversa = new JTextArea(15, 40);
    private final JTextField campoMensagem = new JTextField(30);
    private final JTextArea areaStatus = new JTextArea(4, 40);
    private final JButton botaoEnviar = new JButton("Enviar");
    private final JButton botaoLimpar = new JButton("Limpar");
    private final JButton botaoSair = new JButton("Sair");

    public Cliente() {
        super("Chat - Cliente");
        montarTela();
        conectar();
    }


    private void montarTela() {
        areaConversa.setEditable(false);
        areaConversa.setLineWrap(true);
        JScrollPane rolagemConversa = new JScrollPane(areaConversa);
        rolagemConversa.setBorder(BorderFactory.createTitledBorder("Conversa"));

        JPanel linhaMensagem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linhaMensagem.add(new JLabel("Mensagem:"));
        linhaMensagem.add(campoMensagem);

        JPanel linhaBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linhaBotoes.add(botaoEnviar);
        linhaBotoes.add(botaoLimpar);
        linhaBotoes.add(botaoSair);

        JPanel envio = new JPanel(new GridLayout(2, 1));
        envio.add(linhaMensagem);
        envio.add(linhaBotoes);

        areaStatus.setEditable(false);
        JScrollPane rolagemStatus = new JScrollPane(areaStatus);
        rolagemStatus.setBorder(BorderFactory.createTitledBorder("Status da comunicação"));

        JPanel rodape = new JPanel(new BorderLayout());
        rodape.add(envio, BorderLayout.NORTH);
        rodape.add(rolagemStatus, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(rolagemConversa, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);

        botaoEnviar.addActionListener(e -> enviarMensagem());
        campoMensagem.addActionListener(e -> enviarMensagem()); // ENTER também envia
        botaoLimpar.addActionListener(e -> limpar());
        botaoSair.addActionListener(e -> sair());

        // fechar no X equivale a clicar em Sair
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sair();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        campoMensagem.requestFocusInWindow();
    }

    private void status(String s) {
        SwingUtilities.invokeLater(() -> areaStatus.append(s + "\n"));
    }

    private void limpar() {
        areaConversa.setText("");
    }

    /**
     * abre o Socket com Servidor.ENDERECO e Servidor.PORTA.
     * - Sucesso: mostra status "conectado" e inicia new Thread(this).start() (-> run()).
     * - Mostra JOptionPane de erro e encerra o cliente.
     */
    private void conectar() {
       try { 
        conexao = new SocketCliente(new Socket(ENDERECO, PORT)); 
        status("Conectado a " + conexao.getRemoteSocketAddress() + ". Aguardando mensagens..."); new Thread(this).start(); 
    } 
    catch (IOException e) { 
        JOptionPane.showMessageDialog(this, "Servidor indisponível: " + e.getMessage()); System.exit(0); 
    }
    }

    /**
     * thread que fica presa em conexao.getMessage() esperando o servidor.
     * - Mensagem normal -> exibirMensagem(...)
     * - null -> servidor caiu / conexão encerrada
     */
    @Override
    public void run() {
        String msg; 
        while ((msg = conexao.getMessage()) != null) { 
            exibirMensagem("-> " + msg); 
        } 
        status("Conexão encerrada.");
    }

    private void enviarMensagem() {
        String msg = campoMensagem.getText().trim(); 
        if (msg.isEmpty()) return; 
        if (msg.equalsIgnoreCase("sair")) { 
            sair(); 
            return; 
        } 
        if (conexao.sendMsg(msg)) { 
            exibirMensagem("<- Eu: " + msg); 
        campoMensagem.setText(""); } else status("Erro ao enviar.");
    }

    private void exibirMensagem(String msg) {
        SwingUtilities.invokeLater(() -> areaConversa.append(msg + "\n"));
    }

    private void sair() {
        if (conexao != null) { 
            conexao.sendMsg("sair"); 
            conexao.close(); 
        } 
        dispose(); 
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cliente());
   }
}
