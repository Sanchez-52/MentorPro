package com.mycompany.mentorpro.dao;

import com.mycompany.mentorpro.model.Mentor;
import java.util.List;

public interface MentorDAO {
    public void insertarMentor(Mentor mentor);
    public void modificarMentor(Mentor mentor);
    public void eliminarMentor(Long codMentor);
    public List<Mentor> getMentores();
    public List<Mentor> getMentoresFiltrados(String dni, String nombre, String apellido);
    public Mentor getMentor(Long codMentor);
    public boolean existeMentorPorDni(String dni);
}
