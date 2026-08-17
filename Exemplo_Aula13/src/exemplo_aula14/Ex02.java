/*Utilizando o desenvolvimento da GUI com o NetBeans, use as
classes Relogio e Mostrador das aulas anteriores. Crie uma tela
que apresenta o mostrador do relógio (hora e minuto) em um
JLabel e que tenha três botões: TicTac, que aumenta um
minuto a cada clique; Hora, que acerta a hora (0 a 23); e
Minuto, que acerta o minuto (0 a 59). Use sempre o método
mostra() da classe Relogio para atualizar o mostrador da tela.
Consulte a documentação do Java para aumentar o tamanho e
a cor da fonte.*/

package exemplo_aula14;

import javax.swing.JOptionPane;

public class Ex02 extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Ex02.class.getName());

    private Relogio relogio;
    
    public Ex02() {
        super("Exercicio 2");
        relogio = new Relogio();
        initComponents();
        clockLabel.setText(relogio.mostra());
        setVisible(true);
    }
    
    private void initComponents() {

        clockLabel = new javax.swing.JLabel();
        ticTacButton = new javax.swing.JButton();
        hourButton = new javax.swing.JButton();
        minuteButton = new javax.swing.JButton();

        setDefaultCloseOperation(
                javax.swing.WindowConstants.EXIT_ON_CLOSE
        );

        clockLabel.setText("00:00");

        ticTacButton.setText("TicTac");
        ticTacButton.addActionListener(
                this::ticTacButtonActionPerformed
        );

        hourButton.setText("Hora");
        hourButton.addActionListener(
                this::hourButtonActionPerformed
        );

        minuteButton.setText("Minuto");
        minuteButton.addActionListener(
                this::minuteButtonActionPerformed
        );

        javax.swing.GroupLayout layout =
                new javax.swing.GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING
            )
            .addGroup(
                layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(
                    layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING
                    )
                    .addComponent(
                        clockLabel,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        400,
                        Short.MAX_VALUE
                    )
                    .addGroup(
                        layout.createSequentialGroup()
                        .addComponent(ticTacButton)
                        .addPreferredGap(
                            javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
                        )
                        .addComponent(hourButton)
                        .addPreferredGap(
                            javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
                        )
                        .addComponent(minuteButton)
                        .addGap(0, 0, Short.MAX_VALUE)
                    )
                )
                .addContainerGap()
            )
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING
            )
            .addGroup(
                layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(
                    clockLabel,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    100,
                    javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addGap(30, 30, 30)
                .addGroup(
                    layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.BASELINE
                    )
                    .addComponent(ticTacButton)
                    .addComponent(hourButton)
                    .addComponent(minuteButton)
                )
                .addContainerGap(30, Short.MAX_VALUE)
            )
        );

        pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ticTacButton = new javax.swing.JButton();
        hourButton = new javax.swing.JButton();
        minuteButton = new javax.swing.JButton();
        clockLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ticTacButton.setText("TicTac");
        ticTacButton.addActionListener(this::ticTacButtonActionPerformed);

        hourButton.setText("Hora");
        hourButton.addActionListener(this::hourButtonActionPerformed);

        minuteButton.setText("Minuto");
        minuteButton.addActionListener(this::minuteButtonActionPerformed);

        clockLabel.setText("00:00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(ticTacButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hourButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(minuteButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(clockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(clockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ticTacButton)
                    .addComponent(hourButton)
                    .addComponent(minuteButton))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ticTacButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ticTacButtonActionPerformed

        relogio.ticTac();

        // Sempre utiliza mostra()
        clockLabel.setText(relogio.mostra());
    }//GEN-LAST:event_ticTacButtonActionPerformed

    private void hourButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hourButtonActionPerformed

        String valor = JOptionPane.showInputDialog(
                this,
                "Digite a hora (0 a 23):"
        );

        if (valor != null) {

            try {

                int hora = Integer.parseInt(valor);

                if (hora >= 0 && hora <= 23) {
                    relogio.acertaHora(hora);
                    clockLabel.setText(relogio.mostra());

                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Digite uma hora entre 0 e 23."
                    );
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Digite apenas números."
                );
            }
        }
    }//GEN-LAST:event_hourButtonActionPerformed

    private void minuteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minuteButtonActionPerformed
        String valor = JOptionPane.showInputDialog(
                this,
                "Digite o minuto (0 a 59):"
        );

        if (valor != null) {

            try {

                int minuto = Integer.parseInt(valor);

                if (minuto >= 0 && minuto <= 59) {
                    relogio.acertaMinuto(minuto);
                    clockLabel.setText(relogio.mostra());

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Digite um minuto entre 0 e 59."
                    );
                }

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Digite apenas números."
                );
            }
        }
    }//GEN-LAST:event_minuteButtonActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Ex02().setVisible(true));
    }
    
    class Mostrador {

    private int valor;
    private int limite;

    public Mostrador(int limite) {

        this.limite = limite;
        this.valor = 0;
    }

    public void incrementa() {

        valor++;

        if (valor >= limite) {
            valor = 0;
        }
    }

    public void setValor(int valor) {

        if (valor >= 0 && valor < limite) {
            this.valor = valor;
        }
    }

    public int getValor() {

        return valor;
    }

    public String mostra() {

        if (valor < 10) {
            return "0" + valor;
        }

        return String.valueOf(valor);
    }
}
    
    class Relogio {

    private Mostrador hora;
    private Mostrador minuto;

    public Relogio() {

        hora = new Mostrador(24);
        minuto = new Mostrador(60);
    }

    public void ticTac() {

        minuto.incrementa();

        // Se o minuto voltou para 00,
        // significa que passou uma hora
        if (minuto.getValor() == 0) {
            hora.incrementa();
        }
    }

    public void acertaHora(int hora) {

        this.hora.setValor(hora);
    }

    public void acertaMinuto(int minuto) {

        this.minuto.setValor(minuto);
    }

    public String mostra() {

        return hora.mostra() + ":" + minuto.mostra();
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clockLabel;
    private javax.swing.JButton hourButton;
    private javax.swing.JButton minuteButton;
    private javax.swing.JButton ticTacButton;
    // End of variables declaration//GEN-END:variables
}
