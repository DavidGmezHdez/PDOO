
package GUI;
import Civitas.TituloPropiedad;

public class PropiedadPanel extends javax.swing.JPanel {
    
    TituloPropiedad tituloPropiedad;
    /**
     * Creates new form PropiedadPanel
     */
    public PropiedadPanel() {
        initComponents();
    }
    
    public void setPropiedad(TituloPropiedad titulo){
        tituloPropiedad = titulo;
        this.Nombre.setText(this.tituloPropiedad.getNombre());
        this.Hipotecada.setText(Boolean.toString(this.tituloPropiedad.getHipotecado()));
        this.numCasas.setText(Integer.toString(this.tituloPropiedad.getNumCasas()));
        this.numHoteles.setText(Integer.toString(this.tituloPropiedad.getNumHoteles()));
        this.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombre = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        hipotecada = new javax.swing.JLabel();
        Hipotecada = new javax.swing.JTextField();
        nHoteles = new javax.swing.JLabel();
        numHoteles = new javax.swing.JTextField();
        nCasas = new javax.swing.JLabel();
        numCasas = new javax.swing.JTextField();

        nombre.setText("Nombre:");
        add(nombre);

        Nombre.setText("Nombre");
        add(Nombre);

        hipotecada.setText("Hipotecada:");
        add(hipotecada);

        Hipotecada.setText("Hipotecada");
        add(Hipotecada);

        nHoteles.setText("Número Hoteles:");
        add(nHoteles);

        numHoteles.setText("NumHoteles");
        numHoteles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numHotelesActionPerformed(evt);
            }
        });
        add(numHoteles);

        nCasas.setText("Número Casas:");
        add(nCasas);

        numCasas.setText("NumCasas");
        add(numCasas);
    }// </editor-fold>//GEN-END:initComponents

    private void numHotelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numHotelesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numHotelesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Hipotecada;
    private javax.swing.JTextField Nombre;
    private javax.swing.JLabel hipotecada;
    private javax.swing.JLabel nCasas;
    private javax.swing.JLabel nHoteles;
    private javax.swing.JLabel nombre;
    private javax.swing.JTextField numCasas;
    private javax.swing.JTextField numHoteles;
    // End of variables declaration//GEN-END:variables
}
