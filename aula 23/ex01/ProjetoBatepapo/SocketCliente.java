/* 1. No tópico Cliente-Servidor Java Sockets Bate-Papo, criar um
projeto denominado ProjetoBatepapo, na IDE de sua
preferência, digitar as classes fornecidas SocketClient.java,
ServidorBatepapo.java e ClienteBatepapo.java, distribuídas
entre as páginas 11 à 21 deste material, executar, analisar,
concluir e registrar o funcionamento das mesmas. */

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

/* SocketCliente: embrulha o Socket de uma conexão, oferecendo métodos simples
   para enviar (sendMsg) e receber (getMessage) linhas de texto.
   É usada pelos dois lados: o servidor cria uma para cada cliente conectado,
   e o cliente cria uma para a sua conexão com o servidor. */

public class SocketCliente {   // A conexão e os fluxos de leitura e escrita sobre ela
    private final Socket socket;
    private final BufferedReader entrada;
    private final PrintWriter saida;

    // Construtor: guarda o socket, avisa no console com quem conectou e cria
    // a entrada (leitura de linhas) e a saída (envio com autoflush)
    public SocketCliente(final Socket socket) throws IOException {   
        this.socket = socket;
        System.out.println("Conectado com " + socket.getRemoteSocketAddress() + "!");
        this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.saida = new PrintWriter(socket.getOutputStream(), true);
    }

    // Endereço (IP:porta) do outro lado da conexão (no cliente, o servidor; no servidor, o cliente)
    public SocketAddress getRemoteSocketAddress() {   
        return socket.getRemoteSocketAddress();
    }

    // Fecha a entrada, a saída e o socket
    public void close() {   
        try {   
            entrada.close();
            saida.close();
            socket.close();
        } catch(IOException ex) {   
            System.out.println("Erro o fechar o socket: " + ex.getMessage());
        }
    }

    // Espera (bloqueante) e devolve a próxima linha recebida; null se a conexão acabou
    public String getMessage() {   
        try {   
            return entrada.readLine();
        } catch(IOException ex) {   
            return null;
        }
    }

    // Envia uma linha; devolve false se houve erro de escrita
    public boolean sendMsg(String msg) {   
        saida.println(msg);
        return !saida.checkError();
    }
}
