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

/**
 * CLIENTE
 * - A prova exige interface em JANELA (Swing), nada de console.
 * - extends JFrame  -> a própria classe é a janela.
 * - implements Runnable -> o método run() vira a thread que fica ESCUTANDO o servidor,
 *   enquanto a thread do Swing cuida dos cliques (enviar). Sem isso a tela travaria.
 */
public class Cliente extends JFrame implements Runnable {

    private static final String ENDERECO = "127.0.0.1";
    private static final int PORT = 4000;

    // Conexão com o servidor (socket + entrada + saída)
    private SocketCliente conexao;

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

    /**
     * RAZÃO: abre o Socket com Servidor.ENDERECO e Servidor.PORTA.
     * - Sucesso: mostra status "conectado" e inicia new Thread(this).start() (-> run()).
     * - Falha (caso de teste 1): mostra JOptionPane de erro e encerra o cliente.
     */
    private void conectar() {
       
    }

    /**
     * RAZÃO: thread que fica presa em conexao.getMessage() esperando o servidor.
     * - Mensagem normal -> exibirMensagem(...)
     * - Aviso de servidor cheio (caso de teste 5) -> mostra erro e fecha
     * - null -> servidor caiu / conexão encerrada
     */
    @Override
    public void run() {
        
    }

    /**
     * RAZÃO: pega o texto do campo, envia ao servidor, mostra "Você: ..." na conversa
     * e limpa o campo. Chamado pelo botão Enviar e pelo Enter.
     */
    private void enviarMensagem() {
        
    }

    /**
     * RAZÃO: acrescenta uma linha na área de conversa.
     * Usa SwingUtilities.invokeLater porque quem chama é a thread do run(),
     * e só a thread do Swing deve mexer na tela.
     */
    private void exibirMensagem(String msg) {
        // TODO
    }

    /**
     * RAZÃO: botão Sair / fechar janela -> avisa o servidor ("sair"),
     * fecha a conexão e fecha a janela (dispose()).
     */
    private void sair() {
        
    }

    /**
     * Ponto de entrada (necessário para rodar e para o .jar).
     * RAZÃO: cria a janela dentro da thread do Swing e deixa visível.
     */
    public static void main(String[] args) {
        
   }
}
