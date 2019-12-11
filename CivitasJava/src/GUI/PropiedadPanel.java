
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
        this.alquilerBase.setText(Float.toString(this.tituloPropiedad.getAlquilerBase()));
        this.factorRevalorización.setText(Float.toString(this.tituloPropiedad.getFactorRevalorización()));
        this.hipotecaBase.setText(Float.toString(this.tituloPropiedad.getHipotecaBase()));
        this.hipotecado.setText(Boolean.toString(this.tituloPropiedad.getHipotecado()));
        this.numCasas.setText(Integer.toString(this.tituloPropiedad.getNumCasas()));
        this.numHoteles.setText(Integer.toString(this.tituloPropiedad.getNumHoteles()));
        this.precioCompra.setText(Float.toString(this.tituloPropiedad.getPrecioCompra()));
        this.precioEdificar.setText(Float.toString(this.tituloPropiedad.getPrecioEdificar()));
        this.propietario.setText(this.tituloPropiedad.getPropietario().getNombre());
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

        alquilerBase = new javax.swing.JLabel();
        factorRevalorización = new javax.swing.JLabel();
        hipotecaBase = new javax.swing.JLabel();
        precioCompra = new javax.swing.JLabel();
        precioEdificar = new javax.swing.JLabel();
        propietario = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        numCasas = new javax.swing.JTextField();
        numHoteles = new javax.swing.JTextField();
        hipotecado = new javax.swing.JTextField();

        alquilerBase.setText("alquilerBase");
        alquilerBase.setEnabled(false);
        alquilerBase.setFocusable(false);

        factorRevalorización.setText("factorRevalorización");
        factorRevalorización.setEnabled(false);

        hipotecaBase.setText("hipotecaBase");
        hipotecaBase.setEnabled(false);

        precioCompra.setText("precioCompra");
        precioCompra.setEnabled(false);

        precioEdificar.setText("precioEdificar");
        precioEdificar.setEnabled(false);

        propietario.setText("propietario");
        propietario.setEnabled(false);

        Nombre.setText("Nombre");

        numCasas.setText("NumCasas");

        numHoteles.setText("NumHoteles");

        hipotecado.setText("Hipotecado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hipotecado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numHoteles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numCasas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(propietario)
                    .addComponent(precioEdificar)
                    .addComponent(precioCompra)
                    .addComponent(hipotecaBase)
                    .addComponent(factorRevalorización)
                    .addComponent(alquilerBase))
                .addContainerGap(168, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alquilerBase)
                .addGap(28, 28, 28)
                .addComponent(factorRevalorización)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hipotecaBase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hipotecado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(precioCompra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(precioEdificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numCasas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numHoteles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(propietario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Nombre;
    private javax.swing.JLabel alquilerBase;
    private javax.swing.JLabel factorRevalorización;
    private javax.swing.JLabel hipotecaBase;
    private javax.swing.JTextField hipotecado;
    private javax.swing.JTextField numCasas;
    private javax.swing.JTextField numHoteles;
    private javax.swing.JLabel precioCompra;
    private javax.swing.JLabel precioEdificar;
    private javax.swing.JLabel propietario;
    // End of variables declaration//GEN-END:variables
}
