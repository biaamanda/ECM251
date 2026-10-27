// Aula 21 - Exercicio 2: servidor que responde ao cliente com a mesma mensagem recebida
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
