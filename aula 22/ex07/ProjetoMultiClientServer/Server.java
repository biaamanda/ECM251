/* a. Criar um projeto denominado ProjetoMultiClientServer na IDE de sua preferência e digitar as classes fornecidas ClientSocket.java, Server.java e Client.java;
 *
 * b. Executar, somente, a classe Client.java, verificar e registrar o que ocorre. Explique, em detalhes, o ocorrido;
 *
 * c. Executar a classe Server.java, verificar e registrar o que ocorre. Explique, em detalhes, o ocorrido;
 *
 * d. Sem encerrar a execução da classe Server.java, executar, a seguir, a primeira instância da classe Client.java, digitando algumas mensagens no seu respectivo console, uma após a outra e, por fim, optar por sair. Verificar e registrar o que ocorre. Explique, em detalhes, o ocorrido;
 *
 * e. Sem encerrar a execução da classe Server.java, executar, a seguir, novamente, a primeira instância da classe Client.java, digitando algumas mensagens no seu respectivo console, uma após a outra e não optar por sair, ainda. Verificar e registrar o que ocorre. Explique, em detalhes, o ocorrido;
 *
 * f. Sem encerrar a execução da classe Server.java, executar, a seguir, a segunda instância da classe Client.java, digitando algumas mensagens no seu respectivo console, uma após a outra e não optar por sair, ainda. Verificar e registrar o que ocorre. Explique, em detalhes, o ocorrido;
 *
 * g. Sem encerrar a execução da classe Server.java, executar, a seguir, a terceira instância da classe Client.java, digitando algumas mensagens no seu respectivo console, uma após a outra e não optar por sair, ainda. Verificar e registrar o que ocorre. Explique, em detalhes, o ocorrido;
 *
 * h. Sem encerrar a execução das classe Server.java e das classes Client.java instâncias 1, 2 e 3, digitar outra rodada de mensagens nos respectivos consoles das classes Client.java instâncias 1, 2 e 3, uma após a outra e não optar por sair, ainda, de nenhuma delas. Verificar e registrar o que ocorre. Explique, em detalhes, o ocorrido;
 *
 * i. Encerrar a execução das classes Client.java instâncias 1, 2 e 3, digitando <sair> no respectivo console, para cada uma delas, na ordem dada. Verificar e registrar o que ocorre. Explique, em detalhes, o ocorrido;
 *
 * j. Sem encerrar a execução da classe Server.java, executar, a seguir, uma segunda instância da classe Server.java. Verificar e registrar o que ocorre. Explique, em detalhes, o ocorrido.
 */
import java.io.IOException;
import java.net.ServerSocket;

public class Server
{  public static final String ADDRESS = "127.0.0.1";  // IP Address local do servidor
   public static final int PORT = 4000; //ou 3334
   private ServerSocket serverSocket;

   public void start() throws IOException
   {  serverSocket = new ServerSocket(PORT);
      System.out.println("Servidor iniciado na porta: " + PORT);
      clientConnectionLoop();
   }

   private void clientConnectionLoop() throws IOException
   {  System.out.println("Aguardando conexao de um cliente!");
      do
      {  ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
         new Thread(() -> clientMessageLoop(clientSocket)).start();  // Criando uma Expressão Lambda
      }while(true);
   }

   public void clientMessageLoop(ClientSocket clientSocket)
   {  String msg;
      try
      {  while((msg = clientSocket.getMessage()) != null && !msg.equalsIgnoreCase("sair"))
         {  System.out.printf("Mensagem recebida do cliente %s: %s\n", clientSocket.getRemoteSocketAddress(), msg);
         }
      }
      finally
      {  clientSocket.close();
      }
   }

   public static void main(String args[])
   {  System.out.println("*v*v*v* CONSOLE DO SERVIDOR *v*v*v*");
      try
      {  Server server = new Server();
         server.start();
      }
      catch(IOException ex)
      {  System.out.println("Erro ao iniciar o servidor: " + ex.getMessage());
      }
      System.out.println("Servidor finalizado!");
   }
}
