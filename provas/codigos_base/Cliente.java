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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/*
 * ============================================================================
 *  Cliente  —  janela de chat (Swing)
 * ============================================================================
 *  O QUE É: a janela do chat. Conecta ao servidor, envia mensagens e mostra
 *           o que chega.
 *
 *  extends JFrame       -> a própria classe é a janela.
 *  implements Runnable  -> o run() vira a thread que fica OUVINDO o servidor.
 *  POR QUE 2 THREADS: getMessage() é bloqueante. A thread do Swing cuida dos
 *  cliques (enviar); a thread do run() fica esperando o servidor. Com uma só,
 *  a janela travaria.
 *
 *  ETIQUETAS:  [SEMPRE]  manter em qualquer prova de chat com janela
 *              [2024+]   só se a prova pedir aviso "aguardando / comunicação ativa"
 *              [2025]    só se a prova pedir Cliente #1/#2 e desconexão em cadeia
 *
 *  Se a prova pedir cliente de CONSOLE (sem janela): veja o Client da Aula 22
 *  (codeLP/Aula22/Ex5e6/Client.java) — Scanner(System.in) + loop de envio,
 *  e uma Thread separada com o mesmo while do run() daqui.
 * ============================================================================
 */
public class Cliente extends JFrame implements Runnable {

    // [SEMPRE] mesmos valores do Servidor
    private static final String ENDERECO = "127.0.0.1";
    private static final int PORT = 4000;

    // [SEMPRE] a conexão com o servidor
    private SocketCliente conexao;

    // [2025] número deste cliente (1 ou 2), informado pelo servidor com "ID:n"
    private int numero;
    // [2025] true quando é o PRÓPRIO usuário saindo (Sair / X).
    // Evita mostrar "o servidor caiu" nesse caso. volatile: escrita por uma
    // thread (botão) e lida por outra (run()).
    private volatile boolean saindo;

    // [SEMPRE] componentes da tela
    private final JTextArea areaConversa = new JTextArea(15, 40);   // mensagens
    private final JTextField campoMensagem = new JTextField(30);    // onde digita
    private final JTextArea areaStatus = new JTextArea(4, 40);      // status da comunicação
    private final JButton botaoEnviar = new JButton("Enviar");
    private final JButton botaoLimpar = new JButton("Limpar");
    private final JButton botaoSair = new JButton("Sair");

    /*
     * Construtor
     * FAZ: monta a janela e depois tenta conectar.
     * MANTER: [SEMPRE].
     */
    public Cliente() {
        super("Chat - Cliente");
        montarTela();
        conectar();
    }

    /*
     * montarTela
     * FAZ: cria o layout e liga os botões aos métodos.
     *        CENTER: conversa | SOUTH: campo + botões (em cima) e status (embaixo)
     *      Enter no campo também envia. O X da janela faz o mesmo que Sair.
     * MANTER: [SEMPRE] (a prova exige interface em janela).
     *      Os botões Enviar/Limpar/Sair foram pedidos nos exercícios das aulas;
     *      a área de status é onde aparecem os avisos dos casos de teste.
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
        campoMensagem.addActionListener(e -> enviarMensagem());   // Enter também envia
        botaoLimpar.addActionListener(e -> limpar());
        botaoSair.addActionListener(e -> sair());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);             // X = Sair
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sair();
            }
        });

        pack();
        setLocationRelativeTo(null);                               // centraliza
        setVisible(true);
        campoMensagem.requestFocusInWindow();
    }

    /*
     * status
     * FAZ: escreve uma linha na área "Status da comunicação".
     *      invokeLater: só a thread do Swing deve mexer na tela.
     * MANTER: [SEMPRE] (os casos de teste pedem para "informar" o status).
     */
    private void status(String s) {
        SwingUtilities.invokeLater(() -> areaStatus.append(s + "\n"));
    }

    /*
     * limpar
     * FAZ: apaga a conversa (só nesta janela).
     * MANTER: se a prova/exercício pedir botão Limpar. Senão, pode apagar
     *         junto com o botaoLimpar.
     */
    private void limpar() {
        areaConversa.setText("");
    }

    /*
     * conectar
     * FAZ: abre o Socket com o servidor.
     *      - Deu certo: mostra o status e inicia a thread do run().
     *      - Não há servidor: mostra o erro e FECHA o cliente.
     * MANTER: [SEMPRE].
     * CASOS DE TESTE: "cliente sem servidor -> não conseguiu permanecer aberto"
     *                 e "[2024] conectado, comunicação com o servidor ativa".
     */
    private void conectar() {
        try {
            conexao = new SocketCliente(new Socket(ENDERECO, PORT));
            status("Conectado ao servidor. Comunicação com o servidor ativa.");
            new Thread(this).start();                              // -> run()
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Servidor indisponível em " + ENDERECO + ":" + PORT
                    + ". O cliente será encerrado.\n(" + e.getMessage() + ")");
            System.exit(0);
        }
    }

    /*
     * run
     * FAZ: thread que fica OUVINDO o servidor (getMessage() é bloqueante).
     *      Primeiro testa as palavras de controle; o que não for controle é
     *      mensagem do outro cliente e vai para a conversa.
     *      O "continue" volta para o while sem mostrar a palavra na conversa.
     * MANTER: [SEMPRE] o while, o SERVIDOR_CHEIO e o exibirMensagem.
     *         Os outros if têm etiqueta.
     * ATENÇÃO: se apagar o [2025] "ID:", tire também o "Cliente #" + numero
     *         dos textos de AGUARDANDO/CONECTADO (senão aparece "Cliente #0").
     */
    @Override
    public void run() {
        String msg;
        while ((msg = conexao.getMessage()) != null) {
            // [SEMPRE] 3º cliente
            if (msg.equals("SERVIDOR_CHEIO")) {
                JOptionPane.showMessageDialog(this, "Já há 2 clientes conectados. O cliente será encerrado.");
                System.exit(0);
            }
            // [2025] número do cliente
            if (msg.startsWith("ID:")) {
                numero = Integer.parseInt(msg.substring(3));      // "ID:2" -> 2
                setTitle("Chat - Cliente #" + numero);
                continue;
            }
            // [2024+] só este cliente está conectado
            if (msg.equals("AGUARDANDO")) {
                status("Cliente #" + numero + " aberto e conectado ao servidor. Aguardando a conexão de um segundo cliente...");
                continue;
            }
            // [2024+] os dois estão conectados
            if (msg.equals("CONECTADO")) {
                status("Cliente #" + numero + ": comunicação bidirecional com o outro cliente ativa. Aguardando mensagens...");
                continue;
            }
            // [2025] o outro cliente saiu -> este também fecha
            if (msg.equals("DESCONECTAR")) {
                encerrar("O outro cliente se desconectou. Este cliente também será encerrado.");
                return;
            }
            // [SEMPRE] mensagem normal do outro cliente
            exibirMensagem("-> " + msg);
        }
        // getMessage() devolveu null: a conexão acabou.
        // [2025] se não foi o próprio usuário que saiu, o servidor caiu -> fecha.
        // (Sem a regra de 2025, troque por: status("Conexão encerrada.");)
        if (!saindo) {
            encerrar("O servidor se desconectou. Este cliente será encerrado.");
        }
    }

    /*
     * encerrar
     * FAZ: mostra um aviso, fecha a conexão e encerra o cliente.
     * MANTER: [2025] (usado pelo DESCONECTAR e quando o servidor cai).
     */
    private void encerrar(String aviso) {
        saindo = true;
        JOptionPane.showMessageDialog(this, aviso);
        conexao.close();
        System.exit(0);
    }

    /*
     * enviarMensagem
     * FAZ: pega o texto do campo, envia, mostra "<- Eu: ..." na conversa
     *      e limpa o campo. Digitar "sair" encerra o cliente.
     * MANTER: [SEMPRE].
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
            campoMensagem.setText("");
        } else {
            status("Erro ao enviar: conexão perdida.");
        }
    }

    /*
     * exibirMensagem
     * FAZ: acrescenta uma linha na conversa. Usa invokeLater porque é chamado
     *      pela thread do run(), e só a thread do Swing deve mexer na tela.
     * MANTER: [SEMPRE].
     */
    private void exibirMensagem(String msg) {
        SwingUtilities.invokeLater(() -> areaConversa.append(msg + "\n"));
    }

    /*
     * sair
     * FAZ: avisa o servidor ("sair"), fecha a conexão e fecha a janela.
     *      Chamado pelo botão Sair, pelo X e por digitar "sair".
     * MANTER: [SEMPRE]. A linha "saindo = true" é [2025].
     */
    private void sair() {
        saindo = true;
        if (conexao != null) {
            conexao.sendMsg("sair");
            conexao.close();
        }
        dispose();
        System.exit(0);
    }

    /*
     * main
     * FAZ: cria a janela dentro da thread do Swing.
     * MANTER: [SEMPRE] — ponto de entrada (necessário para o .jar).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cliente());
    }
}
