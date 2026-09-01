/*Desenvolver, utilizando Layouts como interface com o usuário,
um programa que receba um RA, digitado pelo usuário, para
que sejam validados com os Ras lidos no arquivo de texto
gerado pelo aplicativo do item 1, anterior.
• Caso o RA digitado seja encontrado no arquivo texto, o
aplicativo deverá apresentar o RA, o NOME e o
SOBRENOME do aluno, bem como sua MÉDIA = (P1 + P2 +
P3+ P4)/4;
• Caso as informações não coincidam, o aplicativo deverá
apresentar a resposta “RA NÃO CADASTRADO” */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Ex04 extends JFrame implements ActionListener {
    // mesmo arquivo gravado pelo Ex03
    private static final String ARQUIVO = "alunos.txt";

    private final JTextField txtRa = new JTextField(10);
    private final JButton btConsultar = new JButton("Consultar");
    private final JTextArea areaResultado = new JTextArea(5, 25);

    public Ex04() {
        super("Consulta de Aluno por RA");
        setLayout(new BorderLayout(5, 5));
        areaResultado.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); 

        // painel superior: rotulo + campo + botao
        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBusca.add(new JLabel("RA: "));
        painelBusca.add(txtRa);
        painelBusca.add(btConsultar);
        add(painelBusca, BorderLayout.NORTH);

        // area central somente leitura para exibir o resultado
        areaResultado.setEditable(false);
        add(areaResultado, BorderLayout.CENTER);

        btConsultar.addActionListener(this);

        setSize(360, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ra = txtRa.getText().trim();

        if (ra.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Digite um RA para consultar.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Scanner arquivo = new Scanner(new File(ARQUIVO))) {
            while (arquivo.hasNextLine()) {
                String[] campos = arquivo.nextLine().split(";");
                

                // linha valida: RA;NOME;SOBRENOME;P1;P2;P3;P4
                if (campos.length >= 7 && campos[0].equals(ra)) {
                    double p1 = Double.parseDouble(campos[3].replace(",", "."));
                    double p2 = Double.parseDouble(campos[4].replace(",", "."));
                    double p3 = Double.parseDouble(campos[5].replace(",", "."));
                    double p4 = Double.parseDouble(campos[6].replace(",", "."));
                    double media = (p1 + p2 + p3 + p4) / 4;

                    areaResultado.setText(String.format(
                        "RA: %s%nNome: %s%nSobrenome: %s%nMedia: %.2f",
                        campos[0], campos[1], campos[2], media));
                    return;
                }
            }
            // percorreu o arquivo inteiro sem encontrar o RA
            areaResultado.setText("RA NAO CADASTRADO");
        } catch (FileNotFoundException ex) {
            areaResultado.setText("RA NAO CADASTRADO");
            JOptionPane.showMessageDialog(this,
                "Arquivo " + ARQUIVO + " nao encontrado. Cadastre alunos no Ex03 primeiro.",
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        Ex04 tela = new Ex04();
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
