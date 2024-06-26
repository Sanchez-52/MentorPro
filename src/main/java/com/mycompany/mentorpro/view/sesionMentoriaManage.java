package com.mycompany.mentorpro.view;

import com.mycompany.mentorpro.control.SesionMentoriaController;
import com.mycompany.mentorpro.model.SesionMentoria;
import com.mycompany.mentorpro.model.SesionMentoriaDetalle;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class sesionMentoriaManage extends javax.swing.JFrame {

    private SesionMentoriaController sesionMentoriaController;

    /**
     * Creates new form managingLayout
     */
    public sesionMentoriaManage() {
        ViewUtils.setWindowIcon(this);
        initComponents();

        //Inicializa el controlador
        sesionMentoriaController = new SesionMentoriaController();

        //Llena la tabla de sesiones al iniciar la vista
        llenarTablaSesiones();
    }

    //Función para llenar la tablaSesiones
    public void llenarTablaSesiones() {
        //Obtén la lista de sesiones desde el controlador
        List<SesionMentoriaDetalle> sesiones = sesionMentoriaController.getSesionesMentoria();

        //Crea un modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Fecha");
        model.addColumn("Mentor");
        model.addColumn("Estudiante");
        model.addColumn("Tipo");
        model.addColumn("Hora");
        model.addColumn("Duración (h)");
        model.addColumn("Estado");

        //Agregar filas a partir de la lista de sesiones
        for (SesionMentoriaDetalle sesionesMentoria : sesiones) {
            model.addRow(new Object[]{
                sesionesMentoria.getId(),
                sesionesMentoria.getFecha(),
                sesionesMentoria.getNombreMentor(),
                sesionesMentoria.getNombreEstudiante(),
                sesionesMentoria.getTipoSesion().equals("V") ? "VIRTUAL" : "PRESENCIAL",
                sesionesMentoria.getHora(),
                sesionesMentoria.getDuracion(),
                sesionesMentoria.getEstado()});
        }

        //Establece el modelo en la tabla
        tablaSesiones.setModel(model);

        //Configura las celdas de la tabla como no editables
        for (int i = 0; i < tablaSesiones.getColumnCount(); i++) {
            TableColumn column = tablaSesiones.getColumnModel().getColumn(i);
            column.setCellEditor(null);
        }
    }

    public void llenarTablaSesionesFiltrado() {
        //Obtener los criterios de filtrado de la vista
        Date fecha = fechaPicker.getDate();

        String nombreMentor = mentorTxt.getText();

        String nombreEstudiante = estudianteTxt.getText();

        //Obtén la lista de sesiones desde el controlador
        List<SesionMentoriaDetalle> sesiones = sesionMentoriaController.getSesionesMentoriaFiltrado(fecha, nombreMentor, nombreEstudiante);

        //Crea un modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Fecha");
        model.addColumn("Mentor");
        model.addColumn("Estudiante");
        model.addColumn("Tipo");
        model.addColumn("Hora");
        model.addColumn("Duración (h)");
        model.addColumn("Estado");

        //Agregar filas a partir de la lista de sesiones
        for (SesionMentoriaDetalle sesionesMentoria : sesiones) {
            model.addRow(new Object[]{
                sesionesMentoria.getId(),
                sesionesMentoria.getFecha(),
                sesionesMentoria.getNombreMentor(),
                sesionesMentoria.getNombreEstudiante(),
                sesionesMentoria.getTipoSesion().equals("V") ? "VIRTUAL" : "PRESENCIAL",
                sesionesMentoria.getHora(),
                sesionesMentoria.getDuracion(),
                sesionesMentoria.getEstado()});
        }

        //Establece el modelo en la tabla
        tablaSesiones.setModel(model);

        //Configura las celdas de la tabla como no editables
        for (int i = 0; i < tablaSesiones.getColumnCount(); i++) {
            TableColumn column = tablaSesiones.getColumnModel().getColumn(i);
            column.setCellEditor(null);
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
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(60, 0), new java.awt.Dimension(60, 0), new java.awt.Dimension(60, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSesiones = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        filterBtn = new javax.swing.JButton();
        insertBtn = new javax.swing.JButton();
        imprimirBtn = new javax.swing.JButton();
        volverBtn = new javax.swing.JButton();
        fechaPicker = new com.toedter.calendar.JDateChooser();
        estudianteTxt = new javax.swing.JTextField();
        mentorTxt = new javax.swing.JTextField();
        editBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(java.awt.Color.darkGray);
        jPanel1.setPreferredSize(new java.awt.Dimension(850, 480));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MentorPRO");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Sesiones de Mentoría");

        tablaSesiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "sample"
            }
        ));
        jScrollPane1.setViewportView(tablaSesiones);

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Estudiante");

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Mentor");

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Fecha");

        filterBtn.setText("Filtrar");
        filterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterBtnActionPerformed(evt);
            }
        });

        insertBtn.setText("Agregar");
        insertBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertBtnActionPerformed(evt);
            }
        });

        imprimirBtn.setText("Imprimir");
        imprimirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirBtnActionPerformed(evt);
            }
        });

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        editBtn.setText("Editar");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(imprimirBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(volverBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estudianteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mentorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(filterBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(insertBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filler2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(imprimirBtn)
                            .addComponent(volverBtn))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5)
                                .addComponent(filterBtn)
                                .addComponent(insertBtn)
                                .addComponent(estudianteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mentorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(editBtn))
                            .addComponent(fechaPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(33, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterBtnActionPerformed
        // TODO add your handling code here:
        llenarTablaSesionesFiltrado();
    }//GEN-LAST:event_filterBtnActionPerformed

    private void insertBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        sesionMentoriaInsert agregarSesion = new sesionMentoriaInsert();
        agregarSesion.setVisible(true);
        agregarSesion.setLocationRelativeTo(null);
    }//GEN-LAST:event_insertBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        // Verfica si hay una fila seleccionada en la tabla
        if (tablaSesiones.getSelectedRow() == -1) {
            // Si no hay ninguna fila seleccionada, muestra una notificación o alerta al usuario
            JOptionPane.showMessageDialog(sesionMentoriaManage.this, "Por favor, seleccione una fila antes de editar.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Si hay una fila seleccionada, obtiene el ID de la sesion seleccinada
            int filaSeleccionada = tablaSesiones.getSelectedRow();
            Long idSesion = (Long) tablaSesiones.getValueAt(filaSeleccionada, 0);

            //Activar la vista de edición y enviar Id de sesion
            sesionMentoriaInsert editFrame = new sesionMentoriaInsert();
            editFrame.setCodSesion(idSesion);
            setVisible(false);
            editFrame.setVisible(true);
            editFrame.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }//GEN-LAST:event_volverBtnActionPerformed

    private void imprimirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirBtnActionPerformed
        // TODO add your handling code here:
        //Inicializamos el controlador
        SesionMentoriaController controlador = new SesionMentoriaController();
        try {
            controlador.generarInformePDF();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_imprimirBtnActionPerformed

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
            java.util.logging.Logger.getLogger(sesionMentoriaManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sesionMentoriaManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sesionMentoriaManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sesionMentoriaManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sesionMentoriaManage().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editBtn;
    private javax.swing.JTextField estudianteTxt;
    private com.toedter.calendar.JDateChooser fechaPicker;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton filterBtn;
    private javax.swing.JButton imprimirBtn;
    private javax.swing.JButton insertBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mentorTxt;
    private javax.swing.JTable tablaSesiones;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables
}
