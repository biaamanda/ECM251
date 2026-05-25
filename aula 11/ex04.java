/*Crie uma tela para a hierarquia de classes PessoaFisica,
Desempregado, Empregado, Mensalista, Comissionado,
Horista e Tarefeiro. */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class PessoaFisica {

    protected String nome;
    protected String cpf;

    public PessoaFisica(
            String nome,
            String cpf
    ) {

        this.nome = nome;
        this.cpf = cpf;
    }

    public String toString() {

        return "Nome: " + nome +
               "\nCPF: " + cpf;
    }
}

class Desempregado extends PessoaFisica {

    public Desempregado(
            String nome,
            String cpf
    ) {

        super(nome, cpf);
    }

    public String toString() {

        return "Desempregado\n" +
               super.toString();
    }
}

class Empregado extends PessoaFisica {

    public Empregado(
            String nome,
            String cpf
    ) {

        super(nome, cpf);
    }

    public String toString() {

        return "Empregado\n" +
               super.toString();
    }
}


class Mensalista extends Empregado {

    private double salario;

    public Mensalista(
            String nome,
            String cpf,
            double salario
    ) {

        super(nome, cpf);

        this.salario = salario;
    }

    public String toString() {

        return "Mensalista\n" +
               super.toString() +
               "\nSalário: R$ " + salario;
    }
}

class Comissionado extends Empregado {

    private double vendas;
    private double percentual;

    public Comissionado(
            String nome,
            String cpf,
            double vendas,
            double percentual
    ) {

        super(nome, cpf);

        this.vendas = vendas;
        this.percentual = percentual;
    }

    public String toString() {

        return "Comissionado\n" +
               super.toString() +
               "\nVendas: R$ " + vendas +
               "\nPercentual: " + percentual + "%";
    }
}


class Horista extends Empregado {

    private double horas;
    private double valorHora;

    public Horista(
            String nome,
            String cpf,
            double horas,
            double valorHora
    ) {

        super(nome, cpf);

        this.horas = horas;
        this.valorHora = valorHora;
    }

    public String toString() {

        return "Horista\n" +
               super.toString() +
               "\nHoras: " + horas +
               "\nValor Hora: R$ " + valorHora;
    }
}


class Tarefeiro extends Empregado {

    private int tarefas;
    private double valorTarefa;

    public Tarefeiro(
            String nome,
            String cpf,
            int tarefas,
            double valorTarefa
    ) {

        super(nome, cpf);

        this.tarefas = tarefas;
        this.valorTarefa = valorTarefa;
    }

    public String toString() {

        return "Tarefeiro\n" +
               super.toString() +
               "\nTarefas: " + tarefas +
               "\nValor por tarefa: R$ " +
               valorTarefa;
    }
}


public class ex04 extends JFrame
        implements ActionListener {

    private JComboBox<String> comboTipo;

    private JTextField campoNome;
    private JTextField campoCpf;

    private JTextField campo1;
    private JTextField campo2;

    private JButton botaoMostrar;

    public ex04() {

        super("Hierarquia Pessoa Física");

        setLayout(
                new GridLayout(6, 2, 5, 5)
        );

        // TIPO
        add(new JLabel("Tipo:"));

        comboTipo =
                new JComboBox<>(
                        new String[] {
                                "Desempregado",
                                "Mensalista",
                                "Comissionado",
                                "Horista",
                                "Tarefeiro"
                        }
                );

        add(comboTipo);

        // NOME
        add(new JLabel("Nome:"));

        campoNome = new JTextField();

        add(campoNome);

        // CPF
        add(new JLabel("CPF:"));

        campoCpf = new JTextField();

        add(campoCpf);

        // VALOR 1
        add(new JLabel("Valor 1:"));

        campo1 = new JTextField();

        add(campo1);

        // VALOR 2
        add(new JLabel("Valor 2:"));

        campo2 = new JTextField();

        add(campo2);

        // BOTÃO
        botaoMostrar =
                new JButton("Mostrar");

        botaoMostrar.addActionListener(this);

        add(botaoMostrar);

        JButton botaoSair =
                new JButton("Sair");

        botaoSair.addActionListener(
                e -> System.exit(0)
        );

        add(botaoSair);

        // JANELA
        setSize(400, 300);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setVisible(true);
    }

    // evento
    public void actionPerformed(
            ActionEvent e
    ) {

        try {

            String tipo =
                    comboTipo
                    .getSelectedItem()
                    .toString();

            String nome =
                    campoNome.getText();

            String cpf =
                    campoCpf.getText();

            PessoaFisica pessoa = null;

            if (tipo.equals(
                    "Desempregado")) {

                pessoa =
                        new Desempregado(
                                nome,
                                cpf
                        );
            }

            else if (tipo.equals(
                    "Mensalista")) {

                double salario =
                        Double.parseDouble(
                                campo1.getText()
                        );

                pessoa =
                        new Mensalista(
                                nome,
                                cpf,
                                salario
                        );
            }

            else if (tipo.equals( "Comissionado")) {

                double vendas =
                        Double.parseDouble(
                                campo1.getText()
                        );

                double percentual =
                        Double.parseDouble(
                                campo2.getText()
                        );

                pessoa =
                        new Comissionado(
                                nome,
                                cpf,
                                vendas,
                                percentual
                        );
            }

            else if (tipo.equals("Horista")) {

                double horas =
                        Double.parseDouble(
                                campo1.getText()
                        );

                double valorHora =
                        Double.parseDouble(
                                campo2.getText()
                        );

                pessoa =
                        new Horista(
                                nome,
                                cpf,
                                horas,
                                valorHora
                        );
            }

            else if (tipo.equals("Tarefeiro")) {

                int tarefas =
                        Integer.parseInt(
                                campo1.getText()
                        );

                double valor =
                        Double.parseDouble(
                                campo2.getText()
                        );

                pessoa =
                        new Tarefeiro(
                                nome,
                                cpf,
                                tarefas,
                                valor
                        );
            }

            JOptionPane.showMessageDialog(
                    this,
                    pessoa.toString()
            );

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro nos dados!"
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new ex04()
        );
    }
}