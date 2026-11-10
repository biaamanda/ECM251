/* 11. Criar uma interface gráfica para a classe ClienteBatepapo.java,
através dos modelos de layouts estudados anteriormente nesta
disciplina (flowlayout, borderlayout, gridlayout ou os
automaticamente gerados pela IDE NetBeans), eliminando toda
e qualquer forma de comunicação com o usuário através do
console e/ou por interface Swing, criando nessa nova interface
gráfica os campos específicos para as mensagens do Cliente que
serão enviadas ao Servidor e para as mensagens de status da
comunicação, além da inclusão dos botões e suas
funcionalidades para Enviar, Limpar e Sair, na própria interface. */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/* Substitui todo o uso do console por uma janela com:
   - área "Conversa": mensagens enviadas (Eu: ...) e recebidas dos outros clientes;
   - campo "Mensagem": texto que será enviado ao servidor;
   - área "Status da comunicação": conexão, envios, erros e desconexão;
   - botões Enviar, Limpar e Sair.
   Usa duas threads: a thread da interface (Swing) envia as mensagens, 
   e uma thread separada (run) fica bloqueada em getMessage()
   esperando as mensagens que chegam do servidor. */

public class ClienteBatepapoGUI extends JFrame implements Runnable {
    private static final DateTimeFormatter HORA = DateTimeFormatter.ofPattern("HH:mm:ss");

    private SocketCliente clientSocket;

    private final JTextArea areaConversa = new JTextArea(15, 40);
    private final JTextField campoMensagem = new JTextField(30);
    private final JTextArea areaStatus = new JTextArea(4, 40);
    private final JButton botaoEnviar = new JButton("Enviar");
    private final JButton botaoLimpar = new JButton("Limpar");
    private final JButton botaoSair = new JButton("Sair");

    public ClienteBatepapoGUI() {
        super("Bate-papo - Cliente");
        montaTela();
        conecta();
    }

    // Layout: BorderLayout na janela, FlowLayout nas linhas e GridLayout no rodapé
    private void montaTela() {
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

        botaoEnviar.addActionListener(e -> enviar());
        campoMensagem.addActionListener(e -> enviar()); // ENTER também envia
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

    // Conecta ao servidor; se falhar, informa no status e tenta de novo no próximo Enviar
    private void conecta() {
        status("Conectando a " + ServidorBatepapo.ADDRESS + ":" + ServidorBatepapo.PORT + "...");
        try {
            clientSocket = new SocketCliente(new Socket(ServidorBatepapo.ADDRESS, ServidorBatepapo.PORT));
            setTitle("Bate-papo - Cliente " + clientSocket.getLocalSocketAddress());
            status("Conectado com " + clientSocket.getRemoteSocketAddress()
                    + " (este cliente: " + clientSocket.getLocalSocketAddress() + ")");
            new Thread(this).start();
        } catch (IOException ex) {
            clientSocket = null;
            status("Erro ao conectar: " + ex.getMessage() + ". Inicie o servidor e clique em Enviar para tentar de novo.");
        }
    }

    // Thread de leitura: espera mensagens do servidor sem travar a interface
    @Override
    public void run() {
        SocketCliente conexao = clientSocket;
        String msg;
        while ((msg = conexao.getMessage()) != null) {
            final String recebida = msg;
            SwingUtilities.invokeLater(() -> conversa("-> " + recebida));
        }
        SwingUtilities.invokeLater(() -> {
            if (clientSocket == conexao) { // a conexão caiu sem o usuário clicar em Sair
                clientSocket = null;
                status("Conexão encerrada pelo servidor. Clique em Enviar para reconectar.");
            }
        });
    }

    private void enviar() {
        String msg = campoMensagem.getText().trim();
        if (msg.isEmpty()) {
            status("Digite uma mensagem antes de enviar.");
            return;
        }
        if (msg.equalsIgnoreCase("sair")) { // mesmo comando do cliente de console
            sair();
            return;
        }
        if (clientSocket == null) {
            conecta();
            if (clientSocket == null) {
                return;
            }
        }
        if (clientSocket.sendMsg(msg)) {
            conversa("<- Eu: " + msg);
            status("Mensagem enviada.");
            campoMensagem.setText("");
        } else {
            status("Erro ao enviar a mensagem: conexão perdida.");
        }
        campoMensagem.requestFocusInWindow();
    }

    private void limpar() {
        campoMensagem.setText("");
        areaConversa.setText("");
        status("Conversa e campo de mensagem limpos.");
        campoMensagem.requestFocusInWindow();
    }

    // Avisa o servidor com "sair" (como no Exercício 1), fecha a conexão e encerra
    private void sair() {
        if (clientSocket != null) {
            SocketCliente conexao = clientSocket;
            clientSocket = null;
            conexao.sendMsg("sair");
            conexao.close();
        }
        dispose();
        System.exit(0);
    }

    private void conversa(String texto) {
        areaConversa.append(texto + "\n");
        areaConversa.setCaretPosition(areaConversa.getDocument().getLength());
    }

    private void status(String texto) {
        areaStatus.append("[" + LocalTime.now().format(HORA) + "] " + texto + "\n");
        areaStatus.setCaretPosition(areaStatus.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClienteBatepapoGUI::new);
    }
}
