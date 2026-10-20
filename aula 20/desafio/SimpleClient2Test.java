/*
 * Aula 20 - Exercicio Desafio
 *
 * Enunciado:
 * Estudar, pesquisar, desenvolver e testar novas classes SimpleServer2Test.java e
 * SimpleClient2Test.java, baseadas nas respectivas classes fornecidas, fazendo com que a nova
 * classe servidor retransmita de volta para a nova classe cliente as mensagens que a classe
 * servidor receber, vindas da classe cliente, apresentando-as, tambem, na interface grafica da
 * classe cliente desenvolvida no exercicio 2 desta atividade, junto com as suas mensagens
 * anteriores.
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

// Cliente do desafio: mesma interface grafica do exercicio 2, mas agora uma thread
// escuta as mensagens retransmitidas pelo SimpleServer2Test e as exibe no historico,
// junto com as mensagens enviadas.
public class SimpleClient2Test extends JFrame
{
    private Socket cliente;     // Comunicacao com o servidor
    private PrintStream saida;  // Canal de saida para o servidor

    private final JTextField campoMensagem = new JTextField(30);
    private final JTextArea areaMensagens = new JTextArea(12, 40);
    private final JTextField campoStatus = new JTextField();
    private final JButton botaoEnviar = new JButton("Enviar");
    private final JButton botaoLimpar = new JButton("Limpar");
    private final JButton botaoSair = new JButton("Sair");

    public SimpleClient2Test()
    {
        super("Cliente - SimpleClient2Test");
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
        rolagem.setBorder(BorderFactory.createTitledBorder("Conversa com o servidor"));
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
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                sair();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        iniciaCliente();
    }

    // Cria um cliente com IP e Porta para a comunicacao com o servidor
    private boolean iniciaCliente()
    {
        try
        {
            cliente = new Socket(SimpleServer2Test.ENDERECO, SimpleServer2Test.PORTA);
            saida = new PrintStream(cliente.getOutputStream(), true);
            iniciaRecepcao(cliente);
            status("Cliente " + SimpleServer2Test.ENDERECO + ":" + SimpleServer2Test.PORTA
                    + " conectado ao servidor!");
            return true;
        }
        catch (IOException ex)
        {
            status("Falha na comunicacao: " + ex.getMessage() + " (clique em Enviar para tentar de novo)");
            return false;
        }
    }

    private void enviar()
    {
        String msg = campoMensagem.getText().trim();
        if (msg.isEmpty())
        {
            status("Digite uma mensagem antes de enviar.");
            return;
        }
        if (cliente == null && !iniciaCliente())
        {
            return;
        }
        saida.println(msg);
        if (saida.checkError()) // PrintStream nao lanca excecao; verifica o erro manualmente
        {
            status("Falha ao enviar: conexao com o servidor perdida.");
            fechaConexao();
            return;
        }
        areaMensagens.append("Eu: " + msg + "\n");
        campoMensagem.setText("");
        campoMensagem.requestFocus();
        status("Mensagem enviada.");
    }

    // Thread que fica escutando as mensagens que o servidor retransmite ao cliente.
    // A leitura e bloqueante, por isso nao pode rodar na thread da interface (EDT).
    private void iniciaRecepcao(Socket socket)
    {
        Thread receptor = new Thread(() ->
        {
            try
            {
                Scanner entrada = new Scanner(socket.getInputStream());
                while (entrada.hasNextLine())
                {
                    String msg = entrada.nextLine();
                    SwingUtilities.invokeLater(() -> areaMensagens.append("Servidor: " + msg + "\n"));
                }
            }
            catch (IOException ex)
            {
                // tratado abaixo: a conexao terminou
            }
            SwingUtilities.invokeLater(() ->
            {
                if (cliente == socket) // foi o servidor que encerrou, nao o usuario
                {
                    fechaConexao();
                    status("Servidor encerrou a conexao.");
                }
            });
        });
        receptor.setDaemon(true);
        receptor.start();
    }

    private void limpar()
    {
        campoMensagem.setText("");
        areaMensagens.setText("");
        campoMensagem.requestFocus();
        status(cliente != null ? "Conectado ao servidor." : "Desconectado.");
    }

    private void sair()
    {
        fechaConexao();
        dispose();
        System.exit(0);
    }

    private void fechaConexao()
    {
        if (cliente != null)
        {
            try
            {
                cliente.close();
            }
            catch (IOException ex)
            {
                // ignorado: o cliente esta encerrando
            }
            cliente = null;
            saida = null;
        }
    }

    private void status(String texto)
    {
        campoStatus.setText(texto);
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(SimpleClient2Test::new);
    }
}
