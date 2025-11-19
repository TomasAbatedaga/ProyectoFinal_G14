
package Vista;

import Estilo.EstiloVisual;
import Modelo.EspecialidadEnum;
import Modelo.Tratamiento;
import Persistencia.TratamientoData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author abate
 */
public class TratamientoMasSolicitado extends javax.swing.JInternalFrame {

    private ArrayList<Tratamiento> listaT;
    private TratamientoData tratamientodata;
    private DefaultTableModel modeloTabla;

    public TratamientoMasSolicitado() {
        super("Buscar Tratamiento por Tipo", true, true, true, true);
        initComponents();
        EstiloVisual.aplicarEstiloPanel(jPanel1);
        EstiloVisual.aplicarEstiloTabla(jTabTratamientos);
        
        tratamientodata = new TratamientoData();
        listaT = (ArrayList<Tratamiento>) tratamientodata.listarTratamientos();

        modeloTabla = new DefaultTableModel();
        configurarModeloTabla(false);
//        cargarColumnasTablas();
        cargarTratamiento();
        cargarTablaMasVistos();

    }

     private void configurarModeloTabla(boolean mostrarColumnaSesiones) {
        modeloTabla = new DefaultTableModel();
         modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Detalle");
        modeloTabla.addColumn("Duración");
        modeloTabla.addColumn("Costo");

       //booleano true, carga sesiones
        if (mostrarColumnaSesiones) {
            modeloTabla.addColumn("Sesiones Usadas");
        }

        jTabTratamientos.setModel(modeloTabla);
    }
    
//     Cargar los valores del enum en el combo
    public void cargarTratamiento() {

        jCbTratamientos.removeAllItems();
        jCbTratamientos.addItem("Seleccione un tipo...");
        jCbTratamientos.addItem("Todas");
//        java.util.HashSet<String> nombresAgregados = new java.util.HashSet<>();
        
        for (EspecialidadEnum especialidad : EspecialidadEnum.values()) {
            jCbTratamientos.addItem(especialidad.toString());
        
//            if (!nombresAgregados.contains(especialidad.toString())) {
//                jCbTratamientos.addItem(especialidad.toString());
//                nombresAgregados.add(especialidad.toString());
//            
//        }
    }
    }
    
    // Definir columnas de la tabla
//    private void cargarColumnasTablas() {
//        modeloTabla.addColumn("Nombre");
//        modeloTabla.addColumn("Tipo");
//        modeloTabla.addColumn("Detalle");
//        modeloTabla.addColumn("Duración");
//        modeloTabla.addColumn("Costo");
//        modeloTabla.addColumn("Sesiones");
//        jTabTratamientos.setModel(modeloTabla);
//    }
    // Limpia la tabla para dar paso a los nuevos elementos

    private void borrarFilaTabla() {
        int indice = modeloTabla.getRowCount() - 1;
        for (int i = indice; i >= 0; i--) {
            modeloTabla.removeRow(i);
        }
    }

    // Carga y Filtra los tratamientos por el mas visto
    private void cargarTablaMasVistos() {
//        borrarFilaTabla();

        String seleccion = (String)  jCbTratamientos.getSelectedItem();
    if (seleccion == null || seleccion.equals("Seleccione un tipo...")) {
        return;
    }
   
    
        try {
            if (seleccion.equals("Todas")) { 
            configurarModeloTabla(false);
            listaT = (ArrayList<Tratamiento>) tratamientodata.listarTratamientos();
            
            if (listaT != null) {
               for (Tratamiento tratamiento : listaT) {
                modeloTabla.addRow(new Object[]{
                    tratamiento.getNombre(),
                    tratamiento.getEspecialidad(),
                    tratamiento.getDetalle(),
                    tratamiento.getDuracion(),
                    tratamiento.getCosto()
                        
                    });
                }
            }
        } else{
            EspecialidadEnum tipo = EspecialidadEnum.valueOf(seleccion);
            configurarModeloTabla(true);
            listaT = (ArrayList<Tratamiento>) tratamientodata.obtenerTratamientosMasUsadosPorTipo(tipo);
            // Validar lista vacia
            if (listaT == null || listaT.isEmpty()) {
                return;
            }

            for (Tratamiento tratamiento : listaT) {
                modeloTabla.addRow(new Object[]{
                    tratamiento.getNombre(),
                    tratamiento.getEspecialidad(),
                    tratamiento.getDetalle(),
                    tratamiento.getDuracion(),
                    tratamiento.getCosto(),
                    tratamiento.getCantidadSesiones()
                });

            }
            }
        } catch (IllegalArgumentException e) {
        
        JOptionPane.showMessageDialog(this, "Error: El tipo seleccionado no coincide con la base de datos.");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al obtener los Tratamientos: " + e.getMessage());
    }
}

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
        jLabel2 = new javax.swing.JLabel();
        jCbTratamientos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabTratamientos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabTratamientos1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(245, 245, 245));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jLabel1.setFont(new java.awt.Font("SimSun", 1, 30)); // NOI18N
        jLabel1.setText("Tratamientos mas Solicitados");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Seleccione el Tratamiento: ");

        jCbTratamientos.setBackground(new java.awt.Color(224, 255, 255));
        jCbTratamientos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCbTratamientos.setOpaque(true);
        jCbTratamientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbTratamientosActionPerformed(evt);
            }
        });

        jTabTratamientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTabTratamientos);

        jTabTratamientos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTabTratamientos1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCbTratamientos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCbTratamientos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(55, 55, 55)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCbTratamientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbTratamientosActionPerformed
        // TODO add your handling code here:
         cargarTablaMasVistos();

    }//GEN-LAST:event_jCbTratamientosActionPerformed

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
            java.util.logging.Logger.getLogger(TratamientoMasSolicitado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TratamientoMasSolicitado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TratamientoMasSolicitado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TratamientoMasSolicitado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TratamientoMasSolicitado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jCbTratamientos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTabTratamientos;
    private javax.swing.JTable jTabTratamientos1;
    // End of variables declaration//GEN-END:variables
}
