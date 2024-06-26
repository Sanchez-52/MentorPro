/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mentorpro.view;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author 51933
 */
public class MainFrame extends javax.swing.JFrame {

    String pass = "60fe74406e7f353ed979f350f2fbb6a2e8690a5fa7d1b0c32983d1d8b3f95f67";

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        ViewUtils.setWindowIcon(this);
        initComponents();
        setLocationRelativeTo(null);
        estudianteBtn.setEnabled(false);
        sesionesBtn.setEnabled(false);
        mentorBtn.setEnabled(false);
        pedirContra();
    }

    private void pedirContra() {
        // Crear un campo de contraseña
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
            "Ingrese su contraseña:", passwordField
        };

        boolean logged = false;

        while (!logged) {
            // Mostrar el cuadro de diálogo
            int option = JOptionPane.showConfirmDialog(null, message, "Contraseña", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Manejar la respuesta del usuario
            if (option == JOptionPane.OK_OPTION) {
                char[] password = passwordField.getPassword();
                String passwordStr = new String(password);

                passwordStr = hash(passwordStr);

                if (!passwordStr.equals(pass)) {
                    JOptionPane.showMessageDialog(null, "La contraseña es incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
                    // Limpiar la contraseña después de usarla por seguridad
                    passwordField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Bienvenido", "Ingreso exitoso", JOptionPane.INFORMATION_MESSAGE);
                    logged = true;
                    habilitarBotones();
                }

            } else {
                int opcion = JOptionPane.showConfirmDialog(null, "Está por salir de la aplicación.\n¿Desea continuar?", "Salir", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }
    }

    private void habilitarBotones() {
        estudianteBtn.setEnabled(true);
        sesionesBtn.setEnabled(true);
        mentorBtn.setEnabled(true);
    }

    /*
        FUNCION SHA-256
     */
    public static String hash(String input) {
        try {
            // Obtener una instancia de MessageDigest con el algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Aplicar el digest al input
            byte[] hash = digest.digest(input.getBytes());

            // Convertir el hash de bytes a una representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Manejar una excepción en caso de que el algoritmo no esté disponible
            e.printStackTrace();
            return null;
        }
    }

    private void realizarDatabaseDump() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione la ubicación");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File directoryToSave = fileChooser.getSelectedFile();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
            String formattedDate = dateFormat.format(new Date());
            String filePath = directoryToSave.getAbsolutePath() + File.separator + "mentorpro_" + formattedDate +".dump";
            executePgDump(filePath);
        }
    }

    private void executePgDump(String filePath) {
        String host = "localhost"; // Cambia esto según sea necesario
        String port = "5432"; // Cambia esto según sea necesario
        String database = "mentorpro"; // Cambia esto según sea necesario
        String user = "postgres"; // Cambia esto según sea necesario
        String password = "admin"; // Cambia esto según sea necesario

        // Comando para ejecutar pg_dump
        String[] command = {
            "pg_dump",
            "--host", host,
            "--port", port,
            "--username", user,
            "--format", "custom",
            "--file", filePath,
            database
        };

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.environment().put("PGPASSWORD", password);

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                JOptionPane.showMessageDialog(this, "Exportación de base de datos exitosa.");
            } else {
                JOptionPane.showMessageDialog(this, "Fallo al exportar la base de datos. Exit code: " + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Un error ocurrió al exportar la base de datos.");
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
        estudianteBtn = new javax.swing.JButton();
        mentorBtn = new javax.swing.JButton();
        sesionesBtn = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(150, 0), new java.awt.Dimension(150, 0), new java.awt.Dimension(150, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(150, 0), new java.awt.Dimension(150, 0), new java.awt.Dimension(150, 32767));
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(854, 480));
        setResizable(false);

        jPanel1.setBackground(java.awt.Color.darkGray);
        jPanel1.setPreferredSize(new java.awt.Dimension(850, 480));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MentorPRO");

        estudianteBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        estudianteBtn.setText("Estudiante");
        estudianteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        estudianteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                estudianteBtnMouseClicked(evt);
            }
        });
        estudianteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estudianteBtnActionPerformed(evt);
            }
        });

        mentorBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mentorBtn.setText("Mentor");
        mentorBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mentorBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mentorBtnMouseClicked(evt);
            }
        });

        sesionesBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sesionesBtn.setText("Sesiones");
        sesionesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sesionesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sesionesBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Elija qué gestionar");

        jButton1.setText("Realizar Backup");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(estudianteBtn)
                .addGap(89, 89, 89)
                .addComponent(sesionesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(mentorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(60, 60, 60))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(64, 64, 64)
                .addComponent(jLabel2)
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(estudianteBtn)
                        .addComponent(mentorBtn)
                        .addComponent(sesionesBtn))
                    .addComponent(filler2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void estudianteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estudianteBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estudianteBtnActionPerformed

    private void estudianteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_estudianteBtnMouseClicked
        this.setVisible(false);
        estudiantesManage estudiantesFrame = new estudiantesManage();
        estudiantesFrame.setVisible(true);
        estudiantesFrame.setLocationRelativeTo(null);
    }//GEN-LAST:event_estudianteBtnMouseClicked

    private void mentorBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mentorBtnMouseClicked
        this.setVisible(false);
        mentoresManage mentoresFrame = new mentoresManage();
        mentoresFrame.setVisible(true);
        mentoresFrame.setLocationRelativeTo(null);
    }//GEN-LAST:event_mentorBtnMouseClicked

    private void sesionesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sesionesBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        sesionMentoriaManage sesionMentoriaFrame = new sesionMentoriaManage();
        sesionMentoriaFrame.setVisible(true);
        sesionMentoriaFrame.setLocationRelativeTo(null);
    }//GEN-LAST:event_sesionesBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        realizarDatabaseDump();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton estudianteBtn;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton mentorBtn;
    private javax.swing.JButton sesionesBtn;
    // End of variables declaration//GEN-END:variables
}
