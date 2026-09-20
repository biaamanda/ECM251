// "Cola" dos componentes Swing e dos EVENTOS de cada um (aulas 13-14).
// Roda sozinho:   javac TelaComponentes.java   e   java TelaComponentes
//
// Os eventos de cada componente sao alvo classico das afirmacoes V/F:
//
//   JButton, JTextField, JPasswordField, JMenuItem  -> ActionEvent  / ActionListener
//   JComboBox, JCheckBox, JRadioButton              -> ItemEvent    / ItemListener
//   (JComboBox, JCheckBox e JRadioButton TAMBEM disparam ActionEvent)
//   JList                                           -> ListSelectionEvent / ListSelectionListener
//   mouse (JLabel, JPanel...)                       -> MouseEvent   / MouseListener, MouseMotionListener
//   teclado                                         -> KeyEvent     / KeyListener
//
// JPasswordField ESTENDE JTextField (nao JComponent direto). Diferencas:
//   - getPassword() retorna char[] (getText() e desaconselhado);
//   - setEchoChar('*') muda o caractere exibido; copiar/recortar e desabilitado.

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class TelaComponentes extends JFrame {
    private final JTextArea log = new JTextArea(8, 40);
    private final JTextField txtLogin = new JTextField(10);
    private final JPasswordField txtSenha = new JPasswordField(10);
    private final JComboBox<String> cbCurso = new JComboBox<>(new String[]{"Computacao", "Mecanica", "Producao"});
    private final JCheckBox ckBolsa = new JCheckBox("Bolsista");
    private final JRadioButton rbManha = new JRadioButton("Manha", true);
    private final JRadioButton rbNoite = new JRadioButton("Noite");
    private final DefaultTableModel modelo = new DefaultTableModel(new Object[]{"RA", "Nome", "Nota"}, 0);
    private final JTable tabela = new JTable(modelo);

    public TelaComponentes() {
        super("Componentes Swing");
        setLayout(new BorderLayout(5, 5));

        // ----- campos (GridLayout: linhas x colunas) -----
        JPanel campos = new JPanel(new GridLayout(5, 2, 5, 5));
        campos.add(new JLabel("Login:"));  campos.add(txtLogin);
        campos.add(new JLabel("Senha:"));  campos.add(txtSenha);
        txtSenha.setEchoChar('*');
        campos.add(new JLabel("Curso:"));  campos.add(cbCurso);
        campos.add(new JLabel("Bolsa:"));  campos.add(ckBolsa);

        JPanel turno = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        ButtonGroup grupo = new ButtonGroup();            // radio: so UM marcado por grupo
        grupo.add(rbManha);
        grupo.add(rbNoite);
        turno.add(rbManha);
        turno.add(rbNoite);
        campos.add(new JLabel("Turno:"));  campos.add(turno);
        add(campos, BorderLayout.NORTH);

        // ----- eventos -----
        // ItemListener: JComboBox / JCheckBox / JRadioButton
        cbCurso.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {     // o combo dispara 2 eventos (DESELECTED e SELECTED)
                log("ItemEvent combo: " + e.getItem());
            }
        });
        ckBolsa.addItemListener(e -> log("ItemEvent checkbox: " + (e.getStateChange() == ItemEvent.SELECTED)));
        rbManha.addItemListener(e -> { if (e.getStateChange() == ItemEvent.SELECTED) log("ItemEvent radio: Manha"); });
        rbNoite.addItemListener(e -> { if (e.getStateChange() == ItemEvent.SELECTED) log("ItemEvent radio: Noite"); });

        // ActionListener: JButton / JTextField / JPasswordField (Enter)
        JButton btEntrar = new JButton("Entrar");
        btEntrar.addActionListener((ActionEvent e) -> {
            String senha = new String(txtSenha.getPassword());   // getPassword() -> char[]
            log("Login=" + txtLogin.getText() + " Senha=" + "*".repeat(senha.length()));
            modelo.addRow(new Object[]{"2026" + (modelo.getRowCount() + 1), txtLogin.getText(), 7.5});
            Calendar c = Calendar.getInstance();                 // data/hora da operacao (aula 14)
            log(String.format("Em %02d/%02d/%d %02d:%02d", c.get(Calendar.DAY_OF_MONTH),
                    c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR),   // MONTH comeca em 0!
                    c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)));
        });
        txtSenha.addActionListener(e -> btEntrar.doClick());      // Enter no campo = clicar no botao
        getRootPane().setDefaultButton(btEntrar);

        JPanel botoes = new JPanel(new FlowLayout());
        botoes.add(btEntrar);
        add(botoes, BorderLayout.SOUTH);

        // ----- tabela (JTable dentro de JScrollPane) e log -----
        log.setEditable(false);
        JPanel centro = new JPanel(new GridLayout(2, 1, 5, 5));
        centro.add(new JScrollPane(tabela));
        centro.add(new JScrollPane(log));
        add(centro, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(460, 520);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void log(String s) {
        log.append(s + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaComponentes::new);
    }
}
