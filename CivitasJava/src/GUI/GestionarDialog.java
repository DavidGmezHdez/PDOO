
package GUI;

import Civitas.Jugador;
import Civitas.GestionesInmobiliarias;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;

public class GestionarDialog extends javax.swing.JDialog {
    private int gestionElegida = -1, propiedadElegida = -1;
    /**
     * Creates new form GestionarDialog
     */
    public GestionarDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    public int getGestionElegida(){
        return this.gestionElegida;
    }
    
    public int getPropiedadElegida(){
        return this.propiedadElegida;
    }
    
    public void setGestiones(){
        DefaultListModel gestiones = new DefaultListModel<>(); //datos para la lista
        
        ArrayList<String> opciones = new ArrayList<>(Arrays.asList("Terminar",
                "Vender","Hipotecar","Cancelar Hipoteca","Construir Casa","Construir Hotel"));
        
        for(String s:opciones){
            gestiones.addElement(s); //se completan los datos
        }
        
        this.listaGestiones.setModel(gestiones);
    }
    
    public void setPropiedades(Jugador jugador){
        DefaultListModel propiedades = new DefaultListModel<>(); //datos para la lista
        ArrayList<String>  nombresPropiedades = new ArrayList<>();
        
        switch(this.gestionElegida){
            case 1:
                nombresPropiedades.clear();
                for(int i=0;i<jugador.getPropiedades().size();i++){
                    if(!nombresPropiedades.contains(jugador.getPropiedades().get(i).getNombre()))
                        nombresPropiedades.add(jugador.getPropiedades().get(i).getNombre());
                }
              
                for(String s:nombresPropiedades){
                    propiedades.addElement(s);
                }
                
                this.listaPropiedades.setModel(propiedades);
                break;
            
            case 2:
                nombresPropiedades.clear();
                for(int i=0;i<jugador.getPropiedades().size();i++){
                    if(!jugador.getPropiedades().get(i).getHipotecado() 
                            && !nombresPropiedades.contains(jugador.getPropiedades().get(i).getNombre()))
                        nombresPropiedades.add(jugador.getPropiedades().get(i).getNombre());
                }
              
                for(String s:nombresPropiedades){
                    propiedades.addElement(s);
                }
                
                this.listaPropiedades.setModel(propiedades);
                break;
            
            case 3:
                nombresPropiedades.clear();
                for(int i=0;i<jugador.getPropiedades().size();i++){
                    if(jugador.getPropiedades().get(i).getHipotecado() 
                            && !nombresPropiedades.contains(jugador.getPropiedades().get(i).getNombre()))
                        nombresPropiedades.add(jugador.getPropiedades().get(i).getNombre());
                }
              
                for(String s:nombresPropiedades){
                    propiedades.addElement(s);
                }
                
                this.listaPropiedades.setModel(propiedades);
                break;
            case 4:
                nombresPropiedades.clear();
                for(int i=0;i<jugador.getPropiedades().size();i++){
                    if(jugador.puedoEdificarCasa(jugador.getPropiedades().get(i)) 
                            && !nombresPropiedades.contains(jugador.getPropiedades().get(i).getNombre()))
                        nombresPropiedades.add(jugador.getPropiedades().get(i).getNombre());
                }
              
                for(String s:nombresPropiedades){
                    propiedades.addElement(s);
                }
                
                this.listaPropiedades.setModel(propiedades);
                break;
            
            case 5:
                nombresPropiedades.clear();
                for(int i=0;i<jugador.getPropiedades().size();i++){
                    if(jugador.puedoEdificarHotel(jugador.getPropiedades().get(i)) 
                            && !nombresPropiedades.contains(jugador.getPropiedades().get(i).getNombre()))
                        nombresPropiedades.add(jugador.getPropiedades().get(i).getNombre());
                }
              
                for(String s:nombresPropiedades){
                    propiedades.addElement(s);
                }
                
                this.listaPropiedades.setModel(propiedades);
                break;      
        }
    }
    
    
    public void gestionar(Jugador jugador){
        setGestiones();
        
        setPropiedades(jugador);
        
        repaint();
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gestionarDialog = new javax.swing.JLabel();
        Gestiones = new javax.swing.JLabel();
        Propiedades = new javax.swing.JLabel();
        scrollGestiones = new javax.swing.JScrollPane();
        listaGestiones = new javax.swing.JList<>();
        scrollPropiedades = new javax.swing.JScrollPane();
        listaPropiedades = new javax.swing.JList<>();
        Realizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        gestionarDialog.setText("GestionarDialog");

        Gestiones.setText("Gestiones");

        Propiedades.setText("Propiedades");

        listaGestiones.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaGestiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaGestionesMouseClicked(evt);
            }
        });
        scrollGestiones.setViewportView(listaGestiones);

        listaPropiedades.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaPropiedades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaPropiedadesMouseClicked(evt);
            }
        });
        scrollPropiedades.setViewportView(listaPropiedades);

        Realizar.setText("Realizar");
        Realizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(Gestiones))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(gestionarDialog))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(scrollGestiones, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Realizar)
                        .addGap(3, 3, 3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Propiedades)
                        .addGap(65, 65, 65))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(scrollPropiedades, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gestionarDialog)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Gestiones)
                    .addComponent(Propiedades))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(scrollPropiedades)
                        .addComponent(scrollGestiones))
                    .addComponent(Realizar))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listaGestionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaGestionesMouseClicked
        gestionElegida = this.listaGestiones.getSelectedIndex();
    }//GEN-LAST:event_listaGestionesMouseClicked

    private void listaPropiedadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPropiedadesMouseClicked
        propiedadElegida = this.listaPropiedades.getSelectedIndex();
    }//GEN-LAST:event_listaPropiedadesMouseClicked

    private void RealizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RealizarActionPerformed
        this.gestionElegida = this.listaGestiones.getSelectedIndex();
        this.propiedadElegida = this.listaPropiedades.getSelectedIndex();
        this.dispose();
    }//GEN-LAST:event_RealizarActionPerformed

    /**
     * @param args the command line arguments
     */
    
    
//    
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                GestionarDialog dialog = new GestionarDialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Gestiones;
    private javax.swing.JLabel Propiedades;
    private javax.swing.JButton Realizar;
    private javax.swing.JLabel gestionarDialog;
    private javax.swing.JList<String> listaGestiones;
    private javax.swing.JList<String> listaPropiedades;
    private javax.swing.JScrollPane scrollGestiones;
    private javax.swing.JScrollPane scrollPropiedades;
    // End of variables declaration//GEN-END:variables
}
