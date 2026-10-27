// Aula 21 - Exercicio 1: cliente fornecido (envia mensagens digitadas ao servidor)
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class SimpleClientTest {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket(SimpleServerTest.ENDERECO, SimpleServerTest.PORTA);
            System.out.println("Cliente: " + SimpleServerTest.ENDERECO + ":" + SimpleServerTest.PORTA
                    + " conectado ao servidor!");
            PrintStream saida = new PrintStream(cliente.getOutputStream());

            String msg = JOptionPane.showInputDialog("Digite mensagem (ou <sair> para encerrar)");
            while (!msg.equalsIgnoreCase("sair")) {
                System.out.println(msg);
                saida.println(msg);
                msg = JOptionPane.showInputDialog("Digite mensagem (ou <sair> para encerrar)");
            }

            System.out.println("Cliente se desconectou do servidor!");
            cliente.close();
        } catch (IOException ex) {
            System.out.println("Falha na comunicacao: " + ex.getMessage());
        }
    }
}
