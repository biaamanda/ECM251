/* a. Criar um projeto denominado SimpleClientServer na IDE de sua preferencia e digitar as classes
 *    fornecidas SimpleServerTest.java e SimpleClientTest.java;
 *
 * b. Executar, somente, a classe SimpleClientTest.java, verificar e registrar o que ocorre.
 *    Explique, em detalhes, o que ocorre;
 *
 * c. Executar a classe SimpleServerTest.java, verificar e registrar o que ocorre. Explique, em
 *    detalhes, o que ocorre;
 *
 * d. Sem encerrar a execucao da classe SimpleServerTest.java, executar, a seguir, a classe
 *    SimpleClientTest.java, digitando algumas mensagens na interface com o usuario, uma apos a
 *    outra e, por fim, optar por sair. Verificar e registrar o que ocorre. Explique, em detalhes,
 *    o que ocorre;
 *
 * e. Sem encerrar a execucao da classe SimpleServerTest.java, executar, a seguir, a classe
 *    SimpleClientTest.java, digitando uma mensagem, apenas, na interface com o usuario e nao optar
 *    por sair. Sem encerrar a execucao da classe SimpleServerTest.java, nem da primeira instancia
 *    de execucao da classe SimpleClientTest.java, executar outra instancia da classe
 *    SimpleClientTest.java. Digitar algumas mensagens na segunda instancia da classe
 *    SimpleClientTest.java, sem sair dela, verificar e registrar o que ocorre. Repetir a digitacao
 *    para a primeira instancia da classe SimpleClientTest.java, sem sair dela tambem. Explique, em
 *    detalhes, o que ocorre;
 *
 * f. Encerrar a execucao da segunda instancia da classe SimpleClientTest.java, digitando algumas
 *    mensagens na primeira instancia da classe SimpleClientTest.java. Verificar e registrar o que
 *    ocorre. Explique, em detalhes, o que ocorre;
 *
 * g. Executar uma segunda instancia da classe SimpleServerTest.java, ainda com a primeira
 *    instancia dessa classe em execucao. Verificar e registrar o que ocorre. Explique, em
 *    detalhes, o que ocorre.
 */
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class SimpleClientTest {  // Criando a variavel de conexao do tipo Socket
   private static Socket cliente;   // Comunicacao com o servidor
   public static void main(String args[]) {  
      try {  
         String msg;
         iniciaCliente();  // cria cliente e inicia conexao com o servidor
         System.out.println("Mensagens para o servidor:");
         do {  msg = JOptionPane.showInputDialog("Digite mensagem (ou <sair> para encerrar)");
            if(!msg.equalsIgnoreCase("sair"))
            {  System.out.println(msg);
               enviaMensagem(msg);
            }
         } while(!msg.equalsIgnoreCase("sair"));
         System.out.println("Cliente se desconectou do servidor!");
      }
      catch(IOException ex)
      {  System.out.println("Falha na comunicacao: " + ex.getMessage());
      }
   }

   private static void iniciaCliente() throws IOException {  // Cria um cliente com IP e Porta para a comunicacao com o servidor
      cliente = new Socket(SimpleServerTest.ENDERECO, SimpleServerTest.PORTA);
      System.out.println("Cliente: " + SimpleServerTest.ENDERECO +
         ":" + SimpleServerTest.PORTA + " conectado ao servidor!");
   }

   private static void enviaMensagem(String msg) throws IOException {  // Objeto para enviar mensagem ao servidor
      PrintStream saida = new PrintStream(cliente.getOutputStream());
      // Envia mensagem ao servidor
      saida.println(msg);
   }
}
