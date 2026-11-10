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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
   e uma thread separada (run) fica bloqueada em readLine()
   esperando as mensagens que chegam do servidor.
   Conecta ao ServidorBatepapo do Exercício 1 (que deve estar rodando). O cliente
   abre o próprio Socket, em vez de usar o SocketCliente do Exercício 1, porque
   aquela classe escreve no console, o que o enunciado não permite. */

public class ClienteBatepapoGUI extends JFrame implements Runnable {
    // Endereço e porta do ServidorBatepapo do Exercício 1
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 4000;

    // Formato da hora mostrada em cada linha do status
    private static final DateTimeFormatter HORA = DateTimeFormatter.ofPattern("HH:mm:ss");

    // Conexão com o servidor (null quando o cliente está desconectado) e envio de linhas
    private Socket socket;
    private PrintWriter saida;

    // Componentes da janela
    private final JTextArea areaConversa = new JTextArea(15, 40);
    private final JTextField campoMensagem = new JTextField(30);
    private final JTextArea areaStatus = new JTextArea(4, 40);
    private final JButton botaoEnviar = new JButton("Enviar");
    private final JButton botaoLimpar = new JButton("Limpar");
    private final JButton botaoSair = new JButton("Sair");

    // Construtor: monta a janela e já tenta conectar ao servidor
    public ClienteBatepapoGUI() {
        super("Bate-papo - Cliente");
        montaTela();
        conecta();
    }

    // Monta a janela e liga cada botão à sua ação.
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
        status("Conectando a " + ADDRESS + ":" + PORT + "...");
        try {
            socket = new Socket(ADDRESS, PORT);
            saida = new PrintWriter(socket.getOutputStream(), true);
            setTitle("Bate-papo - Cliente " + socket.getLocalSocketAddress());
            status("Conectado com " + socket.getRemoteSocketAddress()
                    + " (este cliente: " + socket.getLocalSocketAddress() + ")");
            new Thread(this).start();
        } catch (IOException ex) {
            fecha();
            status("Erro ao conectar: " + ex.getMessage() + ". Inicie o servidor e clique em Enviar para tentar de novo.");
        }
    }

    // Thread de leitura: espera mensagens do servidor sem travar a interface.
    // Cada mensagem recebida vai para a área Conversa; quando a conexão cai, avisa no status
    @Override
    public void run() {
        Socket conexao = socket;
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()))) {
            String msg;
            while ((msg = entrada.readLine()) != null) {
                final String recebida = msg;
                SwingUtilities.invokeLater(() -> conversa("-> " + recebida));
            }
        } catch (IOException ex) {
            // a conexão foi fechada
        }
        SwingUtilities.invokeLater(() -> {
            if (socket == conexao) { // a conexão caiu sem o usuário clicar em Sair
                fecha();
                status("Conexão encerrada pelo servidor. Clique em Enviar para reconectar.");
            }
        });
    }

    // Botão Enviar (ou ENTER): envia o texto do campo ao servidor e mostra na Conversa.
    // Se estiver desconectado, tenta conectar antes; "sair" funciona como o botão Sair
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
        if (socket == null) {
            conecta();
            if (socket == null) {
                return;
            }
        }
        saida.println(msg);
        if (!saida.checkError()) {
            conversa("<- Eu: " + msg);
            status("Mensagem enviada.");
            campoMensagem.setText("");
        } else {
            status("Erro ao enviar a mensagem: conexão perdida.");
        }
        campoMensagem.requestFocusInWindow();
    }

    // Botão Limpar: apaga o campo de mensagem e a área Conversa (o status é mantido)
    private void limpar() {
        campoMensagem.setText("");
        areaConversa.setText("");
        status("Conversa e campo de mensagem limpos.");
        campoMensagem.requestFocusInWindow();
    }

    // Botão Sair (ou X da janela): avisa o servidor com "sair", fecha a conexão e encerra o programa
    private void sair() {
        if (saida != null) {
            saida.println("sair");
        }
        fecha();
        dispose();
        System.exit(0);
    }

    // Fecha a conexão (se houver) e marca o cliente como desconectado
    private void fecha() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ex) {
            // a conexão já estava fechada; nada a fazer
        }
        socket = null;
        saida = null;
    }

    // Acrescenta uma linha na área Conversa e rola até o fim
    private void conversa(String texto) {
        areaConversa.append(texto + "\n");
        areaConversa.setCaretPosition(areaConversa.getDocument().getLength());
    }

    // Acrescenta uma linha, com a hora, na área Status da comunicação e rola até o fim
    private void status(String texto) {
        areaStatus.append("[" + LocalTime.now().format(HORA) + "] " + texto + "\n");
        areaStatus.setCaretPosition(areaStatus.getDocument().getLength());
    }

    // Ponto de entrada: cria a janela na thread da interface gráfica (Swing)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClienteBatepapoGUI::new);
    }
}
