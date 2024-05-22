package com.mycompany.mentorpro.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ViewUtils {
    public static void setWindowIcon(JFrame frame) {
        // Cargar la imagen del logo
        ImageIcon imgIcon = new ImageIcon(frame.getClass().getResource("/images/mentorpro.png"));
        
        // Establecer el icono de la ventana
        frame.setIconImage(imgIcon.getImage());
    }
}
