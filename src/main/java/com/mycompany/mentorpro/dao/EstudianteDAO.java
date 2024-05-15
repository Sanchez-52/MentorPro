package com.mycompany.mentorpro.dao;

import com.mycompany.mentorpro.model.Estudiante;
import java.util.List;

public interface EstudianteDAO {
    public void insertarEstudiante(Estudiante estudiante);
    public void modificarEstudiante(Estudiante estudiante);
    public void eliminarEstudiante(Long codEstudiante);
    public List<Estudiante> getEstudiantes();
    public List<Estudiante> getEstudiantesFiltrados(String dni, String nombre, String apellido);
    public Estudiante getEstudiante(Long codEstudiante);
    public boolean existeEstudiantePorDni(String dni);
    
    //Modulo para generar un informe en pdf
    public void generarInformePDF();
}
