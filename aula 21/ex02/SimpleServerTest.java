/* Criar uma interface grafica para a classe SimpleClientTest.java, atraves dos modelos de layout
 * estudados anteriormente nesta disciplina (flowlayout, borderlayout, gridlayout ou os
 * automaticamente gerados pela IDE NetBeans), eliminando toda e qualquer forma de comunicacao com
 * o usuario atraves do console e/ou por interface Swing como foi fornecida, criando nessa nova
 * interface grafica os campos especificos para as mensagens a serem enviadas ao servidor,
 * respondidas ao cliente e para as mensagens de status da comunicacao, alem da inclusao dos botoes
 * e suas funcionalidades para Enviar, Limpar e Sair, na propria interface.
 */
// Servidor que responde ao cliente com a mesma mensagem recebida
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleServerTest {
    public static final int PORTA = 3334;

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(PORTA);
            System.out.println("Servidor iniciado na porta " + PORTA);

            Socket cliente = servidor.accept();
            System.out.println("Cliente do IP: " + cliente.getInetAddress().getHostAddress()
                    + " conectado ao servidor");

            Scanner entrada = new Scanner(cliente.getInputStream());
            PrintStream saida = new PrintStream(cliente.getOutputStream(), true);
            while (entrada.hasNextLine()) {
                String msg = entrada.nextLine();
                System.out.println("Recebida: " + msg);
                saida.println(msg); // retransmite ao cliente
            }

            System.out.println("Servidor finalizado!");
            entrada.close();
            servidor.close();
        } catch (IOException ex) {
            System.out.println("Erro ao criar o servidor!");
        }
    }
}
