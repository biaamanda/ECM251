/*Utilizando o desenvolvimento da GUI com o NetBeans, faça
uma calculadora com as 4 operações básicas, com botões de 0
a 9 para a entrada dos dígitos e um único mostrador que
mostra os números digitados e o resultado das operações.
Aceite apenas inteiros e faça as operações somente com
inteiros*/

package exemplo_aula14;

import javax.swing.JTextField;

public class Ex04 extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Ex04.class.getName());

    private JTextField textField;
    private int primeiroValor = 0;
    private String operador = "";
    private boolean iniciarNovoNumero = true;
    
    public Ex04() {
        super("Calculadora");
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
 
        textField = new javax.swing.JTextField();
        setenButton = new javax.swing.JButton();
        eightButton = new javax.swing.JButton();
        nineButton = new javax.swing.JButton();
        sumButton = new javax.swing.JButton();
        fourButton = new javax.swing.JButton();
        oneButton = new javax.swing.JButton();
        zeroButton = new javax.swing.JButton();
        fiveButton = new javax.swing.JButton();
        twoButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        sixButton = new javax.swing.JButton();
        subtractButton = new javax.swing.JButton();
        threeButton = new javax.swing.JButton();
        multiplicationButton = new javax.swing.JButton();
        equalButton = new javax.swing.JButton();
        divisionButton = new javax.swing.JButton();
 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
 
        textField.addActionListener(this::textFieldActionPerformed);
 
        setenButton.setText("7");
        setenButton.addActionListener(this::setenButtonActionPerformed);
 
        eightButton.setText("8");
        eightButton.addActionListener(this::eightButtonActionPerformed);
 
        nineButton.setText("9");
        nineButton.addActionListener(this::nineButtonActionPerformed);
 
        sumButton.setText("+");
        sumButton.addActionListener(this::sumButtonActionPerformed);
 
        fourButton.setText("4");
        fourButton.addActionListener(this::fourButtonActionPerformed);
 
        oneButton.setText("1");
        oneButton.addActionListener(this::oneButtonActionPerformed);
 
        zeroButton.setText("0");
        zeroButton.addActionListener(this::zeroButtonActionPerformed);
 
        fiveButton.setText("5");
        fiveButton.addActionListener(this::fiveButtonActionPerformed);
 
        twoButton.setText("2");
        twoButton.addActionListener(this::twoButtonActionPerformed);
 
        resetButton.setText("C");
        resetButton.addActionListener(this::resetButtonActionPerformed);
 
        sixButton.setText("6");
        sixButton.addActionListener(this::sixButtonActionPerformed);
 
        subtractButton.setText("-");
        subtractButton.addActionListener(this::subtractButtonActionPerformed);
 
        threeButton.setText("3");
        threeButton.addActionListener(this::threeButtonActionPerformed);
 
        multiplicationButton.setText("*");
        multiplicationButton.addActionListener(this::multiplicationButtonActionPerformed);
 
        equalButton.setText("=");
        equalButton.addActionListener(this::equalButtonActionPerformed);
 
        divisionButton.setText("\\");
        divisionButton.addActionListener(this::divisionButtonActionPerformed);
 
        }
    
    private void adicionarDigito(String digito) {
        if (iniciarNovoNumero) {
            textField.setText(digito);
            iniciarNovoNumero = false;
        } else {
            if (textField.getText().equals("0")) {
                textField.setText(digito);
            } else {
                textField.setText(textField.getText() + digito);
            }
        }
    }
 
    private void calculate() {
        if (operador.isEmpty() || textField.getText().isEmpty()) {
            return;
        }
 
        int segundoValor = Integer.parseInt(textField.getText());
        int resultado = 0;
 
        switch (operador) {
            case "+":
                resultado = primeiroValor + segundoValor;
                break;
            case "-":
                resultado = primeiroValor - segundoValor;
                break;
            case "*":
                resultado = primeiroValor * segundoValor;
                break;
            case "\\":
                if (segundoValor == 0) {
                    textField.setText("Erro: /0");
                    operador = "";
                    iniciarNovoNumero = true;
                    return;
                }
                resultado = primeiroValor / segundoValor;
                break;
            default:
                return;
        }
 
        textField.setText(String.valueOf(resultado));
        primeiroValor = resultado;
        operador = "";
        iniciarNovoNumero = true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField = new javax.swing.JTextField();
        setenButton = new javax.swing.JButton();
        eightButton = new javax.swing.JButton();
        nineButton = new javax.swing.JButton();
        sumButton = new javax.swing.JButton();
        fourButton = new javax.swing.JButton();
        oneButton = new javax.swing.JButton();
        zeroButton = new javax.swing.JButton();
        fiveButton = new javax.swing.JButton();
        twoButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        sixButton = new javax.swing.JButton();
        subtractButton = new javax.swing.JButton();
        threeButton = new javax.swing.JButton();
        multiplicationButton = new javax.swing.JButton();
        equalButton = new javax.swing.JButton();
        divisionButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textField.addActionListener(this::textFieldActionPerformed);

        setenButton.setText("7");
        setenButton.addActionListener(this::setenButtonActionPerformed);

        eightButton.setText("8");
        eightButton.addActionListener(this::eightButtonActionPerformed);

        nineButton.setText("9");
        nineButton.addActionListener(this::nineButtonActionPerformed);

        sumButton.setText("+");
        sumButton.addActionListener(this::sumButtonActionPerformed);

        fourButton.setText("4");
        fourButton.addActionListener(this::fourButtonActionPerformed);

        oneButton.setText("1");
        oneButton.addActionListener(this::oneButtonActionPerformed);

        zeroButton.setText("0");
        zeroButton.addActionListener(this::zeroButtonActionPerformed);

        fiveButton.setText("5");
        fiveButton.addActionListener(this::fiveButtonActionPerformed);

        twoButton.setText("2");
        twoButton.addActionListener(this::twoButtonActionPerformed);

        resetButton.setText("C");
        resetButton.addActionListener(this::resetButtonActionPerformed);

        sixButton.setText("6");
        sixButton.addActionListener(this::sixButtonActionPerformed);

        subtractButton.setText("-");
        subtractButton.addActionListener(this::subtractButtonActionPerformed);

        threeButton.setText("3");
        threeButton.addActionListener(this::threeButtonActionPerformed);

        multiplicationButton.setText("*");
        multiplicationButton.addActionListener(this::multiplicationButtonActionPerformed);

        equalButton.setText("=");
        equalButton.addActionListener(this::equalButtonActionPerformed);

        divisionButton.setText("\\");
            divisionButton.addActionListener(this::divisionButtonActionPerformed);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(14, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(oneButton)
                                    .addGap(18, 18, 18)
                                    .addComponent(twoButton)
                                    .addGap(18, 18, 18)
                                    .addComponent(threeButton)
                                    .addGap(18, 18, 18)
                                    .addComponent(multiplicationButton))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(fourButton)
                                            .addGap(18, 18, 18)
                                            .addComponent(fiveButton)
                                            .addGap(18, 18, 18)
                                            .addComponent(sixButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(setenButton)
                                            .addGap(18, 18, 18)
                                            .addComponent(eightButton)
                                            .addGap(18, 18, 18)
                                            .addComponent(nineButton)))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(sumButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(subtractButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(zeroButton)
                                    .addGap(18, 18, 18)
                                    .addComponent(resetButton)
                                    .addGap(18, 18, 18)
                                    .addComponent(equalButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(divisionButton)))
                            .addGap(12, 12, 12))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(setenButton)
                        .addComponent(eightButton)
                        .addComponent(nineButton)
                        .addComponent(sumButton))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fourButton)
                        .addComponent(fiveButton)
                        .addComponent(sixButton)
                        .addComponent(subtractButton))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(oneButton)
                        .addComponent(twoButton)
                        .addComponent(threeButton)
                        .addComponent(multiplicationButton))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(zeroButton)
                        .addComponent(resetButton)
                        .addComponent(equalButton)
                        .addComponent(divisionButton))
                    .addContainerGap(23, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void setenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setenButtonActionPerformed
        adicionarDigito("7");
    }//GEN-LAST:event_setenButtonActionPerformed

    private void sixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sixButtonActionPerformed
        adicionarDigito("6");
    }//GEN-LAST:event_sixButtonActionPerformed

    private void fourButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourButtonActionPerformed
        adicionarDigito("4");
    }//GEN-LAST:event_fourButtonActionPerformed

    private void divisionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divisionButtonActionPerformed
        adicionarDigito("/");
    }//GEN-LAST:event_divisionButtonActionPerformed

    private void textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldActionPerformed
        calculate();
    }//GEN-LAST:event_textFieldActionPerformed

    private void oneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneButtonActionPerformed
        adicionarDigito("1");
    }//GEN-LAST:event_oneButtonActionPerformed

    private void zeroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zeroButtonActionPerformed
        adicionarDigito("0");
    }//GEN-LAST:event_zeroButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        textField.setText("");
        primeiroValor = 0;
        operador = "";
        iniciarNovoNumero = true;
    }//GEN-LAST:event_resetButtonActionPerformed

    private void equalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equalButtonActionPerformed
        calculate();
    }//GEN-LAST:event_equalButtonActionPerformed

    private void multiplicationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiplicationButtonActionPerformed
        adicionarDigito("x");
    }//GEN-LAST:event_multiplicationButtonActionPerformed

    private void subtractButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtractButtonActionPerformed
        adicionarDigito("-");
    }//GEN-LAST:event_subtractButtonActionPerformed

    private void sumButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sumButtonActionPerformed
        adicionarDigito("+");
    }//GEN-LAST:event_sumButtonActionPerformed

    private void nineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nineButtonActionPerformed
        adicionarDigito("9");
    }//GEN-LAST:event_nineButtonActionPerformed

    private void threeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_threeButtonActionPerformed
        adicionarDigito("3");
    }//GEN-LAST:event_threeButtonActionPerformed

    private void twoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoButtonActionPerformed
        adicionarDigito("2");
    }//GEN-LAST:event_twoButtonActionPerformed

    private void fiveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiveButtonActionPerformed
        adicionarDigito("5");
    }//GEN-LAST:event_fiveButtonActionPerformed

    private void eightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightButtonActionPerformed
        adicionarDigito("8");
    }//GEN-LAST:event_eightButtonActionPerformed

    /**
     * @param args the command line arguments
     */
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
        java.awt.EventQueue.invokeLater(() -> new Ex04().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton divisionButton;
    private javax.swing.JButton eightButton;
    private javax.swing.JButton equalButton;
    private javax.swing.JButton fiveButton;
    private javax.swing.JButton fourButton;
    private javax.swing.JButton multiplicationButton;
    private javax.swing.JButton nineButton;
    private javax.swing.JButton oneButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton setenButton;
    private javax.swing.JButton sixButton;
    private javax.swing.JButton subtractButton;
    private javax.swing.JButton sumButton;
    private javax.swing.JTextField textField;
    private javax.swing.JButton threeButton;
    private javax.swing.JButton twoButton;
    private javax.swing.JButton zeroButton;
    // End of variables declaration//GEN-END:variables
}
