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
    private static final int PORT = 4000;

    // Conexão com o servidor (socket + entrada + saída)
    private SocketCliente conexao;

    // P4 2025: número deste cliente (1 ou 2), informado pelo servidor com "ID:n"
    private int numero;
    // P4 2025: fica true quando é o PRÓPRIO usuário que está saindo (botão Sair / X).
    // Serve para o run() não mostrar "o servidor caiu" nesse caso.
    // volatile: é escrita por uma thread (a do botão) e lida por outra (a do run())
    private volatile boolean saindo;

    // Componentes da tela
    private final JTextArea areaConversa = new JTextArea(15, 40);
    private final JTextField campoMensagem = new JTextField(30);
    private final JTextArea areaStatus = new JTextArea(4, 40);
    private final JButton botaoEnviar = new JButton("Enviar");
    private final JButton botaoLimpar = new JButton("Limpar");
    private final JButton botaoSair = new JButton("Sair");


    /** monta a janela e em seguida tenta conectar. */
    public Cliente() {
        super("Chat - Cliente");
        montarTela();
        conectar();
    }

    /**
     * RAZÃO: cria os componentes, os layouts (BorderLayout/FlowLayout)
     * e liga os botões aos métodos (addActionListener).
     */
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
     * RAZÃO: abre o Socket com Servidor.ENDERECO e Servidor.PORTA.
     * - Sucesso: mostra status "conectado" e inicia new Thread(this).start() (-> run()).
     * - Falha (caso de teste 1): mostra JOptionPane de erro e encerra o cliente.
     */
    private void conectar() {
       try { 
        conexao = new SocketCliente(new Socket(ENDERECO, PORT)); 
        status("Conectado ao servidor. Comunicação com o servidor ativa"); 
        new Thread(this).start(); 
    } 
    catch (IOException e) { 
        JOptionPane.showMessageDialog(this, "Servidor indisponível: " + e.getMessage()); System.exit(0); 
    }
    }

    /**
     * RAZÃO: thread que fica presa em conexao.getMessage() esperando o servidor.
     * - Mensagem normal -> exibirMensagem(...)
     * - Aviso de servidor cheio (caso de teste 5) -> mostra erro e fecha
     * - null -> servidor caiu / conexão encerrada
     */
    @Override
    public void run() {
        String msg; 
        while ((msg = conexao.getMessage()) != null) { 
            if (msg.equals("SERVIDOR_CHEIO")) { 
                JOptionPane.showMessageDialog(this, "Já há 2 clientes conectados."); 
                System.exit(0); 
            }
            // P4 2025: o servidor informa o número deste cliente ("ID:1" ou "ID:2")
            if (msg.startsWith("ID:")) {
                numero = Integer.parseInt(msg.substring(3));   // "ID:2" -> 2
                setTitle("Chat - Cliente #" + numero);
                continue;
            }
            // P4 2025 (caso 4): texto agora diz que é o Cliente #1 e que espera o segundo
            if (msg.equals("AGUARDANDO")) {
                status("Cliente #" + numero + " aberto e conectado ao servidor. Aguardando a conexão de um segundo cliente...");
                continue;   // volta para o while sem mostrar na conversa
            }
            // P4 2025 (caso 5): texto agora inclui o número do cliente
            if (msg.equals("CONECTADO")) {
                status("Cliente #" + numero + ": comunicação bidirecional com o outro cliente ativa. Aguardando mensagens...");
                continue;
            }
            // P4 2025 (caso 9): o outro cliente saiu -> este cliente também fecha
            if (msg.equals("DESCONECTAR")) {
                encerrar("O outro cliente se desconectou. Este cliente também será encerrado.");
                return;
            }
            exibirMensagem("-> " + msg);
        }
        // P4 2025 (caso 9): getMessage() devolveu null -> o servidor caiu.
        // Se não foi o próprio usuário que saiu, avisa e fecha o cliente
        if (!saindo) {
            encerrar("O servidor se desconectou. Este cliente será encerrado.");
        }
    }

    // P4 2025: método novo -> mostra o aviso, fecha a conexão e encerra o cliente
    private void encerrar(String aviso) {
        saindo = true;
        JOptionPane.showMessageDialog(this, aviso);
        conexao.close();
        System.exit(0);
    }

    /**
     * RAZÃO: pega o texto do campo, envia ao servidor, mostra "Você: ..." na conversa
     * e limpa o campo. Chamado pelo botão Enviar e pelo Enter.
     */
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

    /**
     * RAZÃO: acrescenta uma linha na área de conversa.
     * Usa SwingUtilities.invokeLater porque quem chama é a thread do run(),
     * e só a thread do Swing deve mexer na tela.
     */
    private void exibirMensagem(String msg) {
        SwingUtilities.invokeLater(() -> areaConversa.append(msg + "\n"));
    }

    /**
     * RAZÃO: botão Sair / fechar janela -> avisa o servidor ("sair"),
     * fecha a conexão e fecha a janela (dispose()).
     */
    private void sair() {
        // P4 2025: marca que é o próprio usuário saindo (o run() não mostra "o servidor caiu")
        saindo = true;
        if (conexao != null) {
            conexao.sendMsg("sair"); 
            conexao.close(); 
        } 
        dispose(); 
        System.exit(0);
    }

    /**
     * Ponto de entrada (necessário para rodar e para o .jar).
     * RAZÃO: cria a janela dentro da thread do Swing e deixa visível.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cliente());
   }
}
