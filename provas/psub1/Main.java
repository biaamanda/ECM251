import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;

public final class Main extends JFrame implements ActionListener{
    
    private static final int COUNT = 3;

    private JTextField nomeClienteField;
    private JTextField taxaManuseioField;
    private final JTextField[] nomeVolumeFields = new JTextField[COUNT];
    private final JTextField[] pesoFields = new JTextField[COUNT];
    private final JTextField[] realPesoFields = new JTextField[COUNT];
    private final JTextField[] custoFields = new JTextField[COUNT];
    private JTextField testCodeField;

    public Main() {
        super("Sistema Cálculo de Frete Internacional");

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

    public void montarConteudo() {
        Container root = getContentPane();
        root.setLayout(new BorderLayout(10, 10));
        ((JComponent) root).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Nome do Cliente [___]
        // Taxa Manuseio [___]
        JPanel topo = new JPanel(new GridLayout(2, 2, 5, 5));
        topo.add(new JLabel("Nome do Cliente:"));
        nomeClienteField = new JTextField();
        topo.add(nomeClienteField);

        topo.add(new JLabel("Taxa de Manuseio (R$):"));
        taxaManuseioField = new JTextField();
        topo.add(taxaManuseioField);

        JPanel custoVolumesPanel = new JPanel(new GridLayout(COUNT + 1, 4, 5, 5));
        custoVolumesPanel.add(new JLabel("Nome do Volume"));
        custoVolumesPanel.add(new JLabel("Peso (kg)"));
        custoVolumesPanel.add(new JLabel("R$/kg (kg)"));
        custoVolumesPanel.add(new JLabel("Custo (R$)"));

        RecalculaAoSairDoCampo listenerRecalculo = new RecalculaAoSairDoCampo();
        for (int i = 0; i < COUNT; i++) {
            nomeVolumeFields[i] = new JTextField();

            pesoFields[i] = new JTextField();
            pesoFields[i].addFocusListener(listenerRecalculo);

            custoFields[i] = new JTextField();
            custoFields[i].setEditable(false);

            custoVolumesPanel.add(nomeVolumeFields[i]);
            custoVolumesPanel.add(pesoFields[i]);
            custoVolumesPanel.add(custoFields[i]);
        }
        taxaManuseioField.addFocusListener(listenerRecalculo);

        JPanel centro = new JPanel(new BorderLayout(5, 10));
        centro.add(topo, BorderLayout.NORTH);
        centro.add(custoVolumesPanel, BorderLayout.CENTER);

        JPanel testCodePanel = new JPanel();
        testCodePanel.setLayout(new BoxLayout(testCodePanel, BoxLayout.Y_AXIS));
        testCodePanel.add(new JLabel("Test Code:"));
        testCodeField.setMaximumSize(new Dimension(100, 30));
        testCodeField.setEditable(false);
        testCodePanel.add(testCodeField);

        root.add(centro, BorderLayout.CENTER);
        root.add(testCodePanel, BorderLayout.EAST);
    }

    private class RecalculaAoSairDoCampo extends FocusAdapter {
        @Override
        public void focusLost(FocusEvent e) {
            calcularCustos();
        }
    }

    private void calcularCustos() {
        double valorKwh = parseDoubleSeguro(taxaManuseioField.getText());
        for (int i = 0; i < COUNT; i++) {
            double consumo = parseDoubleSeguro(pesoFields[i].getText());
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
            JOptionPane.showMessageDialog(this, "Informe o Nome do Cliente.", "Sistema Cálculo de Frete Internacional", JOptionPane.WARNING_MESSAGE);
            return;
        }

        calcularCustos();

        Cliente cliente = new Cliente(nome, parseDoubleSeguro(taxaManuseioField.getText()));
        for (int i = 0; i < COUNT; i++) {
            NomeVolume c = new NomeVolume(nomeVolumeFields[i].getText().trim(), parseDoubleSeguro(pesoFields[i].getText()));
            c.calcularCusto(cliente.getTaxaManuseio());
            cliente.addNomeVolume(c);
        }

        try {
            dao.salvar(cliente);
            JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!", "Sistema Cálculo de Frete Internacional", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            mostrarErroBanco("Erro ao salvar no banco de dados", ex);
        }
    }

    private void lerDoBanco() {
        String nome = nomeClienteField.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o Nome do Cliente para buscar.", "Sistema Cálculo de Frete Internacional", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Cliente cliente = dao.buscarPorNome(nome);
            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Sistema Cálculo de Frete Internacional", JOptionPane.WARNING_MESSAGE);
                return;
            }

            taxaManuseioField.setText(df.format(cliente.getTaxaManuseio()));
            for (int i = 0; i < COUNT && i < cliente.getNomeVolumes().size(); i++) {
                NomeVolume c = cliente.getNomeVolumes().get(i);
                nomeVolumeFields[i].setText(c.getNome());
                pesoFields[i].setText(df.format(c.getConsumo()));
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
