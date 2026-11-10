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

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

/* SocketCliente: embrulha o Socket da conexão com o servidor, oferecendo
   métodos simples para enviar (sendMsg) e receber (getMessage) linhas de texto.
   É a mesma classe do Exercício 1, mas sem nenhum System.out: como o cliente
   agora é gráfico, nada pode ser escrito no console. Os erros são devolvidos
   para quem chamou (exceção ou valor de retorno), e a interface os mostra. */
public class SocketCliente {
    private final Socket socket;
    private final BufferedReader entrada;
    private final PrintWriter saida;

    public SocketCliente(final Socket socket) throws IOException {
        this.socket = socket;
        this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.saida = new PrintWriter(socket.getOutputStream(), true);
    }

    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    public SocketAddress getLocalSocketAddress() {
        return socket.getLocalSocketAddress();
    }

    public void close() {
        try {
            entrada.close();
            saida.close();
            socket.close();
        } catch (IOException ex) {
            // a conexão já estava fechada; nada a fazer
        }
    }

    public String getMessage() {
        try {
            return entrada.readLine();
        } catch (IOException ex) {
            return null;
        }
    }

    public boolean sendMsg(String msg) {
        saida.println(msg);
        return !saida.checkError();
    }
}
