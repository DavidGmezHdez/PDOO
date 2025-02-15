
package GUI;

import Civitas.CivitasJuego;
import Civitas.OperacionesJuego;
import Civitas.SalidasCarcel;
import Civitas.Diario;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CivitasView extends javax.swing.JFrame {

    CivitasJuego juego;
    JugadorPanel jugadorPanel;
    CasillaView casilla;
    GestionarDialog gestionarD = new GestionarDialog(this,true);
    /**
     * Creates new form CivitasView
     */
    public CivitasView() {
        initComponents();
        jugadorPanel = new JugadorPanel();
        casilla = new CasillaView();
        this.contenedorVistaJugador.add(jugadorPanel);
        this.casillaActual.add(casilla);
        repaint();
        revalidate();
    }

    
    public void setCivitas(CivitasJuego civitas){
        this.juego = civitas;
        this.setVisible(true);
    }
    
    public void actualizarVista(){
        this.jugadorPanel.setJugador(juego.getJugadorActual());
        this.jugadorPanel.setVisible(true);
        //this.casilla.setCasilla(juego.getCasillaActual());
        //this.casilla.setVisible(true);
        
        this.casillaActual.setText(this.juego.getCasillaActual().toString());
        this.Ranking.setText((this.juego.ranking()).toString());
        this.Ranking.setVisible(this.juego.finalDelJuego());
        
        mostrarSiguienteOperacion(this.juego.siguientePaso());
        mostrarEventos();
        repaint();
        revalidate();
    }
    
    public void mostrarSiguienteOperacion(OperacionesJuego operacion){
        this.siguienteOperacion.setText(operacion.toString());
        this.siguienteOperacion.setVisible(true);
        //actualizarVista();
        repaint();
        revalidate();
    }
    
    public void gestionar(){
        this.gestionarD.gestionar(juego.getJugadorActual());
        gestionarD.pack();
        gestionarD.repaint();
        gestionarD.revalidate();
        this.gestionarD.setVisible(true);
    }

    public void mostrarEventos() {
        if (Diario.getInstance().eventosPendientes()) {
            DiarioDialog diarioD = new DiarioDialog(this); //crea la ventana del diario
        }
    }

    Respuestas comprar() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres comprar la calle actual?",
                "Compra", JOptionPane.YES_NO_OPTION);
        return (Respuestas.values()[opcion]);
    }
    
    SalidasCarcel salirCarcel(){
        String[] opciones = {"PAGANDO","TIRANDO"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Cómo quieres salir de la cárcel?",
        "Salir de la cárcel", JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,null, opciones, opciones[0] );
        return SalidasCarcel.values()[respuesta];
    }
    
    
    public int getGestion(){
        return this.gestionarD.getGestionElegida();
    }
    
    public int getPropiedad(){
        return this.gestionarD.getPropiedadElegida();
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CivitasView = new javax.swing.JLabel();
        contenedorVistaJugador = new javax.swing.JPanel();
        siguienteOperacion = new javax.swing.JTextField();
        Ranking = new javax.swing.JTextField();
        casillaActual = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setEnabled(false);

        CivitasView.setText("CivitasView");
        CivitasView.setEnabled(false);

        siguienteOperacion.setText("SiguienteOperacion");

        Ranking.setText("Ranking");

        casillaActual.setText("casillaActual");
        casillaActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                casillaActualActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(CivitasView)
                        .addGap(443, 443, 443))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(siguienteOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 284, Short.MAX_VALUE))
                            .addComponent(casillaActual)
                            .addComponent(Ranking, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(contenedorVistaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(CivitasView)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(Ranking, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(siguienteOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(casillaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(68, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(contenedorVistaJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void casillaActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_casillaActualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_casillaActualActionPerformed

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
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CivitasView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CivitasView;
    private javax.swing.JTextField Ranking;
    private javax.swing.JTextField casillaActual;
    private javax.swing.JPanel contenedorVistaJugador;
    private javax.swing.JTextField siguienteOperacion;
    // End of variables declaration//GEN-END:variables
}
