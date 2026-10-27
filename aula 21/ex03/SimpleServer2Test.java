// Aula 21 - Exercicio 3: servidor que mostra a mensagem no console e deixa o usuario
// confirma-la (ENTER) ou edita-la antes de reenvia-la ao cliente.
// Use com o cliente do exercicio 2 (SimpleClientTest.java desta pasta).
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleServer2Test {
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
            Scanner teclado = new Scanner(System.in);
            while (entrada.hasNextLine()) {
                String msg = entrada.nextLine();
                System.out.println("Recebida: " + msg);
                System.out.print("ENTER para confirmar ou digite a nova mensagem: ");
                String editada = teclado.nextLine();
                if (!editada.isEmpty()) {
                    msg = editada;
                }
                saida.println(msg);
                System.out.println("Enviada: " + msg);
            }

            System.out.println("Servidor finalizado!");
            entrada.close();
            servidor.close();
        } catch (IOException ex) {
            System.out.println("Erro ao criar o servidor!");
        }
    }
}
