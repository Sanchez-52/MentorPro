package com.mycompany.mentorpro.view;

import com.mycompany.mentorpro.control.EstudianteController;
import com.mycompany.mentorpro.model.Estudiante;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class estudiantesManage extends javax.swing.JFrame {

    private EstudianteController estudianteController;

    /**
     * Creates new form managingLayout
     */
    public estudiantesManage() {
        ViewUtils.setWindowIcon(this);
        initComponents();

        /*
            Función para que el JTextField de DNI solo acepte
            caracteres numericos y que no exceda los 8 digitos
         */
        ((AbstractDocument) dniTxt.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string == null) {
                    return;
                }

                StringBuilder sb = new StringBuilder(dniTxt.getText());
                sb.insert(offset, string);
                if (sb.toString().matches("\\d{0,8}")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text == null) {
                    return;
                }

                StringBuilder sb = new StringBuilder(dniTxt.getText());
                sb.replace(offset, offset + length, text);
                if (sb.toString().matches("\\d{0,8}")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        // Inicializa el controlador
        estudianteController = new EstudianteController();

        // Llena la tabla de estudiantes al iniciar la vista
        llenarTablaEstudiantes();
    }

    //Función para llenar la tablaEstudiantes
    private void llenarTablaEstudiantes() {
        // Obtén la lista de estudiantes desde el controlador
        List<Estudiante> estudiantes = estudianteController.getEstudiantes();

        // Crea un modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Codigo");
        model.addColumn("DNI");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Direccion");
        model.addColumn("Correo");

        // Agrega filas a partir de la lista de estudiantes
        for (Estudiante estudiante : estudiantes) {
            model.addRow(new Object[]{estudiante.getCodEstudiante(), estudiante.getDni(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getDireccion(), estudiante.getCorreo()});
        }

        // Establece el modelo en la tabla
        tablaEstudiantes.setModel(model);

        // Configura las celdas de la tabla como no editables
        for (int i = 0; i < tablaEstudiantes.getColumnCount(); i++) {
            TableColumn column = tablaEstudiantes.getColumnModel().getColumn(i);
            column.setCellEditor(null); // Deshabilita la edición de celdas
        }

    }

    private void llenarTablaEstudiantesFiltrados() {
        // Obtén los valores de los campos de búsqueda
        String dni = dniTxt.getText();
        String nombre = nombreTxt.getText();
        String apellido = apellidoTxt.getText();

        // Obtén la lista de estudiantes filtrada desde el controlador
        List<Estudiante> estudiantesFiltrados = estudianteController.getEstudiantesFiltrados(dni, nombre, apellido);

        // Crea un modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Codigo");
        model.addColumn("DNI");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Direccion");
        model.addColumn("Correo");

        // Agrega filas a partir de la lista de estudiantes filtrados
        for (Estudiante estudiante : estudiantesFiltrados) {
            model.addRow(new Object[]{estudiante.getCodEstudiante(), estudiante.getDni(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getDireccion(), estudiante.getCorreo()});
        }

        // Establece el modelo en la tabla
        tablaEstudiantes.setModel(model);

        // Configura las celdas de la tabla como no editables
        for (int i = 0; i < tablaEstudiantes.getColumnCount(); i++) {
            TableColumn column = tablaEstudiantes.getColumnModel().getColumn(i);
            column.setCellEditor(null); // Deshabilita la edición de celdas
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
        tablaEstudiantes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        nombreTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        apellidoTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        dniTxt = new javax.swing.JTextField();
        filterBtn = new javax.swing.JButton();
        insertBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        imprimirBtn = new javax.swing.JButton();
        volverBtn = new javax.swing.JButton();

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
        jLabel2.setText("Estudiantes");

        tablaEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "DNI", "Nombre", "Apellido", "Direccion", "Correo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEstudiantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEstudiantesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaEstudiantes);
        if (tablaEstudiantes.getColumnModel().getColumnCount() > 0) {
            tablaEstudiantes.getColumnModel().getColumn(0).setResizable(false);
            tablaEstudiantes.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaEstudiantes.getColumnModel().getColumn(1).setResizable(false);
            tablaEstudiantes.getColumnModel().getColumn(1).setPreferredWidth(100);
            tablaEstudiantes.getColumnModel().getColumn(2).setResizable(false);
            tablaEstudiantes.getColumnModel().getColumn(2).setPreferredWidth(100);
            tablaEstudiantes.getColumnModel().getColumn(3).setResizable(false);
            tablaEstudiantes.getColumnModel().getColumn(3).setPreferredWidth(100);
            tablaEstudiantes.getColumnModel().getColumn(4).setResizable(false);
            tablaEstudiantes.getColumnModel().getColumn(4).setPreferredWidth(100);
            tablaEstudiantes.getColumnModel().getColumn(5).setResizable(false);
            tablaEstudiantes.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Nombre");

        nombreTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreTxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreTxtFocusLost(evt);
            }
        });
        nombreTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nombreTxtMouseClicked(evt);
            }
        });
        nombreTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreTxtActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Apellido");

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("DNI");

        filterBtn.setText("Filtrar");
        filterBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        filterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterBtnActionPerformed(evt);
            }
        });

        insertBtn.setText("Agregar");
        insertBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        insertBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertBtnActionPerformed(evt);
            }
        });

        editBtn.setText("Editar");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(imprimirBtn)
                                .addGap(431, 431, 431))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dniTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(filterBtn)
                                .addGap(18, 18, 18)
                                .addComponent(editBtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(insertBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(volverBtn, javax.swing.GroupLayout.Alignment.TRAILING))))
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(dniTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filterBtn)
                            .addComponent(insertBtn)
                            .addComponent(editBtn))
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

    private void nombreTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreTxtActionPerformed

    private void filterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterBtnActionPerformed
        // TODO add your handling code here:
        llenarTablaEstudiantesFiltrados();
    }//GEN-LAST:event_filterBtnActionPerformed

    private void insertBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        estudianteInsert agregarFrame = new estudianteInsert();
        agregarFrame.setVisible(true);
        agregarFrame.setLocationRelativeTo(null); 
    }//GEN-LAST:event_insertBtnActionPerformed

    private void tablaEstudiantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEstudiantesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaEstudiantesMouseClicked

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        // Verifica si hay una fila seleccionada en la tabla
        if (tablaEstudiantes.getSelectedRow() == -1) {
            // Si no hay ninguna fila seleccionada, muestra una notificación o alerta al usuario
            JOptionPane.showMessageDialog(estudiantesManage.this, "Por favor, seleccione una fila antes de editar.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Si hay una fila seleccionada, obtén los datos de la fila seleccionada y abre la pantalla de edición
            int filaSeleccionada = tablaEstudiantes.getSelectedRow();
            Long codEstudiante = (Long) tablaEstudiantes.getValueAt(filaSeleccionada, 0);
            String dni = (String) tablaEstudiantes.getValueAt(filaSeleccionada, 1);
            String nombre = (String) tablaEstudiantes.getValueAt(filaSeleccionada, 2);
            String apellido = (String) tablaEstudiantes.getValueAt(filaSeleccionada, 3);
            String direccion = (String) tablaEstudiantes.getValueAt(filaSeleccionada, 4);
            String correo = (String) tablaEstudiantes.getValueAt(filaSeleccionada, 5);

            //Pasar datos obtenidos de la tabla a una entidad
            Estudiante stud = new Estudiante();
            stud.setCodEstudiante(codEstudiante);
            stud.setDni(dni);
            stud.setNombre(nombre);
            stud.setApellido(apellido);
            stud.setDireccion(direccion);
            stud.setCorreo(correo);

            estudianteInsert pantallaEdicion = new estudianteInsert();
            pantallaEdicion.setEstudiante(stud);
            setVisible(false);
            pantallaEdicion.setVisible(true);
            pantallaEdicion.setLocationRelativeTo(null); 
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void nombreTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombreTxtMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreTxtMouseClicked

    private void nombreTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreTxtFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreTxtFocusGained

    private void nombreTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreTxtFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreTxtFocusLost

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }//GEN-LAST:event_volverBtnActionPerformed

    private void imprimirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirBtnActionPerformed
        // TODO add your handling code here:
        //Inicializar controlador
        EstudianteController controller = new EstudianteController();
        controller.generarInformePDF();
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
            java.util.logging.Logger.getLogger(estudiantesManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(estudiantesManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(estudiantesManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(estudiantesManage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new estudiantesManage().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoTxt;
    private javax.swing.JTextField dniTxt;
    private javax.swing.JButton editBtn;
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
    private javax.swing.JTextField nombreTxt;
    private javax.swing.JTable tablaEstudiantes;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables
}
