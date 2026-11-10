/* 1. No tópico Cliente-Servidor Java Sockets Bate-Papo, criar um
projeto denominado ProjetoBatepapo, na IDE de sua
preferência, digitar as classes fornecidas SocketClient.java,
ServidorBatepapo.java e ClienteBatepapo.java, distribuídas
entre as páginas 11 à 21 deste material, executar, analisar,
concluir e registrar o funcionamento das mesmas. */

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/* ServidorBatepapo: servidor do bate-papo. Aceita vários clientes ao mesmo tempo,
   criando uma thread para cada um, e repassa cada mensagem recebida para todos
   os outros clientes conectados. Mostra no console as conexões e as mensagens. */
public class ServidorBatepapo {  
   // Endereço e porta onde o servidor escuta (usados também pelo cliente)
   public static final String ADDRESS = "127.0.0.1";  // IP Address local do servidor
   public static final int PORT = 4000; //ou 3334
   // Socket que aceita as conexões e lista dos clientes conectados
   private ServerSocket serverSocket;
   private final List<SocketCliente> clients = new LinkedList<>();

   // Abre a porta e começa a aceitar clientes
   public void start() throws IOException {  
      serverSocket = new ServerSocket(PORT);
      System.out.println("Servidor iniciado na porta: " + PORT);
      clientConnectionLoop();
   }

   // Laço infinito: aceita cada cliente, guarda na lista e cria uma thread só para ele
   private void clientConnectionLoop() throws IOException {  
      System.out.println("Aguardando conexao de um cliente!");
      while(true) {  
         SocketCliente clientSocket = new SocketCliente(serverSocket.accept());
         clients.add(clientSocket);
         new Thread(() -> clientMessageLoop(clientSocket)).start();  // Expressão Lambda
      }
   }

   // Thread de um cliente: imprime cada mensagem e repassa aos outros, até "sair" ou a conexão cair
   private void clientMessageLoop(SocketCliente clientSocket) {  
      String msg;
      try {  
         while((msg = clientSocket.getMessage()) != null) {  
            if("sair".equalsIgnoreCase(msg)) return;
            System.out.printf("<- Cliente %s: %s\n", clientSocket.getRemoteSocketAddress(), msg);
            sendMsgToAll(clientSocket, msg);
         }
      } finally {  
         clientSocket.close();
      }
   }

   // Envia a mensagem para todos os clientes, menos o remetente; remove quem não recebe mais
   private void sendMsgToAll(SocketCliente sender, String msg) {  
      Iterator<SocketCliente> iterator = clients.iterator();
      while(iterator.hasNext()) {  
         SocketCliente clientSocket = iterator.next();
         if(!sender.equals(clientSocket)) {  
            if(!clientSocket.sendMsg("Cliente " + sender.getRemoteSocketAddress() + ": " + msg)) {  iterator.remove();
            }
         }
      }
   }

   // Ponto de entrada: cria e inicia o servidor
   public static void main(String args[]) {  
      System.out.println("*v*v*v* CONSOLE DO SERVIDOR *v*v*v*");
      try {  
         ServidorBatepapo server = new ServidorBatepapo();
         server.start();
      } catch(IOException ex) { 
         System.out.println("Erro ao iniciar o servidor: " + ex.getMessage());
      }
      System.out.println("Servidor finalizado!");
   }
}
