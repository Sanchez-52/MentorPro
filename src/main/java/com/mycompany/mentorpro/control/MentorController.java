package com.mycompany.mentorpro.control;

import com.mycompany.mentorpro.dao.MentorDAO;
import com.mycompany.mentorpro.dao.MentorDAOImp;
import com.mycompany.mentorpro.model.Mentor;
import java.util.List;

public class MentorController {
    private MentorDAO mentordao;

    public static void main(String[] args) {
        MentorController controller = new MentorController();
        Mentor mentor = new Mentor();
        mentor.setCodMentor(11L);
        mentor.setNombre("Alexa");
        mentor.setApellido("Maldonado");
        mentor.setDni("48651297");
        mentor.setDireccion("Gutierrez 746");
        mentor.setCorreo("falexamaldomo@gmail.com");
        mentor.setPasswordHash("6747ddb267aabd98cad8b15b28d62db5489a3112f855f7dc0aafa23936510461");
        controller.insertarMentor(mentor);        
    }
    
    public void insertarMentor(Mentor mentor){
        mentordao.insertarMentor(mentor);
    }
    
    public void modificarMentor(Mentor mentor){
        mentordao.modificarMentor(mentor);
    }
    
    public void eliminarMentor(Long codMentor){
        mentordao.eliminarMentor(codMentor);
    }
    
    public List<Mentor> getMentores(){
        return mentordao.getMentores();
    }
    
    public List<Mentor> getMentoresFiltrados(String dni, String nombre, String apellido){
        return mentordao.getMentoresFiltrados(dni, nombre, apellido);
    }
    
    public MentorController(){
        mentordao = new MentorDAOImp();
    }
    
    public Mentor getMentor(Long codMentor){
        Mentor mentor = mentordao.getMentor(codMentor);
        return mentor;
    }
    
    public boolean existeMentorPorDni(String dni){
        boolean existe = mentordao.existeMentorPorDni(dni);
        return existe;
    }
    
    //Modulo para generar un informe en pdf
    public void generarInformePDF(){
        mentordao.generarInformePDF();
    }
}
