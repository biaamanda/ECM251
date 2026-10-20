import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class SimpleClientTest
{  // Criando a variavel de conexao do tipo Socket
   private static Socket cliente;   // Comunicacao com o servidor
   public static void main(String args[])
   {  try
      {  String msg;
         iniciaCliente();  // cria cliente e inicia conexao com o servidor
         System.out.println("Mensagens para o servidor:");
         do
         {  msg = JOptionPane.showInputDialog("Digite mensagem (ou <sair> para encerrar)");
            if(!msg.equalsIgnoreCase("sair"))
            {  System.out.println(msg);
               enviaMensagem(msg);
            }
         }while(!msg.equalsIgnoreCase("sair"));
         System.out.println("Cliente se desconectou do servidor!");
      }
      catch(IOException ex)
      {  System.out.println("Falha na comunicacao: " + ex.getMessage());
      }
   }

   private static void iniciaCliente() throws IOException
   {  // Cria um cliente com IP e Porta para a comunicacao com o servidor
      cliente = new Socket(SimpleServerTest.ENDERECO, SimpleServerTest.PORTA);
      System.out.println("Cliente: " + SimpleServerTest.ENDERECO +
         ":" + SimpleServerTest.PORTA + " conectado ao servidor!");
   }

   private static void enviaMensagem(String msg) throws IOException
   {  // Objeto para enviar mensagem ao servidor
      PrintStream saida = new PrintStream(cliente.getOutputStream());
      // Envia mensagem ao servidor
      saida.println(msg);
   }
}
