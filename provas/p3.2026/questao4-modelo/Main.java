// Classe de execucao ("Main.java") -- a que vai no Main-Class do .jar

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Swing deve ser criado na thread de eventos
        SwingUtilities.invokeLater(() -> new TelaPrincipal());
    }
}
