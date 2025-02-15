
package GUI;

import Civitas.Jugador;
import Civitas.TituloPropiedad;
import java.util.ArrayList;

public class JugadorPanel extends javax.swing.JPanel {

    public Jugador jugador;
    /**
     * Creates new form JugadorPanel
     */
    public JugadorPanel() {
        initComponents();
    }
    
    public void setJugador(Jugador jug){
        this.jugador = jug;
        this.Nombre.setText(jugador.getNombre());
        this.Saldo.setText(Float.toString(jugador.getSaldo()));
        this.Encarcelado.setText(Boolean.toString(jugador.isEncarcelado()));
        this.Especulador.setText(Boolean.toString(jugador.getEspeculador()));
        rellenaPropiedades(jugador.getPropiedades());
        repaint();
        revalidate();
    }
    
    public void rellenaPropiedades(ArrayList<TituloPropiedad> lista){
        this.propiedades.removeAll();
        for(TituloPropiedad t:lista){
            PropiedadPanel vistaPropiedad = new PropiedadPanel();
            vistaPropiedad.setPropiedad(t);
            
            propiedades.add(vistaPropiedad);
            vistaPropiedad.setVisible(true);
        }
        repaint();
        revalidate();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        encarcelado = new javax.swing.JLabel();
        saldo = new javax.swing.JLabel();
        especulador = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        Saldo = new javax.swing.JTextField();
        Encarcelado = new javax.swing.JTextField();
        Especulador = new javax.swing.JTextField();
        propiedades = new javax.swing.JPanel();

        encarcelado.setText("Encarcelado:");

        saldo.setText("Saldo:");

        especulador.setText("Especulador:");

        nombre.setText("Nombre:");

        Nombre.setText("Nombre");
        Nombre.setEnabled(false);

        Saldo.setText("Saldo");
        Saldo.setEnabled(false);
        Saldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaldoActionPerformed(evt);
            }
        });

        Encarcelado.setText("Encarcelado");
        Encarcelado.setEnabled(false);
        Encarcelado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EncarceladoActionPerformed(evt);
            }
        });

        Especulador.setText("Especulador");
        Especulador.setEnabled(false);
        Especulador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EspeculadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saldo)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombre)
                            .addComponent(encarcelado)
                            .addComponent(especulador))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Encarcelado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Especulador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(propiedades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saldo)
                    .addComponent(Saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Encarcelado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encarcelado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Especulador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(especulador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(propiedades, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SaldoActionPerformed

    private void EncarceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EncarceladoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EncarceladoActionPerformed

    private void EspeculadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EspeculadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EspeculadorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Encarcelado;
    private javax.swing.JTextField Especulador;
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField Saldo;
    private javax.swing.JLabel encarcelado;
    private javax.swing.JLabel especulador;
    private javax.swing.JLabel nombre;
    private javax.swing.JPanel propiedades;
    private javax.swing.JLabel saldo;
    // End of variables declaration//GEN-END:variables
}
