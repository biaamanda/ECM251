/* 11. Criar uma interface gráfica para a classe ClienteBatepapo.java,
através dos modelos de layouts estudados anteriormente nesta
disciplina (flowlayout, borderlayout, gridlayout ou os
automaticamente gerados pela IDE NetBeans), eliminando toda
e qualquer forma de comunicação com o usuário através do
console e/ou por interface Swing, criando nessa nova interface
gráfica os campos específicos para as mensagens do Cliente que
serão enviadas ao Servidor e para as mensagens de status da
comunicação, além da inclusão dos botões e suas
funcionalidades para Enviar, Limpar e Sair, na própria interface. */

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/* ServidorBatepapo: é o mesmo servidor do Exercício 1, sem alterações no código.
   Fica nesta pasta só para o projeto do Exercício 11 compilar e rodar sozinho:
   o cliente gráfico usa as constantes ADDRESS e PORT para saber onde conectar.
   O servidor continua usando o console, porque o exercício pede a interface
   gráfica apenas para o cliente. Única diferença no console: como ele usa o
   SocketCliente desta pasta (sem System.out), não mostra mais "Conectado com ...". */
public class ServidorBatepapo
{  public static final String ADDRESS = "127.0.0.1";  // IP Address local do servidor
   public static final int PORT = 4000; //ou 3334
   private ServerSocket serverSocket;
   private final List<SocketCliente> clients = new LinkedList<>();

   public void start() throws IOException
   {  serverSocket = new ServerSocket(PORT);
      System.out.println("Servidor iniciado na porta: " + PORT);
      clientConnectionLoop();
   }

   private void clientConnectionLoop() throws IOException
   {  System.out.println("Aguardando conexao de um cliente!");
      while(true)
      {  SocketCliente clientSocket = new SocketCliente(serverSocket.accept());
         clients.add(clientSocket);
         new Thread(() -> clientMessageLoop(clientSocket)).start();  // Expressão Lambda
      }
   }

   private void clientMessageLoop(SocketCliente clientSocket)
   {  String msg;
      try
      {  while((msg = clientSocket.getMessage()) != null)
         {  if("sair".equalsIgnoreCase(msg)) return;
            System.out.printf("<- Cliente %s: %s\n", clientSocket.getRemoteSocketAddress(), msg);
            sendMsgToAll(clientSocket, msg);
         }
      }
      finally
      {  clientSocket.close();
      }
   }

   private void sendMsgToAll(SocketCliente sender, String msg)
   {  Iterator<SocketCliente> iterator = clients.iterator();
      while(iterator.hasNext())
      {  SocketCliente clientSocket = iterator.next();
         if(!sender.equals(clientSocket))
         {  if(!clientSocket.sendMsg("Cliente " + sender.getRemoteSocketAddress() + ": " + msg))
            {  iterator.remove();
            }
         }
      }
   }
   public static void main(String args[])
   {  System.out.println("*v*v*v* CONSOLE DO SERVIDOR *v*v*v*");
      try
      {  ServidorBatepapo server = new ServidorBatepapo();
         server.start();
      }
      catch(IOException ex)
      {  System.out.println("Erro ao iniciar o servidor: " + ex.getMessage());
      }
      System.out.println("Servidor finalizado!");
   }
}
