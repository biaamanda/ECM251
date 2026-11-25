import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketCliente {

    private final Socket socket;            // a conexão em si (IP + porta dos dois lados)
    private final BufferedReader entrada;   // para LER o que chega
    private final PrintWriter saida;        // para ESCREVER o que sai

    /**
     * RAZÃO: recebe um Socket já conectado e cria os streams de entrada e saída.
     * (PrintWriter com autoFlush = true, senão a mensagem não sai na hora.)
     */
    public SocketCliente(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("Conectado com " + socket.getRemoteSocketAddress() + "!");
        this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.saida = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * RAZÃO: identificar quem é o cliente (IP:porta) nas mensagens do console do servidor.
     */
    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    /**
     * RAZÃO: ler UMA linha. É BLOQUEANTE (espera chegar algo).
     * Retorna null quando o outro lado desconectou -> é assim que se detecta a saída.
     */
    public String getMessage() {
        try {   
            return entrada.readLine();
        } catch(IOException ex) {   
            return null;
        }
    }

    /**
     * RAZÃO: enviar UMA linha. Retorna false se o envio falhou (outro lado caiu).
     */
    public boolean sendMsg(String msg) {
        saida.println(msg);
        return !saida.checkError();
    }

    /**
     * RAZÃO: fechar streams e socket para liberar os recursos.
     */
    public void close() {
        try {   
            entrada.close();
            saida.close();
            socket.close();
        } catch(IOException ex) {   
            System.out.println("Erro o fechar o socket: " + ex.getMessage());
        }
    }
}
