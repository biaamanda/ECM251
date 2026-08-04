import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public final class Main extends JFrame  {

    private static final String DB_FILE = "energyConsumption.db";
    private static final int ENERGY_COUNT = 3;

    private JButton readDBButton;
    private JButton saveDBButton;
    private JButton exitButton;
    private final JTextField[] roomNames = new JTextField[ENERGY_COUNT];
    private final JTextField[][] consumptionFields = new JTextField[ENERGY_COUNT][6];
    private final JTextField[][] costFields = new JTextField[ENERGY_COUNT][3]; 

    public Main() {
        super("CEM – Consumo de Energia Mensal");

        Container rootContainer = getContentPane();
        rootContainer.setLayout(new BorderLayout());

        JPanel informationContainer = new JPanel();
        informationContainer.setLayout(new GridLayout(4, 3,));

        JPanel clientsInfoContainer = new JPanel(); // adiciona clientContainer e valueContainer
        JLabel clientContainer = new JLabel("Nome do Cliente");
        clientsInfoContainer.add(clientContainer);

        JLabel valueContainer = new JLabel("Valor do kWh (R$)");
        clientsInfoContainer.add(valueContainer);


        JPanel costsContainer = new JPanel();
        // adiciona roomContainer, consumptionContainer, costContainer
        JLabel roomContainer = new JLabel("Cômodo");
        costsContainer.add(roomContainer);

        JLabel consumptionContainer = new JLabel("Consumo");
        costsContainer.add(consumptionContainer);

        JLabel costContainer = new JLabel("Custo (R$)");
        costsContainer.add(costContainer);

        informationContainer.add(clientsInfoContainer);
        informationContainer.add(costsContainer);


        //botoes
        JPanel buttonsContainer = new JPanel(new FlowLayout());
        buttonsContainer.setLayout(new GridLayout(3, 1, 5, 5));
        readDBButton = new JButton("Ler do banco");
        readDBButton.setActionCommand("READ");
        //readDBButton.addActionListener(this);

        saveDBButton = new JButton("Salvar no banco");
        saveDBButton.setActionCommand("SAVE");
        //saveDBButton.addActionListener(this);

        exitButton = new JButton("Sair");
        exitButton.setActionCommand("EXIT");
        //exitButton.addActionListener(this);

        buttonsContainer.add(readDBButton);
        buttonsContainer.add(saveDBButton);
        buttonsContainer.add(exitButton);

        rootContainer.add(informationContainer, BorderLayout.CENTER);
        rootContainer.add(buttonsContainer, BorderLayout.WEST);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // @Override
    // public void actionPerformed(ActionEvent e) {
    //     switch (e.getActionCommand()) {
    //         case "READ":
    //             readFromDatabase();
    //             break;

    //         case "SAVE":
    //             saveToDatabase();
    //             break;

    //         case "EXIT":
    //             System.exit(0);
    //             break;
    //     }
    // }

    // private void readFromDatabase() {
        
    // }

    // private void saveToDatabase() {
        
    // }

    // private double[] readGrades(int row) {
        
    // }

    // private void updateAverages(int row) {
        
    // }

    private String formatGrade(double grade) {
        return String.format(Locale.US, "%.1f", grade);
    }

    private String formatSqlGrade(double grade) {
        return String.format(Locale.US, "%.2f", grade);
    }

    private String executeSqlite(String sql) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("sqlite3", "-separator", "\t", DB_FILE, sql);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        StringBuilder output = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException(output.toString().trim());
        }

        return output.toString().trim();
    }

    private void showDatabaseError(String message, Exception ex) {
        JOptionPane.showMessageDialog(
                this,
                message + "\n\n" + ex.getMessage(),
                "Erro no SQLite",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}