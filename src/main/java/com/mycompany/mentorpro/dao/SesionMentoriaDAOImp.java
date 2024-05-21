package com.mycompany.mentorpro.dao;

import com.mycompany.mentorpro.model.SesionMentoria;
import com.mycompany.mentorpro.model.SesionMentoriaDetalle;
import java.util.ArrayList;
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
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(sesionMentoria);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void modificarSesionMentoria(SesionMentoria sesionMentoria) {

    }

    @Override
    public void eliminarSesionMentoria(Long id) {

    }

    @Override
    public List<SesionMentoriaDetalle> getSesionesMentoria() {
        EntityManager em = emf.createEntityManager();
        List<SesionMentoriaDetalle> sesionesMentoria = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<SesionMentoriaDetalle> cq = cb.createQuery(SesionMentoriaDetalle.class);
            Root<SesionMentoriaDetalle> root = cq.from(SesionMentoriaDetalle.class);

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
    public List<SesionMentoriaDetalle> getSesionesMentoriaFiltrado(Date fecha, String nombreMentor, String nombreEstudiante) {
        EntityManager em = emf.createEntityManager();
        List<SesionMentoriaDetalle> sesionesMentoria = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<SesionMentoriaDetalle> cq = cb.createQuery(SesionMentoriaDetalle.class);
            Root<SesionMentoriaDetalle> root = cq.from(SesionMentoriaDetalle.class);

            // Agregar criterios de filtrado
            List<Predicate> predicates = new ArrayList<>();

            // Filtrar por fecha si se proporciona una fecha
            if (fecha != null) {
                predicates.add(cb.equal(root.get("fecha"), fecha));
            }

            // Filtrar por nombre de mentor si se proporciona un nombre de mentor
            if (nombreMentor != null && !nombreMentor.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("nombreMentor")), "%" + nombreMentor.toLowerCase() + "%"));
            }

            // Filtrar por nombre de estudiante si se proporciona un nombre de estudiante
            if (nombreEstudiante != null && !nombreEstudiante.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("nombreEstudiante")), "%" + nombreEstudiante.toLowerCase() + "%"));
            }

            // Agregar todos los predicados al criterio de consulta
            cq.where(predicates.toArray(new Predicate[0]));

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
    public SesionMentoria getSesionMentoria(Long id) {
        SesionMentoria sesionMentoria = null;
        return sesionMentoria;
    }

    //Módulo para generar informe PDF
    @Override
    public void generarInformePDF() {

    }

}
