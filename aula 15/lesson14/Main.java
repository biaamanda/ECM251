import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        ResourceBundle bundle = chooseLanguage();
        new LoginScreen(bundle);
    }

    private static ResourceBundle chooseLanguage() {
        String[] options = {"Português", "English"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Selecione o idioma / Select your language:",
                "ECM251",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        Locale locale = (choice == 1) ? Locale.US : new Locale("pt", "BR");
        return ResourceBundle.getBundle("Ex2", locale);
    }
}
