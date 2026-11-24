import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.Iterator;

public class Servidor {

    // Endereço e porta: o Cliente usa essas mesmas constantes para se conectar
    public static final String ENDERECO = "127.0.0.1";
    public static final int PORT = 4000;
    // Regra da prova: só 2 clientes por vez
    public static final int MAX_CLIENTES = 2;

    // "Porta aberta" do servidor, onde os clientes batem para conectar
    private ServerSocket serverSocket;
    // Clientes conectados no momento (usada para repassar mensagens e contar vagas)
    private final LinkedList<SocketCliente> clients = new LinkedList<>();

    /**
     * Ponto de entrada (necessário para rodar e para o .jar).
     * RAZÃO: cria o servidor e trata o erro de porta em uso.
     * Caso de teste 3: se já houver um servidor na porta, cai no catch
     * e deve informar que não conseguiu iniciar.
     */
    public static void main(String[] args) {
        // criar new Servidor() e chamar iniciar(); no catch, mostrar o erro
        System.out.println("*v*v*v* CONSOLE DO SERVIDOR *v*v*v*");
        try {  
            Servidor server = new Servidor();
            server.iniciar();
        } catch(IOException ex) { 
            System.out.println("Erro ao iniciar o servidor: " + ex.getMessage());
        }
        System.out.println("Servidor finalizado!");
   }

    /**
     * RAZÃO: abrir a porta (new ServerSocket(PORTA)) e avisar no console
     * que o servidor está rodando e aguardando até 2 clientes (caso de teste 2).
     * Depois disso, entra no loop de conexões.
     */
    public void iniciar() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta: " + PORT);
        aguardarConexoes();
    }

    /**
     * RAZÃO: loop infinito que fica em serverSocket.accept() esperando clientes.
     * Para cada conexão nova:
     *  - se já houver MAX_CLIENTES -> recusarCliente(...)
     *  - senão -> adiciona na lista e cria uma Thread para atenderCliente(...)
     * A thread principal fica SÓ aceitando conexões; quem conversa são as outras threads.
     */
    private void aguardarConexoes() throws IOException {
       System.out.println("Aguardando a conexão de até 2 clientes");
        while(true) {
            SocketCliente clientSocket = new SocketCliente(serverSocket.accept());
            if (clients.size() >= MAX_CLIENTES) {
                recusarCliente(clientSocket);                       // 3º cliente
            } else {
                System.out.println("Cliente " + clientSocket.getRemoteSocketAddress() + " conectado!");
                clients.add(clientSocket);                          // ① registra
                new Thread(() -> atenderCliente(clientSocket)).start(); // ② cria a thread dele

            if (clients.size() == 1) {                          // ③ avisa
                clientSocket.sendMsg("AGUARDANDO");
            } else {
                for (SocketCliente c : clients) {
                    c.sendMsg("CONECTADO");
                }
                System.out.println("Dois clientes conectados: comunicação entre eles ativa.");
            }
            }
        }
    }
    /**
     * RAZÃO: caso de teste 5 (3º cliente).
     * Avisa o cliente que o servidor está cheio, fecha a conexão
     * e registra no console que a conexão foi recusada.
     */
    private void recusarCliente(SocketCliente cliente) {
        cliente.sendMsg("SERVIDOR_CHEIO"); 
        cliente.close(); 
        System.out.println("Conexão recusada: já há 2 clientes.");
    }

    /**
     * RAZÃO: é o que roda DENTRO da thread de cada cliente.
     * Lê mensagens desse cliente em loop (getMessage() até vir null ou "sair"),
     * mostra no console com a identificação do remetente e repassa ao outro cliente.
     * No finally: removerCliente(...) para liberar a vaga.
     */
    private void atenderCliente(SocketCliente cliente) {
        String msg;
        try {
            while ((msg = cliente.getMessage()) != null) { 
                if ("sair".equalsIgnoreCase(msg)) break; 
                System.out.println("<- Cliente " + cliente.getRemoteSocketAddress() + ": " + msg); 
                repassarMensagem(cliente, msg); 
            }
        } 
                finally { 
                    removerCliente(cliente); //sai do loop -> vibera a vaga
                }
    }

    /**
     * Envia a mensagem para todos os clientes MENOS o remetente (sem eco na origem).
     */
    private void repassarMensagem(SocketCliente remetente, String msg) {
        Iterator<SocketCliente> iterator = clients.iterator();
        while(iterator.hasNext()) {  
            SocketCliente clientSocket = iterator.next();
        if(!remetente.equals(clientSocket)) {  
            if(!clientSocket.sendMsg("Cliente " + remetente.getRemoteSocketAddress() + ": " + msg)) {  iterator.remove();
            }
         }
      }
    }

    /**
     * RAZÃO: quando um cliente sai/cai, tira ele da lista, fecha o socket
     * e mostra no console (status). Sem isso a vaga nunca libera.
     */
    private void removerCliente(SocketCliente cliente) {
        clients.remove(cliente); 
        cliente.close(); 
        System.out.println("Cliente " + cliente.getRemoteSocketAddress() + " desconectou. Conectados: " + clients.size());
    }
}
