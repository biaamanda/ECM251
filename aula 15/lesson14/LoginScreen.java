import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginScreen extends JFrame implements ActionListener {
    private final Auth auth;
    private final ResourceBundle bundle;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton clearButton;
    private JButton exitButton;

    public LoginScreen(ResourceBundle bundle) {
        super("ECM251 - Login");

        this.bundle = bundle;
        auth = new Auth();

        ImageIcon lockIcon = new ImageIcon(getClass().getResource("lock.png"));

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        passwordField.setEchoChar('*');

        loginButton = new JButton(bundle.getString("login.button.login"));
        exitButton = new JButton(bundle.getString("login.button.exit"));

        JLabel picture = new JLabel(lockIcon, SwingConstants.CENTER);
        picture.setBorder(new EmptyBorder(15, 15, 5, 15));

        JPanel fieldPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        fieldPanel.setBorder(new EmptyBorder(5, 15, 10, 15));
        fieldPanel.add(new JLabel(bundle.getString("login.label.username")));
        fieldPanel.add(usernameField);
        fieldPanel.add(new JLabel(bundle.getString("login.label.password")));
        fieldPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(exitButton);

        Container content = getContentPane();
        content.setLayout(new BorderLayout());
        content.add(picture, BorderLayout.NORTH);
        content.add(fieldPanel, BorderLayout.CENTER);
        content.add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(this);
        exitButton.addActionListener(this);
        getRootPane().setDefaultButton(loginButton);

        setTitle(bundle.getString("login.title"));
        setIconImage(lockIcon.getImage());
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == loginButton) {
            login();
        } else if (source == exitButton) {
            System.exit(0);
        }
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        try {
            Session session = auth.login(username, password);

            if (session == null) {
                JOptionPane.showMessageDialog(this, bundle.getString("login.error.invalid.message"),
                        bundle.getString("login.error.invalid.title"), JOptionPane.WARNING_MESSAGE);
                passwordField.setText("");
                passwordField.requestFocus();
                return;
            }

            dispose();
            new OverviewScreen(session, bundle);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this,
                    bundle.getString("login.error.db.message") + "\n" + exception.getMessage(),
                    bundle.getString("login.error.db.title"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
