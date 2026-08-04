import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public final class Main extends JFrame implements ActionListener {

    private static final String DB_FILE = "subjects.db";
    private static final int SUBJECT_COUNT = 4;

    private JButton readDBButton;
    private JButton saveDBButton;
    private JButton exitButton;
    private final JTextField[] subjectNames = new JTextField[SUBJECT_COUNT];
    private final JTextField[][] gradeFields = new JTextField[SUBJECT_COUNT][6];
    private final JTextField[][] averageFields = new JTextField[SUBJECT_COUNT][3];

    public Main() {
        super("Banco de Dados");

        Container rootContainer = getContentPane();
        rootContainer.setLayout(new BorderLayout());

        JPanel subjectsContainer = new JPanel();
        subjectsContainer.setLayout(new GridLayout(5, 10, 10, 10));
        JLabel subjectLabel = new JLabel("materia");
        JLabel p1Label = new JLabel("p1");
        JLabel p2Label = new JLabel("p2");
        JLabel t1Label = new JLabel("t1");
        JLabel p3Label = new JLabel("p3");
        JLabel p4Label = new JLabel("p4");
        JLabel t2Label = new JLabel("t2");
        JLabel mpLabel = new JLabel("mp");
        JLabel mtLabel = new JLabel("mt");
        JLabel mfLabel = new JLabel("mf");

// labels
        subjectsContainer.add(subjectLabel);
        subjectsContainer.add(p1Label);
        subjectsContainer.add(p2Label);
        subjectsContainer.add(t1Label);
        subjectsContainer.add(p3Label);
        subjectsContainer.add(p4Label);
        subjectsContainer.add(t2Label);
        subjectsContainer.add(mpLabel);
        subjectsContainer.add(mtLabel);
        subjectsContainer.add(mfLabel);

        for (int row = 0; row < SUBJECT_COUNT; row++) {
            subjectNames[row] = new JTextField(20);
            subjectNames[row].setEditable(false);
            subjectsContainer.add(subjectNames[row]);

            for (int column = 0; column < gradeFields[row].length; column++) {
                gradeFields[row][column] = new JTextField(20);
                subjectsContainer.add(gradeFields[row][column]);
            }

            for (int column = 0; column < averageFields[row].length; column++) {
                averageFields[row][column] = new JTextField(20);
                averageFields[row][column].setEditable(false);
                subjectsContainer.add(averageFields[row][column]);
            }
        }


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
                readSubjectsFromDatabase();
                break;

            case "SAVE":
                saveSubjectsToDatabase();
                break;

            case "EXIT":
                System.exit(0);
                break;
        }
    }

    private void readSubjectsFromDatabase() {
        String sql = "SELECT id, name, p1, p2, t1, p3, p4, t2 FROM subjects ORDER BY id LIMIT " + SUBJECT_COUNT + ";";

        try {
            String output = executeSqlite(sql);
            String[] lines = output.split("\\R");

            for (int row = 0; row < lines.length && row < SUBJECT_COUNT; row++) {
                String[] columns = lines[row].split("\\t", -1);
                if (columns.length < 8) {
                    continue;
                }

                subjectNames[row].setText(columns[1]);
                for (int column = 0; column < gradeFields[row].length; column++) {
                    gradeFields[row][column].setText(formatGrade(Double.parseDouble(columns[column + 2])));
                }
                updateAverages(row);
            }

            JOptionPane.showMessageDialog(this, "Dados carregados do SQLite.");
        } catch (IOException | InterruptedException ex) {
            showDatabaseError("Não foi possível ler do banco.", ex);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Nota inválida", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveSubjectsToDatabase() {
        StringBuilder sql = new StringBuilder("BEGIN TRANSACTION;");

        try {
            for (int row = 0; row < SUBJECT_COUNT; row++) {
                double[] grades = readGrades(row);
                sql.append("UPDATE subjects SET ")
                        .append("p1 = ").append(formatSqlGrade(grades[0])).append(", ")
                        .append("p2 = ").append(formatSqlGrade(grades[1])).append(", ")
                        .append("t1 = ").append(formatSqlGrade(grades[2])).append(", ")
                        .append("p3 = ").append(formatSqlGrade(grades[3])).append(", ")
                        .append("p4 = ").append(formatSqlGrade(grades[4])).append(", ")
                        .append("t2 = ").append(formatSqlGrade(grades[5])).append(" ")
                        .append("WHERE id = ").append(row + 1).append(";");
                updateAverages(row);
            }
            sql.append("COMMIT;");

            executeSqlite(sql.toString());
            JOptionPane.showMessageDialog(this, "Dados salvos no SQLite.");
        } catch (IOException | InterruptedException ex) {
            showDatabaseError("Não foi possível salvar no banco.", ex);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Nota inválida", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double[] readGrades(int row) {
        double[] grades = new double[gradeFields[row].length];

        for (int column = 0; column < gradeFields[row].length; column++) {
            String value = gradeFields[row][column].getText().trim().replace(',', '.');
            if (value.isEmpty()) {
                value = "0";
            }

            grades[column] = Double.parseDouble(value);
            if (grades[column] < 0 || grades[column] > 10) {
                throw new NumberFormatException("Use notas entre 0 e 10.");
            }
        }

        return grades;
    }

    private void updateAverages(int row) {
        double[] grades = readGrades(row);
        double mp = (grades[0] + grades[1] + grades[3] + grades[4]) / 4.0;
        double mt = (grades[2] + grades[5]) / 2.0;
        double mf = (mp + mt) / 2.0;

        averageFields[row][0].setText(formatGrade(mp));
        averageFields[row][1].setText(formatGrade(mt));
        averageFields[row][2].setText(formatGrade(mf));
    }

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