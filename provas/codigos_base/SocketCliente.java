import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

/*
 * ============================================================================
 *  SocketCliente  —  "embrulho" de UMA conexão
 * ============================================================================
 *  O QUE É: junta numa classe só o Socket + o canal de leitura (entrada)
 *           + o canal de escrita (saida) de UMA conexão.
 *
 *  QUEM USA: os DOIS lados.
 *    - Cliente: a conexão dele com o servidor.
 *    - Servidor: um objeto destes para CADA cliente conectado
 *      (cada thread tem os próprios streams -> um cliente não atrapalha o outro).
 *
 *  MANTER: SEMPRE, em qualquer prova de chat/cliente-servidor. Não precisa mudar.
 *  (Mesma ideia do ClientSocket da Aula 22 e do SocketCliente da Aula 23.)
 * ============================================================================
 */
public class SocketCliente {

    private final Socket socket;            // a conexão (IP + porta dos dois lados)
    private final BufferedReader entrada;   // para LER o que chega
    private final PrintWriter saida;        // para ESCREVER o que sai

    /*
     * Construtor
     * FAZ: recebe um Socket já conectado e cria a entrada e a saída.
     *      O "true" do PrintWriter é o autoFlush: sem ele a mensagem fica
     *      presa no buffer e não chega no outro lado.
     * MANTER: SEMPRE.
     * O println mostra no console do servidor quem conectou (status, requisito II).
     * Pode apagar o println se a prova não pedir status no console.
     */
    public SocketCliente(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("Conectado com " + socket.getRemoteSocketAddress() + "!");
        this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.saida = new PrintWriter(socket.getOutputStream(), true);
    }

    /*
     * getRemoteSocketAddress
     * FAZ: devolve o IP:porta do OUTRO lado (ex.: /127.0.0.1:52344).
     * MANTER: SEMPRE que o servidor precisar identificar o remetente
     *         ("com devida identificação dos remetentes" — todas as P4).
     */
    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    /*
     * getMessage
     * FAZ: lê UMA linha. É BLOQUEANTE: a thread fica parada aqui até chegar algo.
     *      Devolve null quando o outro lado desconectou (ou deu erro de rede).
     *      É esse null que avisa "o cliente saiu" (no servidor) ou
     *      "o servidor caiu" (no cliente).
     * MANTER: SEMPRE.
     */
    public String getMessage() {
        try {
            return entrada.readLine();
        } catch (IOException ex) {
            return null;
        }
    }

    /*
     * sendMsg
     * FAZ: envia UMA linha. Devolve false se o envio falhou (o outro lado caiu).
     * MANTER: SEMPRE.
     */
    public boolean sendMsg(String msg) {
        saida.println(msg);
        return !saida.checkError();
    }

    /*
     * close
     * FAZ: fecha entrada, saída e socket (libera os recursos).
     * MANTER: SEMPRE.
     */
    public void close() {
        try {
            entrada.close();
            saida.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Erro ao fechar o socket: " + ex.getMessage());
        }
    }
}
