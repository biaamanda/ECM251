/* 1. No tópico Cliente-Servidor Java Sockets Bate-Papo, criar um
projeto denominado ProjetoBatepapo, na IDE de sua
preferência, digitar as classes fornecidas SocketClient.java,
ServidorBatepapo.java e ClienteBatepapo.java, distribuídas
entre as páginas 11 à 21 deste material, executar, analisar,
concluir e registrar o funcionamento das mesmas. */

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/* ClienteBatepapo: cliente do bate-papo, via console. Conecta ao servidor e usa
   duas threads: a principal lê o teclado e envia as mensagens, e outra (run)
   fica esperando e mostrando as mensagens que os outros clientes enviam. */
public class ClienteBatepapo implements Runnable {  
   // Conexão com o servidor e leitor do teclado
   private SocketCliente clientSocket;
   private Scanner scanner;

   // Construtor: prepara a leitura do teclado
   public ClienteBatepapo() {  
      scanner = new Scanner(System.in);
   }

   // Conecta ao servidor, inicia a thread de leitura e entra no laço de envio;
   // no fim, fecha a conexão
   public void start() throws IOException {  
      try {  
         clientSocket = new SocketCliente(new Socket(ServidorBatepapo.ADDRESS, ServidorBatepapo.PORT));
         new Thread(this).start();
         messageLoop();
      } finally {  
         clientSocket.close();
      }
   }

   // Thread de leitura: mostra cada mensagem recebida do servidor e repete o prompt
   @Override
   public void run() {  
      String msg;
      while((msg = clientSocket.getMessage()) != null) {  
         System.out.printf("\n-> %s\n", msg);
         System.out.print("Digite uma mensagem (ou <sair> para finalizar):\n<- ");
      }
   }

   // Lê o teclado e envia cada linha ao servidor, até digitar "sair"
   private void messageLoop() throws IOException {  
      String msg;
      System.out.println("Digite uma mensagem (ou <sair> para finalizar):");
      do {  
         System.out.print("<- ");
         msg = scanner.nextLine();
         clientSocket.sendMsg(msg);
      } while(!msg.equalsIgnoreCase("sair"));
   }

   // Ponto de entrada: cria e inicia o cliente
   public static void main(String args[]) {  
      System.out.println("*v*v*v* CONSOLE DO CLIENTE *v*v*v*");
      try {  
         ClienteBatepapo client = new ClienteBatepapo();
         client.start();
      } catch(IOException ex) {  
         System.out.println("Erro ao iniciar o cliente: " + ex.getMessage());
      }
      System.out.println("Cliente finalizado!");
   }
}
