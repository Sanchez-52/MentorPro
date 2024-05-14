package com.mycompany.mentorpro.control;

import com.mycompany.mentorpro.dao.EstudianteDAO;
import com.mycompany.mentorpro.dao.EstudianteDAOImp;
import com.mycompany.mentorpro.model.Estudiante;
import java.util.List;

public class EstudianteController {
    private EstudianteDAO estudiantedao;

    public static void main(String[] args) {
        EstudianteController controller = new EstudianteController();
        Estudiante estudiante = new Estudiante();
        estudiante.setCodEstudiante(11L);
        estudiante.setNombre("Sebastian");
        estudiante.setApellido("Sanchez");
        estudiante.setDni("74297337");
        estudiante.setDireccion("Gutierrez 746");
        estudiante.setCorreo("jsebas02san@gmail.com");
        estudiante.setPasswordHash("8ec67a3b88a530b88e5c5a57537d7df88e733516f850e18f9f9bf749ff6a118e");
        controller.insertarEstudiante(estudiante);
    }
    
    public void insertarEstudiante(Estudiante estudiante){
        estudiantedao.insertarEstudiante(estudiante);
    }
    
    public void modificarEstudiante(Estudiante estudiante){
        estudiantedao.modificarEstudiante(estudiante);
    }
    
    public void eliminarEstudiante(Long codEstudiante){
        estudiantedao.eliminarEstudiante(codEstudiante);
    }
    
    public List<Estudiante> getEstudiantes(){
        return estudiantedao.getEstudiantes();
    }
    
    public List<Estudiante> getEstudiantesFiltrados(String dni, String nombre, String apellido){
        return estudiantedao.getEstudiantesFiltrados(dni, nombre, apellido);
    }
    
    public EstudianteController(){
        estudiantedao = new EstudianteDAOImp();
    }
    
    public Estudiante getEstudiante(Long codEstudiante){
        Estudiante estudiante = estudiantedao.getEstudiante(codEstudiante);
        return estudiante;
    }
    
    public boolean existeEstudiantePorDni(String dni){
        boolean existe = estudiantedao.existeEstudiantePorDni(dni);
        return existe;
    }
}
