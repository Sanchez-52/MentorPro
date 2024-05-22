package com.mycompany.mentorpro.view;

import com.mycompany.mentorpro.control.EstudianteController;
import com.mycompany.mentorpro.control.MentorController;
import com.mycompany.mentorpro.control.SesionMentoriaController;
import com.mycompany.mentorpro.model.Estudiante;
import com.mycompany.mentorpro.model.Mentor;
import com.mycompany.mentorpro.model.SesionMentoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class sesionMentoriaInsert extends javax.swing.JFrame {

    private Long id = null;

    //Constructor por defecto
    public sesionMentoriaInsert() {
        iniciarHorasDisponibles();
        initComponents();
        cargarMentores();
        cargarEstudiantes();
        ButtonGroup modalidad = new ButtonGroup();
        modalidad.add(presencialRadio);
        modalidad.add(virtualRadio);
        presencialRadio.addActionListener(actionListener);
        virtualRadio.addActionListener(actionListener);
        eliminarBtn.setVisible(false);

    }

    //Función para pasar el codigo de sesion para editar
    public void setCodSesion(Long id) {
        this.id = id;
        tituloLabel.setText("Modificar Sesion de Mentoría");
        eliminarBtn.setVisible(true);
        llenarCampos();
    }

    //Inicio de funciones de selección de horas
    List<LocalTime> horasDisponibles = new ArrayList<>();

    private void iniciarHorasDisponibles() {
        for (int hora = 10; hora <= 21; hora++) {
            horasDisponibles.add(LocalTime.of(hora, 0));
        }
    }

    // Calcular las horas disponibles para la hora de fin
    private List<String> calcularHorasFinDisponibles(LocalTime horaInicio) {
        List<String> horasFin = new ArrayList<>();
        int index = horasDisponibles.indexOf(horaInicio) + 1;
        int count = 0;
        while (index < horasDisponibles.size() && count < 3) {
            horasFin.add(horasDisponibles.get(index).toString());
            index++;
            count++;
        }
        return horasFin;
    }

    // Actualizar las opciones del JComboBox de hora de fin
    private void actualizarComboBoxFin(List<String> horasFinDisponibles) {
        horaFinCombo.removeAllItems();
        for (String horaFin : horasFinDisponibles) {
            horaFinCombo.addItem(horaFin);
        }
        horaFinCombo.setEnabled(true);
    }
    //Fin de funciones de selección de horas

    //Funciones para cargar los Mentores y Estudiantes a los comboBox
    private void cargarMentores() {
        MentorController mentorController = new MentorController();
        List<Mentor> mentores = mentorController.getMentores();
        for (Mentor mentor : mentores) {
            String item = mentor.getNombre() + " "
                    + mentor.getApellido() + " - "
                    + mentor.getCodMentor().toString();
            mentorCombo.addItem(item);
        }
    }

    private void cargarEstudiantes() {
        EstudianteController estudianteController = new EstudianteController();
        List<Estudiante> estudiantes = estudianteController.getEstudiantes();
        for (Estudiante estudiante : estudiantes) {
            String item = estudiante.getNombre() + " "
                    + estudiante.getApellido() + " - "
                    + estudiante.getCodEstudiante().toString();
            estudianteCombo.addItem(item);
        }
    }

    private void llenarCampos() {
        SesionMentoriaController controlador = new SesionMentoriaController();
        SesionMentoria sesion = new SesionMentoria();

        sesion = controlador.getSesionMentoria(id);

        System.out.println("La sesion seleccionada fue " + id);

        //Llenamos el campo fecha
        fechaPicker.setDate(sesion.getFecha());

        //Llenamos el campos hora inicio y hora fin
        LocalTime horaInicio = sesion.getHora().toLocalTime();
        List<String> horasFinDisponibles = calcularHorasFinDisponibles(horaInicio);
        actualizarComboBoxFin(horasFinDisponibles);
        horaInicioCombo.setSelectedIndex(horaInicio.getHour() - 9);
        horaFinCombo.setSelectedIndex(sesion.getDuracion() - 1);

        //Llenamos el campo mentor
        for (int i = 1; i < mentorCombo.getItemCount(); i++) {
            String item = mentorCombo.getItemAt(i);
            item = item.split(" - ")[1];
            if (item.equals(sesion.getCodMentor().toString())) {
                mentorCombo.setSelectedIndex(i);
                break;
            }
        }

        //Llenamos el campo estudiante
        for (int i = 1; i < estudianteCombo.getItemCount(); i++) {
            String item = estudianteCombo.getItemAt(i);
            item = item.split(" - ")[1];
            if (!item.equals(sesion.getCodEstudiante().toString())) {
            } else {
                estudianteCombo.setSelectedIndex(i);
                break;
            }
        }

        //Llenamos el campo modalidad
        if (sesion.getTipoSesion().equals("V")) {
            virtualRadio.setSelected(true);
            presencialRadio.setSelected(false);
        } else {
            presencialRadio.setSelected(true);
            virtualRadio.setSelected(false);
        }

        //Llenamos el campo estado
        for (int i = 1; i < estadoCombo.getItemCount(); i++) {
            String item = estadoCombo.getItemAt(i);
            if (item.equals(sesion.getEstado())) {
                estadoCombo.setSelectedIndex(i);
                break;
            }
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
        tituloLabel = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        cancelarBtn = new javax.swing.JButton();
        agregarBtn = new javax.swing.JButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        fechaPicker = new com.toedter.calendar.JDateChooser();
        horaInicioCombo = new javax.swing.JComboBox<>();
        horaFinCombo = new javax.swing.JComboBox<>();
        mentorCombo = new javax.swing.JComboBox<>();
        estudianteCombo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        presencialRadio = new javax.swing.JRadioButton();
        virtualRadio = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        estadoCombo = new javax.swing.JComboBox<>();
        eliminarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(java.awt.Color.darkGray);
        jPanel1.setPreferredSize(new java.awt.Dimension(450, 500));

        tituloLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tituloLabel.setForeground(new java.awt.Color(204, 204, 204));
        tituloLabel.setText("Agregar Sesión de Mentoría");

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Fecha");

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Hora");

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Mentor");

        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Estudiante");

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        agregarBtn.setText("Agregar");
        agregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtnActionPerformed(evt);
            }
        });

        fechaPicker.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        horaInicioCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));
        horaInicioCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horaInicioComboActionPerformed(evt);
            }
        });

        horaFinCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir" }));
        horaFinCombo.setEnabled(false);

        mentorCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar..." }));
        mentorCombo.setMinimumSize(new java.awt.Dimension(291, 22));

        estudianteCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar..." }));
        estudianteCombo.setMinimumSize(new java.awt.Dimension(291, 22));

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Modalidad");

        presencialRadio.setForeground(new java.awt.Color(204, 204, 204));
        presencialRadio.setSelected(true);
        presencialRadio.setText("PRESENCIAL");

        virtualRadio.setForeground(new java.awt.Color(204, 204, 204));
        virtualRadio.setText("VIRTUAL");
        virtualRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                virtualRadioActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Estado");

        estadoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "PROGRAMADO", "COMPLETADO", "CANCELADO" }));
        estadoCombo.setMinimumSize(new java.awt.Dimension(291, 22));

        eliminarBtn.setText("Eliminar");
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tituloLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filler3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarBtn))
                    .addComponent(filler5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filler4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addGap(18, 20, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(estadoCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(fechaPicker, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(horaInicioCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(horaFinCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(mentorCombo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(estudianteCombo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(presencialRadio)
                                .addGap(18, 18, 18)
                                .addComponent(virtualRadio)))))
                .addGap(40, 40, 40))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(cancelarBtn)
                .addGap(18, 18, 18)
                .addComponent(agregarBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tituloLabel)
                        .addComponent(eliminarBtn)))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(horaInicioCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(horaFinCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(fechaPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mentorCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudianteCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(presencialRadio)
                    .addComponent(virtualRadio)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estadoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(49, 49, 49)
                .addComponent(filler5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarBtn)
                    .addComponent(agregarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filler4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void horaInicioComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horaInicioComboActionPerformed
        // TODO add your handling code here:
        String horaInicioSeleccionada = horaInicioCombo.getSelectedItem().toString();
        if (!horaInicioSeleccionada.equals("Elegir")) {
            // Determinar las horas disponibles para la hora de fin
            List<String> horasFinDisponibles = calcularHorasFinDisponibles(LocalTime.parse(horaInicioSeleccionada));
            // Actualizar las opciones del JComboBox de hora de fin
            actualizarComboBoxFin(horasFinDisponibles);
        } else {
            horaFinCombo.removeAllItems();
            horaFinCombo.setEnabled(false);
            horaFinCombo.addItem("Elegir");
        }
    }//GEN-LAST:event_horaInicioComboActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        sesionMentoriaManage sesionMentoriaManageFrame = new sesionMentoriaManage();
        sesionMentoriaManageFrame.setVisible(true);
        sesionMentoriaManageFrame.setLocationRelativeTo(null);
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        // TODO add your handling code here:
        Date fecha = null;
        boolean fechaCompleta = false;
        Time horaInicio = null;
        boolean horaInicioCompleta = false;
        Time horaFin = null;
        boolean horaFinCompleta = false;
        Long codMentor = null;
        boolean codMentorCompleto = false;
        Long codEstudiante = null;
        boolean codEstudianteCompleto = false;
        String tipoSesion = null;
        int duracion = 0;
        boolean duracionCompleta = false;
        String estado = null;
        boolean estadoCompleto = false;

        boolean executed = false;

        //Llenado de variable fecha
        try {
            fecha = fechaPicker.getDate();
            fechaCompleta = true;
        } catch (Exception e) {
            e.printStackTrace();
            fechaCompleta = false;
        }

        // Llenado de variable horaInicio
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String horaSeleccionada = (String) horaInicioCombo.getSelectedItem();
        if (!horaSeleccionada.equals("Elegir")) {
            try {
                // Parsear el String a un objeto Date
                Date date = dateFormat.parse(horaSeleccionada);

                // Convertir el objeto Date a un objeto Time
                Time inicio = new Time(date.getTime());

                // Usar horaInicio como un objeto Time
                horaInicio = inicio;
                horaInicioCompleta = true;
            } catch (ParseException e) {
                e.printStackTrace();
                horaInicioCompleta = false;
            }
        } else {
            horaInicioCompleta = false;
        }

        //Llenado de variable horaFin
        horaSeleccionada = (String) horaFinCombo.getSelectedItem();
        if (!horaSeleccionada.equals("Elegir")) {
            try {
                // Parsear el String a un objeto Date
                Date date = dateFormat.parse(horaSeleccionada);

                // Convertir el objeto Date a un objeto Time
                Time fin = new Time(date.getTime());

                // Usar horaInicio como un objeto Time
                horaFin = fin;
                horaFinCompleta = true;
            } catch (ParseException e) {
                e.printStackTrace();
                horaFinCompleta = false;
            }
        } else {
            horaFinCompleta = false;
        }

        //Llenado de variable codMentor
        String temp = (String) mentorCombo.getSelectedItem();
        if (!temp.equals("Seleccionar...")) {
            String mentor = temp;
            mentor = mentor.replaceAll("[^0-9]", "");
            codMentor = Long.parseLong(mentor);
            codEstudianteCompleto = true;
        } else {
            codEstudianteCompleto = false;
        }

        //Llenado de variable codEstudiante
        String temp2 = (String) estudianteCombo.getSelectedItem();
        if (!temp2.equals("Seleccionar...")) {
            String estudiante = temp2;
            estudiante = estudiante.replaceAll("[^0-9]", "");
            codEstudiante = Long.parseLong(estudiante);
            codMentorCompleto = true;
        } else {
            codMentorCompleto = false;
        }

        //Llenado de variable tipoSesion
        if (presencialRadio.isSelected()) {
            tipoSesion = "P";
        } else {
            tipoSesion = "V";
        }

        //Llenado de variable duracion
        try {
            // Calcular la diferencia en milisegundos
            long diferenciaMilisegundos = horaFin.getTime() - horaInicio.getTime();

            // Convertir la diferencia de milisegundos a horas
            duracion = (int) (diferenciaMilisegundos / (1000 * 60 * 60));
            duracionCompleta = true;
        } catch (Exception e) {
            duracionCompleta = false;
        }

        //Lenado de variable estado
        estado = estadoCombo.getSelectedItem().toString();
        if (estado.equals("Seleccionar...")) {
            estadoCompleto = false;
        } else {
            estadoCompleto = true;
        }

        boolean camposCompletos = fechaCompleta && horaInicioCompleta
                && horaFinCompleta && codEstudianteCompleto
                && codMentorCompleto && duracionCompleta && estadoCompleto;

        if (!camposCompletos) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            //Inicializamos el controlador
            SesionMentoriaController controller = new SesionMentoriaController();

            //Llenamos la entidad SesionMentoria
            SesionMentoria sesion = new SesionMentoria();
            sesion.setId(id);
            sesion.setCodMentor(codMentor);
            sesion.setCodEstudiante(codEstudiante);
            sesion.setHora(horaInicio);
            sesion.setFecha(fecha);
            sesion.setTipoSesion(tipoSesion);
            sesion.setDuracion(duracion);
            sesion.setEstado(estado);

            if (id == null) {
                //Realiza el insert
                try {
                    controller.insertarSesionMentoria(sesion);
                    executed = true;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Hubo un error al insertar los datos, inténtelo más tarde", "Error", JOptionPane.ERROR);
                    executed = false;
                }
            } else {
                //Realiza la modificacion
                try {
                    controller.modificarSesionMentoria(sesion);
                    executed = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Hubo un error al modificar los datos, inténtelo más tarde", "Error", JOptionPane.ERROR_MESSAGE);
                    executed = false;
                }
            }
        }
        if (executed) {
            JOptionPane.showMessageDialog(null, "El registro de datos ha sido exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            sesionMentoriaManage adminFrame = new sesionMentoriaManage();
            adminFrame.setVisible(true);
            adminFrame.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_agregarBtnActionPerformed

    // Crear ActionListener para los JRadioButton
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton selectedButton = (JRadioButton) e.getSource();
            if (selectedButton == presencialRadio) {
                virtualRadio.setSelected(false);
            } else if (selectedButton == virtualRadio) {
                presencialRadio.setSelected(false);
            }
        }
    };

    private void virtualRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_virtualRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_virtualRadioActionPerformed

    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed
        // TODO add your handling code here:

        // Mostrar una alerta de confirmación
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar esta sesión?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        // Verificar la opción seleccionada por el usuario
        if (opcion == JOptionPane.YES_OPTION) {
            // Si el usuario selecciona "Sí" (YES_OPTION), procede con la eliminación
            //Inicializamos el controlador
            SesionMentoriaController controller = new SesionMentoriaController();
            controller.eliminarSesionMentoria(id);

            this.setVisible(false);
            sesionMentoriaManage sesionMentoriaManageView = new sesionMentoriaManage();
            sesionMentoriaManageView.setVisible(true);
            sesionMentoriaManageView.setLocationRelativeTo(null);
        } else {
            // Si el usuario selecciona "No" o cierra el diálogo (NO_OPTION o CANCEL_OPTION), cancela la eliminación
            // Aquí puedes agregar cualquier código adicional necesario
        }
    }//GEN-LAST:event_eliminarBtnActionPerformed

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
            java.util.logging.Logger.getLogger(sesionMentoriaInsert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sesionMentoriaInsert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sesionMentoriaInsert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sesionMentoriaInsert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sesionMentoriaInsert().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JComboBox<String> estadoCombo;
    private javax.swing.JComboBox<String> estudianteCombo;
    private com.toedter.calendar.JDateChooser fechaPicker;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.JComboBox<String> horaFinCombo;
    private javax.swing.JComboBox<String> horaInicioCombo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> mentorCombo;
    private javax.swing.JRadioButton presencialRadio;
    private javax.swing.JLabel tituloLabel;
    private javax.swing.JRadioButton virtualRadio;
    // End of variables declaration//GEN-END:variables
}
