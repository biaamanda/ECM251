// Aula 21 - Exercicio Desafio: servidor que atende varios clientes ao mesmo tempo.
// Cada cliente aceito ganha uma thread propria, que le e responde so as mensagens dele.
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTest {
    public static final int PORTA = 3334;

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(PORTA);
            System.out.println("Servidor iniciado na porta " + PORTA);

            int contador = 0;
            while (true) { // continua aceitando novos clientes
                Socket cliente = servidor.accept();
                int id = ++contador;
                System.out.println("Cliente " + id + " (" + cliente.getInetAddress().getHostAddress()
                        + ") conectado");
                new Thread(() -> atende(cliente, id)).start();
            }
        } catch (IOException ex) {
            System.out.println("Erro ao criar o servidor!");
        }
    }

    private static void atende(Socket cliente, int id) {
        try {
            Scanner entrada = new Scanner(cliente.getInputStream());
            PrintStream saida = new PrintStream(cliente.getOutputStream(), true);
            while (entrada.hasNextLine()) {
                String msg = entrada.nextLine();
                System.out.println("Cliente " + id + ": " + msg);
                saida.println(msg); // responde so ao cliente que enviou
            }
            cliente.close();
        } catch (IOException ex) {
            // conexao perdida
        }
        System.out.println("Cliente " + id + " desconectado");
    }
}
