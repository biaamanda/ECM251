/*
 * Aula 20 - Exercicio 1
 *
 * Enunciado:
 * a. Criar um projeto denominado SimpleClientServer na IDE de sua preferencia e digitar as classes
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
 *
 * As respostas dos itens b a g estao em ../RESPOSTAS.md
 */
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

public class SimpleServerTest
{  // Especifica endereco do IP local para o servidor
      public static final String ENDERECO = "127.0.0.1";
   // Especifica porta livre do computador para o servidor escutar
      public static final int PORTA = 3334;

   public static void   main(String args[])
   {  try
      {  // Cria servico de escuta da porta especificada via socket no servidor
         ServerSocket   servidor = new ServerSocket(PORTA);
         System.out.println("Servidor iniciado na porta " + PORTA);
         // Cria canal de comunicacao para a conexao de um cliente (servico)
         Socket   cliente = servidor.accept();
         // Apresenta endereco do cliente conectado ao servidor
         System.out.println("Cliente do IP: " +
            cliente.getInetAddress().getHostAddress() +
            " conectado ao servidor");
         // Obtem a entrada do canal (socket)
         Scanner entrada = new Scanner(cliente.getInputStream());
         // Escutando as mensagens do cliente que chegam ao servidor
         System.out.println("Mensagens do Cliente:");
         while(entrada.hasNextLine())  // Enquanto a entrada tiver uma próxima linha...
         {  System.out.println(entrada.nextLine());   // Imprime na tela a mensagem de entrada
         }
         // Fechando o canal de entrada do servidor e o servico de conexao ao servidor
         System.out.println("Servidor finalizado!");
         entrada.close();  // Fecha o canal de comunicacao
         servidor.close(); // Fecha o servico
      }
      catch(IOException ex)
      {  System.out.println("Erro ao criar o servidor!");
      }
   }
}
