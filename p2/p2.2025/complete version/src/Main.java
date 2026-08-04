import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;

public final class Main extends JFrame implements ActionListener {

    private static final int ENERGY_COUNT = 3;

    // Test Code fornecido na prova - hard code, não vai para o B.D.
    private static final String TEST_CODE = "OBQFG";

    private JTextField nomeClienteField;
    private JTextField valorKwhField;
    private final JTextField[] comodoFields = new JTextField[ENERGY_COUNT];
    private final JTextField[] consumoFields = new JTextField[ENERGY_COUNT];
    private final JTextField[] custoFields = new JTextField[ENERGY_COUNT];
    private JTextField testCodeField;

    private final ClienteDAO dao = new ClienteDAO();
    private final DecimalFormat df = new DecimalFormat("0.00");

    public Main() {
        super("CEM – Consumo de Energia Mensal");

        montarMenu();
        montarConteudo();

        setSize(650, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void montarMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu opcoesMenu = new JMenu("Opções");
        opcoesMenu.setMnemonic('O');

        JMenuItem salvarItem = new JMenuItem("Salvar no B.D.");
        salvarItem.setMnemonic('S');
        salvarItem.setActionCommand("SAVE");
        salvarItem.addActionListener(this);

        JMenuItem lerItem = new JMenuItem("Ler do B.D.");
        lerItem.setMnemonic('L');
        lerItem.setActionCommand("READ");
        lerItem.addActionListener(this);

        JMenuItem sairItem = new JMenuItem("Sair");
        sairItem.setMnemonic('a');
        sairItem.setActionCommand("EXIT");
        sairItem.addActionListener(this);

        opcoesMenu.add(salvarItem);
        opcoesMenu.add(lerItem);
        opcoesMenu.add(sairItem);
        menuBar.add(opcoesMenu);

        setJMenuBar(menuBar);
    }

    private void montarConteudo() {
        Container root = getContentPane();
        root.setLayout(new BorderLayout(10, 10));
        ((JComponent) root).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Nome do Cliente / Valor do kWh
        JPanel topo = new JPanel(new GridLayout(2, 2, 5, 5));
        topo.add(new JLabel("Nome do Cliente:"));
        nomeClienteField = new JTextField();
        topo.add(nomeClienteField);

        topo.add(new JLabel("Valor do kWh (R$):"));
        valorKwhField = new JTextField();
        topo.add(valorKwhField);

        // Tabela de cômodos: Cômodo | Consumo (kWh) | Custo (R$)
        JPanel comodosPanel = new JPanel(new GridLayout(ENERGY_COUNT + 1, 3, 5, 5));
        comodosPanel.add(new JLabel("Cômodo"));
        comodosPanel.add(new JLabel("Consumo (kWh)"));
        comodosPanel.add(new JLabel("Custo (R$)"));

        RecalculaAoSairDoCampo listenerRecalculo = new RecalculaAoSairDoCampo();
        for (int i = 0; i < ENERGY_COUNT; i++) {
            comodoFields[i] = new JTextField();

            consumoFields[i] = new JTextField();
            consumoFields[i].addFocusListener(listenerRecalculo);

            custoFields[i] = new JTextField();
            custoFields[i].setEditable(false);
            custoFields[i].setBackground(Color.LIGHT_GRAY);

            comodosPanel.add(comodoFields[i]);
            comodosPanel.add(consumoFields[i]);
            comodosPanel.add(custoFields[i]);
        }
        valorKwhField.addFocusListener(listenerRecalculo);

        JPanel centro = new JPanel(new BorderLayout(5, 10));
        centro.add(topo, BorderLayout.NORTH);
        centro.add(comodosPanel, BorderLayout.CENTER);

        // Test Code (somente leitura, hard code)
        JPanel testCodePanel = new JPanel();
        testCodePanel.setLayout(new BoxLayout(testCodePanel, BoxLayout.Y_AXIS));
        testCodePanel.add(new JLabel("Test Code:"));
        testCodeField = new JTextField(TEST_CODE, 8);
        testCodeField.setMaximumSize(new Dimension(100, 30));
        testCodeField.setEditable(false);
        testCodeField.setBackground(Color.LIGHT_GRAY);
        testCodePanel.add(testCodeField);

        root.add(centro, BorderLayout.CENTER);
        root.add(testCodePanel, BorderLayout.EAST);
    }

    /** Recalcula os custos automaticamente ao sair de um campo de consumo/valor do kWh. */
    private class RecalculaAoSairDoCampo extends FocusAdapter {
        @Override
        public void focusLost(FocusEvent e) {
            calcularCustos();
        }
    }

    /** Custo (R$) = Valor do kWh (R$) * Consumo (kWh), calculado para os 3 cômodos. */
    private void calcularCustos() {
        double valorKwh = parseDoubleSeguro(valorKwhField.getText());
        for (int i = 0; i < ENERGY_COUNT; i++) {
            double consumo = parseDoubleSeguro(consumoFields[i].getText());
            custoFields[i].setText(df.format(valorKwh * consumo));
        }
    }

    private double parseDoubleSeguro(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(texto.trim().replace(",", "."));
        } catch (NumberFormatException ex) {
            return 0.0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "SAVE":
                salvarNoBanco();
                break;
            case "READ":
                lerDoBanco();
                break;
            case "EXIT":
                System.exit(0);
                break;
        }
    }

    private void salvarNoBanco() {
        String nome = nomeClienteField.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o Nome do Cliente.", "CEM", JOptionPane.WARNING_MESSAGE);
            return;
        }

        calcularCustos();

        Cliente cliente = new Cliente(nome, parseDoubleSeguro(valorKwhField.getText()));
        for (int i = 0; i < ENERGY_COUNT; i++) {
            Comodo c = new Comodo(comodoFields[i].getText().trim(), parseDoubleSeguro(consumoFields[i].getText()));
            c.calcularCusto(cliente.getValorKwh());
            cliente.addComodo(c);
        }

        try {
            dao.salvar(cliente);
            JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!", "CEM", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            mostrarErroBanco("Erro ao salvar no banco de dados", ex);
        }
    }

    private void lerDoBanco() {
        String nome = nomeClienteField.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o Nome do Cliente para buscar.", "CEM", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Cliente cliente = dao.buscarPorNome(nome);
            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "CEM", JOptionPane.WARNING_MESSAGE);
                return;
            }

            valorKwhField.setText(df.format(cliente.getValorKwh()));
            for (int i = 0; i < ENERGY_COUNT && i < cliente.getComodos().size(); i++) {
                Comodo c = cliente.getComodos().get(i);
                comodoFields[i].setText(c.getNome());
                consumoFields[i].setText(df.format(c.getConsumo()));
                custoFields[i].setText(df.format(c.getCusto()));
            }
        } catch (SQLException ex) {
            mostrarErroBanco("Erro ao ler do banco de dados", ex);
        }
    }

    private void mostrarErroBanco(String mensagem, Exception ex) {
        JOptionPane.showMessageDialog(
                this,
                mensagem + "\n\n" + ex.getMessage(),
                "Erro no Banco de Dados",
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
