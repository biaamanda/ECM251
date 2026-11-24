import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * ============================================================================
 *  Servidor  —  chat com no máximo 2 clientes (console)
 * ============================================================================
 *  O QUE É: abre a porta, aceita até 2 clientes, cria UMA THREAD por cliente
 *           e repassa as mensagens de um para o outro.
 *
 *  POR QUE THREADS: accept() e readLine() são BLOQUEANTES. Com uma thread só,
 *  o servidor ficaria preso esperando um cliente e não atenderia o outro.
 *  A thread main só aceita conexões; cada cliente conversa na sua thread.
 *
 *  ETIQUETAS dos comentários:
 *    [SEMPRE]  -> manter em qualquer prova de chat
 *    [2024+]   -> só se a prova pedir aviso de "aguardando / comunicação ativa"
 *    [2025]    -> só se a prova pedir Cliente #1/#2 e desconexão em cadeia
 *
 *  "PALAVRAS DE CONTROLE" que o servidor manda para o cliente:
 *    SERVIDOR_CHEIO [SEMPRE]  já há 2 clientes -> o cliente avisa e fecha
 *    AGUARDANDO     [2024+]   você é o único -> espere o segundo
 *    CONECTADO      [2024+]   os dois estão conectados -> conversa liberada
 *    ID:n           [2025]    você é o cliente #n
 *    DESCONECTAR    [2025]    o outro saiu -> feche também
 *  (o texto tem que ser IDÊNTICO no Servidor e no Cliente)
 * ============================================================================
 */
public class Servidor {

    // [SEMPRE] endereço e porta. O Cliente usa os mesmos valores.
    public static final String ENDERECO = "127.0.0.1";
    public static final int PORT = 4000;
    // [SEMPRE] limite de clientes. Se a prova pedir outro número, mude só aqui.
    public static final int MAX_CLIENTES = 2;

    // [SEMPRE] a "porta aberta" onde os clientes batem para conectar
    private ServerSocket serverSocket;
    // [SEMPRE] clientes conectados agora (para repassar mensagens e contar vagas)
    private final LinkedList<SocketCliente> clients = new LinkedList<>();

    /*
     * main
     * FAZ: cria o servidor e chama iniciar().
     *      Se a porta já estiver em uso (já existe outro servidor), cai no catch.
     * MANTER: [SEMPRE] — é o ponto de entrada (necessário para o .jar).
     * CASO DE TESTE: "2º servidor -> informar que não conseguiu executar".
     */
    public static void main(String[] args) {
        System.out.println("*v*v*v* CONSOLE DO SERVIDOR *v*v*v*");
        try {
            new Servidor().iniciar();
        } catch (IOException ex) {
            System.out.println("Erro: não foi possível iniciar o servidor. Já existe um servidor em funcionamento na porta "
                    + PORT + " (" + ex.getMessage() + ")");
        }
        System.out.println("Servidor finalizado!");
    }

    /*
     * iniciar
     * FAZ: abre a porta (new ServerSocket), avisa no console e entra no loop de conexões.
     * MANTER: [SEMPRE].
     * CASO DE TESTE: "servidor sobe e aguarda a conexão de até dois clientes".
     */
    public void iniciar() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta " + PORT + ". Aguardando a conexão de até "
                + MAX_CLIENTES + " clientes...");
        aguardarConexoes();
    }

    /*
     * aguardarConexoes
     * FAZ: loop infinito em accept(). Para cada conexão nova:
     *      - se já houver MAX_CLIENTES -> recusa;
     *      - senão -> registra na lista, cria a thread dele e avisa os clientes.
     * MANTER: [SEMPRE] o accept, o if do limite, o add e o new Thread.
     *         O bloco de avisos tem partes [2024+] e [2025] (marcadas abaixo).
     * synchronized(this): as threads dos clientes também mexem na lista;
     *         assim uma não atrapalha a outra. Pode manter sempre.
     */
    private void aguardarConexoes() throws IOException {
        while (true) {
            SocketCliente cliente = new SocketCliente(serverSocket.accept());   // BLOQUEIA até alguém conectar
            synchronized (this) {
                if (clients.size() >= MAX_CLIENTES) {
                    recusarCliente(cliente);                                     // [SEMPRE] 3º cliente
                    continue;
                }
                clients.add(cliente);                                            // [SEMPRE] registra
                new Thread(() -> atenderCliente(cliente)).start();               // [SEMPRE] thread dele

                int numero = clients.size();                                     // 1 ou 2
                System.out.println("Cliente #" + numero + " (" + cliente.getRemoteSocketAddress() + ") conectado!");

                // [2025] diz ao cliente se ele é o #1 ou o #2. Apague se a prova não pedir número.
                cliente.sendMsg("ID:" + numero);

                // [2024+] avisos de status. Apague este if/else se a prova não pedir.
                if (numero == 1) {
                    cliente.sendMsg("AGUARDANDO");                               // só ele está conectado
                } else {
                    for (SocketCliente c : clients) {
                        c.sendMsg("CONECTADO");                                  // avisa OS DOIS
                    }
                    System.out.println("Dois clientes conectados: comunicação entre eles ativa.");
                }
            }
        }
    }

    /*
     * recusarCliente
     * FAZ: avisa o cliente que o servidor está cheio, fecha a conexão dele
     *      e registra no console.
     * MANTER: [SEMPRE] que houver limite de clientes.
     * CASO DE TESTE: "3º cliente -> não conseguiu permanecer aberto".
     */
    private void recusarCliente(SocketCliente cliente) {
        cliente.sendMsg("SERVIDOR_CHEIO");
        cliente.close();
        System.out.println("Conexão recusada: já há " + MAX_CLIENTES + " clientes.");
    }

    /*
     * atenderCliente
     * FAZ: roda DENTRO da thread de cada cliente. Lê as mensagens em loop
     *      (getMessage() até vir null ou "sair"), mostra no console com o
     *      remetente e repassa ao outro. No finally, remove o cliente.
     * MANTER: [SEMPRE].
     * REQUISITO II: "console do servidor mostra as mensagens com o remetente".
     *      Se a prova disser "sem ecoar no servidor" (2025) e o professor
     *      confirmar que o console NÃO deve mostrar o texto, apague o println.
     */
    private void atenderCliente(SocketCliente cliente) {
        String msg;
        try {
            while ((msg = cliente.getMessage()) != null) {   // null = o cliente caiu
                if ("sair".equalsIgnoreCase(msg)) break;     // o cliente clicou em Sair
                System.out.println("<- Cliente " + cliente.getRemoteSocketAddress() + ": " + msg);
                repassarMensagem(cliente, msg);
            }
        } finally {
            removerCliente(cliente);                         // sempre libera a vaga
        }
    }

    /*
     * repassarMensagem
     * FAZ: envia a mensagem para TODOS os clientes MENOS o remetente
     *      (sem eco na origem). Se o envio para alguém falhar, tira da lista.
     * MANTER: [SEMPRE].
     * CASO DE TESTE: "mensagem do 1 aparece no 2, sem ecoar na origem" (e vice-versa).
     */
    private synchronized void repassarMensagem(SocketCliente remetente, String msg) {
        Iterator<SocketCliente> it = clients.iterator();
        while (it.hasNext()) {
            SocketCliente c = it.next();
            if (!c.equals(remetente)) {
                if (!c.sendMsg("Cliente " + remetente.getRemoteSocketAddress() + ": " + msg)) {
                    it.remove();
                }
            }
        }
    }

    /*
     * removerCliente
     * FAZ: tira o cliente da lista, fecha a conexão e mostra no console.
     *      Sem isso a vaga nunca é liberada.
     * MANTER: [SEMPRE] o remove, o close e o println.
     *   - "if (!clients.remove(...)) return;" evita repetir tudo quando o mesmo
     *     cliente é removido duas vezes (acontece com o DESCONECTAR). Pode manter sempre.
     *   - O for com DESCONECTAR é [2025]: apague se a prova não pedir que o
     *     outro cliente feche junto.
     */
    private synchronized void removerCliente(SocketCliente cliente) {
        if (!clients.remove(cliente)) return;
        cliente.close();
        System.out.println("Cliente " + cliente.getRemoteSocketAddress() + " desconectou. Conectados: " + clients.size());

        // [2025] quem sobrou também tem que sair. Percorre uma CÓPIA da lista
        // para não dar erro se ela mudar durante o for.
        for (SocketCliente c : new ArrayList<>(clients)) {
            c.sendMsg("DESCONECTAR");
            System.out.println("Pedindo ao cliente " + c.getRemoteSocketAddress() + " que se desconecte.");
        }
    }
}
