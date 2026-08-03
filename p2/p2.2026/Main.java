import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public final class Main extends JFrame implements ActionListener {

    private JButton readDBButton;
    private JButton saveDBButton;
    private JButton exitButton;

    public Main() {
        super("Banco de Dados");

        Container rootContainer = getContentPane();
        rootContainer.setLayout(new BorderLayout());

        JPanel subjectsContainer = new JPanel();
        subjectsContainer.setLayout(new GridLayout(5, 10, 10, 10));

        JPanel buttonsContainer = new JPanel(new FlowLayout());

        readDBButton = new JButton("Ler do banco");
        readDBButton.setActionCommand("READ");
        readDBButton.addActionListener(this);

        saveDBButton = new JButton("Salvar no banco");
        saveDBButton.setActionCommand("SAVE");
        saveDBButton.addActionListener(this);

        exitButton = new JButton("Sair");
        exitButton.setActionCommand("EXIT");
        exitButton.addActionListener(this);

        buttonsContainer.add(readDBButton);
        buttonsContainer.add(saveDBButton);
        buttonsContainer.add(exitButton);

        rootContainer.add(subjectsContainer, BorderLayout.CENTER);
        rootContainer.add(buttonsContainer, BorderLayout.SOUTH);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "READ":
                JOptionPane.showMessageDialog(this, "Lendo do banco de dados...");
                break;

            case "SAVE":
                JOptionPane.showMessageDialog(this, "Salvando no banco de dados...");
                break;

            case "EXIT":
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}