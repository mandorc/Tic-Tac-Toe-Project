
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

/**
 *
 * @author armandorivera
 */
public class StartFrame extends javax.swing.JFrame {

    /**
     * Creates new form StartFrame
     */
    public StartFrame() {
        // Intentaba poner un icono pero al parecer en macOS no sirve - no se si en windows se vea
        ImageIcon logo = new ImageIcon("logo.png");
        this.setIconImage(logo.getImage());
        // Iniciamos los componentes
        initComponents();
        // Aparece el frame en el centro
        this.setLocationRelativeTo(null);
        // No se modifica el tamaño
        setResizable(false);
        // No podemos empezar el juego sin que el jugador haya seleccionado un personaje
        // por eso deshabilitamos el boton de empezar hasta que se seleccione un jugador
        playButton.setEnabled(false);
    }
    
    /**
     * VARIABLES ///
     */
    
    // Va a jugar con otra persona o contra la computadora?
    boolean iaPlaying = false;
    // Personaje seleccionado por el usuario
    String playerName;
    
    /**
     * /// VARIABLES
     */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        infoButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        oPlayerButton = new javax.swing.JButton();
        xPlayerButton1 = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        playIACheckbox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setText("Selecciona tu personaje");

        infoButton.setText("Info");
        infoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("X empieza primero");

        oPlayerButton.setFont(new java.awt.Font("Helvetica Neue", 0, 36)); // NOI18N
        oPlayerButton.setText("O");
        oPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oPlayerButtonActionPerformed(evt);
            }
        });

        xPlayerButton1.setFont(new java.awt.Font("Helvetica Neue", 0, 36)); // NOI18N
        xPlayerButton1.setText("X");
        xPlayerButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xPlayerButton1ActionPerformed(evt);
            }
        });

        playButton.setText("Empezar a jugar");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        playIACheckbox.setText("Jugar contra la computadora");
        playIACheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playIACheckboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 96, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(92, 92, 92))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(143, 143, 143))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(infoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(playIACheckbox)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(playButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(xPlayerButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(oPlayerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xPlayerButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oPlayerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(infoButton)
                    .addComponent(playIACheckbox))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playIACheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playIACheckboxActionPerformed
        // Cuando modifica el checkbox el usuario sabemos si va a jugar o no con el sistema
        if(this.playIACheckbox.isSelected())
            // va a jugar con el sistema
            iaPlaying = true;
        else
            // No va a jugar con el sistema
            iaPlaying = false;
        
        //System.out.println("Va a jugar con IA? " +iaPlaying);        
            
    }//GEN-LAST:event_playIACheckboxActionPerformed

    
    private void xPlayerButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xPlayerButton1ActionPerformed
        // Cuando el usuario ha seleccionado el personaje X
        playerName = xPlayerButton1.getText();
        // Cuando ya se selecciono el personaje habilitamos el boton para empezar el juego
        playButton.setEnabled(true);
        // Mostramos en consola que jugador seleccionó
        //System.out.println("Seleccionó el jugador: " +playerName);
        
    }//GEN-LAST:event_xPlayerButton1ActionPerformed

    private void oPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oPlayerButtonActionPerformed
        // Cuando el usuario ha seleccionado el personaje O
        playerName = oPlayerButton.getText();
        // Cuando ya se selecciono el personaje habilitamos el boton para empezar el juego
        playButton.setEnabled(true);
        // Mostramos en consola que jugador seleccionó
        //System.out.println("Seleccionó el jugador: " +playerName);
    }//GEN-LAST:event_oPlayerButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        // Cuando el usuario da clic en empezar juego:
        System.out.println("Va a jugar con el personaje " +playerName);
        System.out.println("Y va a jugar con IA: " +iaPlaying);
        // Mandamos los parametros a nuestra nueva ventana donde jugaremos
        JFrame gameFrame = new GameFrame(playerName, iaPlaying);
        // Mostramos el frame del juego
        gameFrame.setVisible(true);
        // Ocultamos este frame
        this.setVisible(false);
        
        
    }//GEN-LAST:event_playButtonActionPerformed

    private void infoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoButtonActionPerformed
        // Si el usuario quiere ver info del proyecto:
        JFrame aboutFrame = new AboutFrame();
        aboutFrame.setVisible(true);
    }//GEN-LAST:event_infoButtonActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon("src/logo.png");
        setIconImage(icon.getImage());
    }//GEN-LAST:event_formWindowActivated

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton infoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton oPlayerButton;
    private javax.swing.JButton playButton;
    private javax.swing.JCheckBox playIACheckbox;
    private javax.swing.JButton xPlayerButton1;
    // End of variables declaration//GEN-END:variables
}
