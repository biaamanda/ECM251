// Internacionalizacao (aula 15) com TROCA DE IDIOMA PELO MENU, em tempo de execucao
// -- e o que o Ex01 da aula 15 pede ("insira essa opcao de escolha no Menu").
//
// Como funciona:
//   1) um arquivo .properties por idioma:  <base>_<idioma>_<pais>.properties
//        Tela.properties        (BASE / fallback -- sempre crie)
//        Tela_pt_BR.properties, Tela_en_US.properties, Tela_fr_FR.properties, Tela_it_IT.properties
//      chaves iguais em todos, valores traduzidos: rotulo.valor=Valor / Value / Valeur
//   2) ResourceBundle.getBundle("Tela", locale) escolhe o arquivo;
//      bundle.getString("chave") devolve o texto.
//   3) para trocar o idioma "ao vivo": recarregar o bundle e RE-aplicar os textos
//      em TODOS os componentes (metodo aplicarTextos()).
//
// Pegadinhas:
//   - O nome base tem que ser IDENTICO ao do arquivo, inclusive maiusculas/minusculas.
//     No Windows "ex1" acha "Ex1_pt_BR.properties", mas DENTRO DO .jar nao acha!
//   - Os .properties precisam estar no classpath (mesma pasta dos .class / dentro do .jar).
//     Ao montar o .jar na mao, copie os .properties para a pasta de saida (veja README).
//   - Locale: Locale.forLanguageTag("pt-BR") funciona em qualquer versao do Java;
//     new Locale("pt","BR") esta obsoleto (Java 19+); Locale.US/FRANCE/ITALY sao constantes prontas.
//   - Ordem de busca: pt_BR -> pt -> (Locale padrao do PC) -> arquivo base.
//
// Roda sozinho:  javac -encoding UTF-8 TelaI18n.java   e   java TelaI18n

import java.awt.FlowLayout;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TelaI18n extends JFrame {
    private ResourceBundle bn;

    private final JMenu mArquivo = new JMenu();
    private final JMenu mIdioma = new JMenu();
    private final JMenuItem miSair = new JMenuItem();
    private final JLabel rotulo = new JLabel();
    private final JTextField campo = new JTextField(10);
    private final JButton botao = new JButton();

    public TelaI18n() {
        setLayout(new FlowLayout());

        // ----- menu Idioma: radio buttons (so um marcado) -----
        ButtonGroup grupo = new ButtonGroup();
        adicionarIdioma(grupo, "Português", Locale.forLanguageTag("pt-BR"), true);
        adicionarIdioma(grupo, "English", Locale.US, false);
        adicionarIdioma(grupo, "Français", Locale.FRANCE, false);
        adicionarIdioma(grupo, "Italiano", Locale.ITALY, false);

        mArquivo.add(miSair);
        miSair.addActionListener(e -> System.exit(0));

        JMenuBar barra = new JMenuBar();
        barra.add(mArquivo);
        barra.add(mIdioma);
        setJMenuBar(barra);

        add(rotulo);
        add(campo);
        add(botao);
        botao.addActionListener(e -> calcular());

        carregarIdioma(Locale.forLanguageTag("pt-BR"));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 120);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void adicionarIdioma(ButtonGroup grupo, String nome, Locale locale, boolean marcado) {
        JRadioButtonMenuItem item = new JRadioButtonMenuItem(nome, marcado);
        item.addActionListener(e -> carregarIdioma(locale));
        grupo.add(item);
        mIdioma.add(item);
    }

    private void carregarIdioma(Locale locale) {
        bn = ResourceBundle.getBundle("Tela", locale);
        aplicarTextos();
    }

    // Re-aplica os textos em todos os componentes (chamado a cada troca de idioma)
    private void aplicarTextos() {
        setTitle(bn.getString("titulo"));
        mArquivo.setText(bn.getString("menu.arquivo"));
        mIdioma.setText(bn.getString("menu.idioma"));
        miSair.setText(bn.getString("item.sair"));
        rotulo.setText(bn.getString("rotulo.valor") + ":");
        botao.setText(bn.getString("botao.calcular"));
    }

    private void calcular() {
        String txt = campo.getText().trim();
        if (txt.isEmpty()) {
            // (aqui JOptionPane e permitido; na P3 2025 era proibido -- leia o enunciado!)
            JOptionPane.showMessageDialog(this, bn.getString("erro.vazio"),
                    bn.getString("erro.titulo"), JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int n = Integer.parseInt(txt);
            campo.setText(String.valueOf(n * n));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, bn.getString("erro.invalido"),
                    bn.getString("erro.titulo"), JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaI18n::new);
    }
}
