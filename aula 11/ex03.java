/* Fazer uma tela para a hierarquia de classes Ponto, Circulo e
Cilindro da aula anterior. A tela deve decidir qual é a forma
pelos parâmetros de entrada, instanciar esta forma e mostrar
seus dados por meio do toString() e um JOptionPane().*/

/*De modo análogo ao Exemplo Resolvido, crie uma tela para a
hierarquia de classes Empregado, Mensalista, Comissionado,
Horista e Tarefeiro */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Empregado {
    protected String nome;

    public Empregado(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return "Empregado: " + nome;
    }
}

class Mensalista extends Empregado {
    private double salario;

    public Mensalista(String nome, double salario) {
        super(nome);
        this.salario = salario;
    }

    public String toString() {
        return "Mensalista\nNome: " + nome +
               "\nSalário: R$ " + salario;
    }
}


class Comissionado extends Empregado {
    private double vendas;
    private double percentual;

    public Comissionado(String nome, double vendas, double percentual) {
        super(nome);
        this.vendas = vendas;
        this.percentual = percentual;
    }

    public String toString() {
        return "Comissionado\nNome: " + nome +
               "\nVendas: R$ " + vendas +
               "\nPercentual: " + percentual + "%";
    }
}

class Horista extends Empregado {
    private double horas;
    private double valorHora;

    public Horista(String nome, double horas, double valorHora) {
        super(nome);
        this.horas = horas;
        this.valorHora = valorHora;
    }

    public String toString() {
        return "Horista\nNome: " + nome +
               "\nHoras: " + horas +
               "\nValor Hora: R$ " + valorHora;
    }
}

class Tarefeiro extends Empregado {
    private int tarefas;
    private double valorTarefa;

    public Tarefeiro(String nome, int tarefas, double valorTarefa) {
        super(nome);
        this.tarefas = tarefas;
        this.valorTarefa = valorTarefa;
    }

    public String toString() {
        return "Tarefeiro\nNome: " + nome +
               "\nTarefas: " + tarefas +
               "\nValor por tarefa: R$ " + valorTarefa;
    }
}

// tela
public class ex03 extends JFrame {

    private JTextField campoNome;
    private JTextField campo1;
    private JTextField campo2;

    private JComboBox<String> comboTipo;

    public ex03() {

        setTitle("Cadastro de Empregados");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(6, 2, 5, 5));

        // Tipo
        add(new JLabel("Tipo:"));

        comboTipo = new JComboBox<>(
                new String[]{
                        "Mensalista",
                        "Comissionado",
                        "Horista",
                        "Tarefeiro"
                });

        add(comboTipo);

        // Nome
        add(new JLabel("Nome:"));
        campoNome = new JTextField();
        add(campoNome);

        // Campo 1
        add(new JLabel("Valor 1:"));
        campo1 = new JTextField();
        add(campo1);

        // Campo 2
        add(new JLabel("Valor 2:"));
        campo2 = new JTextField();
        add(campo2);

        // Botão mostrar
        JButton botaoMostrar = new JButton("Mostrar");
        add(botaoMostrar);

        // Botão sair
        JButton botaoSair = new JButton("Sair");
        add(botaoSair);

        // mostrar
        botaoMostrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {

                    String tipo = comboTipo.getSelectedItem().toString();
                    String nome = campoNome.getText();

                    Empregado emp = null;

                    if (tipo.equals("Mensalista")) {

                        double salario =
                                Double.parseDouble(campo1.getText());

                        emp = new Mensalista(nome, salario);

                    } else if (tipo.equals("Comissionado")) {

                        double vendas =
                                Double.parseDouble(campo1.getText());

                        double percentual =
                                Double.parseDouble(campo2.getText());

                        emp = new Comissionado(
                                nome,
                                vendas,
                                percentual
                        );

                    } else if (tipo.equals("Horista")) {

                        double horas =
                                Double.parseDouble(campo1.getText());

                        double valorHora =
                                Double.parseDouble(campo2.getText());

                        emp = new Horista(
                                nome,
                                horas,
                                valorHora
                        );

                    } else if (tipo.equals("Tarefeiro")) {

                        int tarefas =
                                Integer.parseInt(campo1.getText());

                        double valorTarefa =
                                Double.parseDouble(campo2.getText());

                        emp = new Tarefeiro(
                                nome,
                                tarefas,
                                valorTarefa
                        );
                    }

                    JOptionPane.showMessageDialog(
                            null,
                            emp.toString()
                    );

                } catch (Exception erro) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Erro nos dados!"
                    );
                }
            }
        });

        // sair
        botaoSair.addActionListener(
                e -> System.exit(0)
        );

        setVisible(true);
    }

    public static void main(String[] args) {

        new ex03();
    }
}