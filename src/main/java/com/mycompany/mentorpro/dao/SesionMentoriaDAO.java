package com.mycompany.mentorpro.dao;

import com.mycompany.mentorpro.model.SesionMentoria;
import java.util.Date;
import java.util.List;

public interface SesionMentoriaDAO {
    public void insertarSesionMentoria(SesionMentoria sesionMentoria);
    public void modificarSesionMentoria(SesionMentoria sesionMentoria);
    public void eliminarSesionMentoria(Long id);
    public List<SesionMentoria> getSesionesMentoria();
    public List<SesionMentoria> getSesionesMentoriaFiltrado(Date fecha, String nombreMentor, String nombreEstudiante);
    public SesionMentoria getSesionMentoria(Long id);
    
    //Módulo para generar informe PDF
    public void generarInformePDF();
}
