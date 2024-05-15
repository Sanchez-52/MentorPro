/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testing;

import com.mycompany.mentorpro.control.EstudianteController;
import com.mycompany.mentorpro.model.Estudiante;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author 51933
 */
public class testing {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
    
    public static void main(String[] args) {
        // TODO code application logic here
        EstudianteController estudianteController = new EstudianteController();
        /*
        List<Estudiante> estudiantes = estudianteController.getEstudiantes();
        for(Estudiante v:estudiantes){
            System.out.println(v.getCodEstudiante() + ", " + v.getNombre() + ", " + v.getApellido());
        }
        */
        
        estudianteController.generarInformePDF();
    }
    
}
