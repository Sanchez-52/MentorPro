package com.mycompany.mentorpro.control;

import com.mycompany.mentorpro.dao.SesionMentoriaDAO;
import com.mycompany.mentorpro.dao.SesionMentoriaDAOImp;
import com.mycompany.mentorpro.model.SesionMentoria;
import com.mycompany.mentorpro.model.SesionMentoriaDetalle;
import java.util.Date;
import java.util.List;

public class SesionMentoriaController {

    private SesionMentoriaDAO sesionmentoriadao;

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public SesionMentoriaController(){
        sesionmentoriadao = new SesionMentoriaDAOImp();
    }

    public void insertarSesionMentoria(SesionMentoria sesion) {
        sesionmentoriadao.insertarSesionMentoria(sesion);
    }

    public void modificarSesionMentoria(SesionMentoria sesion) {
        sesionmentoriadao.modificarSesionMentoria(sesion);
    }

    public void eliminarSesionMentoria(Long id) {
        sesionmentoriadao.eliminarSesionMentoria(id);
    }

    public List<SesionMentoriaDetalle> getSesionesMentoria() {
        return sesionmentoriadao.getSesionesMentoria();
    }
    
    public List<SesionMentoriaDetalle> getSesionesMentoriaFiltrado(Date fecha, String nombreMentor, String nombreEstudiante) {
        return sesionmentoriadao.getSesionesMentoriaFiltrado(fecha, nombreMentor, nombreEstudiante);
    }
    
    public SesionMentoria getSesionMentoria(Long id){
        SesionMentoria sesion = sesionmentoriadao.getSesionMentoria(id);
        return sesion;
    }
    
    //Modulo para generar un informe en pdf
    public void generarInformePDF(){
        sesionmentoriadao.generarInformePDF();
    }
}
