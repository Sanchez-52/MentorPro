package com.mycompany.mentorpro.dao;

import com.mycompany.mentorpro.model.SesionMentoria;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SesionMentoriaDAOImp implements SesionMentoriaDAO {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

    public static void main(String[] args) {

    }

    @Override
    public void insertarSesionMentoria(SesionMentoria sesionMentoria) {

    }

    @Override
    public void modificarSesionMentoria(SesionMentoria sesionMentoria) {

    }

    @Override
    public void eliminarSesionMentoria(Long id) {

    }

    @Override
    public List<SesionMentoria> getSesionesMentoria() {
        EntityManager em = emf.createEntityManager();
        List<SesionMentoria> sesionesMentoria = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<SesionMentoria> cq = cb.createQuery(SesionMentoria.class);
            Root<SesionMentoria> root = cq.from(SesionMentoria.class);

            // Agregar un predicado para filtrar las sesiones con isDeleted = false
            Predicate isNotDeleted = cb.equal(root.get("isDeleted"), false);
            cq.where(isNotDeleted);

            // Agregar orden ascendente por la fecha
            cq.orderBy(cb.asc(root.get("fecha")));

            // Ejecutar la consulta y obtener la lista de estudiantes
            sesionesMentoria = em.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return sesionesMentoria;
    }
    
    @Override
    public List<SesionMentoria> getSesionesMentoriaFiltrado(Date fecha, String nombreMentor, String nombreEstudiante){
        List<SesionMentoria> sesionesMentoria = null;
        return sesionesMentoria;
    }
            
    @Override
    public SesionMentoria getSesionMentoria(Long id) {
        SesionMentoria sesionMentoria = null;
        return sesionMentoria;
    }

    //Módulo para generar informe PDF
    @Override
    public void generarInformePDF(){
        
    }

}
