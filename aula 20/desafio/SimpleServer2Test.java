import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// Servidor baseado no SimpleServerTest que, alem de imprimir as mensagens recebidas,
// retransmite (eco) cada uma delas de volta para o cliente que a enviou.
public class SimpleServer2Test
{
    // Especifica endereco do IP local para o servidor
    public static final String ENDERECO = "127.0.0.1";
    // Especifica porta livre do computador para o servidor escutar
    public static final int PORTA = 3335;

    public static void main(String args[])
    {
        try
        {
            // Cria servico de escuta da porta especificada via socket no servidor
            ServerSocket servidor = new ServerSocket(PORTA);
            System.out.println("Servidor iniciado na porta " + PORTA);
            // Cria canal de comunicacao para a conexao de um cliente (servico)
            Socket cliente = servidor.accept();
            System.out.println("Cliente do IP: " + cliente.getInetAddress().getHostAddress()
                    + " conectado ao servidor");
            // Canal de entrada (mensagens vindas do cliente)
            Scanner entrada = new Scanner(cliente.getInputStream());
            // Canal de saida (mensagens retransmitidas ao cliente), com auto-flush
            PrintStream saida = new PrintStream(cliente.getOutputStream(), true);

            System.out.println("Mensagens do Cliente:");
            while (entrada.hasNextLine())
            {
                String msg = entrada.nextLine();
                System.out.println(msg);  // Imprime na tela a mensagem de entrada
                saida.println(msg);       // Retransmite a mensagem de volta ao cliente
            }

            System.out.println("Servidor finalizado!");
            saida.close();
            entrada.close();
            servidor.close();
        }
        catch (IOException ex)
        {
            System.out.println("Erro ao criar o servidor!");
        }
    }
}
