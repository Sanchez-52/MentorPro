package com.mycompany.mentorpro.dao;

import com.mycompany.mentorpro.model.Mentor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MentorDAOImp implements MentorDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
    
    public static void main(String[] args) {
        MentorDAO mentordao = new MentorDAOImp();
        Mentor mentor = new Mentor();
        mentor.setCodMentor(11L);
        mentor.setNombre("Alexa");
        mentor.setApellido("Maldonado");
        mentor.setDni("48651297");
        mentor.setDireccion("Gutierrez 746");
        mentor.setCorreo("falexamaldomo@gmail.com");
        mentor.setPasswordHash("6747ddb267aabd98cad8b15b28d62db5489a3112f855f7dc0aafa23936510461");
        
        List<Mentor> mentores = mentordao.getMentores();
        for(Mentor v:mentores){
            System.out.println(v.toString());
        }
    }
    
    @Override
    public void insertarMentor(Mentor mentor){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(mentor);
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public void modificarMentor(Mentor mentor){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Mentor m = em.find(Mentor.class, mentor.getCodMentor());
        m.setNombre(mentor.getNombre());
        m.setApellido(mentor.getApellido());
        m.setDni(mentor.getDni());
        m.setCorreo(mentor.getCorreo());
        m.setDireccion(mentor.getDireccion());
        m.setPasswordHash(mentor.getPasswordHash());
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public void eliminarMentor(Long codMentor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Mentor m = em.find(Mentor.class, codMentor);
        m.setIsDeleted(true);
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public Mentor getMentor(Long codMentor){
        EntityManager em = emf.createEntityManager();
        List<Mentor> mentores = (List<Mentor>) em.createQuery("From Mentor").getResultList();        
        return mentores.getFirst();
    }

    @Override
    public List<Mentor> getMentores() {
        EntityManager em = emf.createEntityManager();
        List<Mentor> mentores = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Mentor> cq = cb.createQuery(Mentor.class);
            Root<Mentor> root = cq.from(Mentor.class);

            // Agregar un predicado para filtrar los estudiantes con isDeleted = false
            Predicate isNotDeleted = cb.equal(root.get("isDeleted"), false);
            cq.where(isNotDeleted);

            // Agregar orden ascendente por el código de mentor
            cq.orderBy(cb.asc(root.get("codMentor")));

            // Ejecutar la consulta y obtener la lista de mentores
            mentores = em.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return mentores;
    }
    
    @Override
    public List<Mentor> getMentoresFiltrados(String dni, String nombre, String apellido){
        EntityManager em = emf.createEntityManager();
        List<Mentor> mentores = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Mentor> cq = cb.createQuery(Mentor.class);
            Root<Mentor> root = cq.from(Mentor.class);
            List<Predicate> predicates = new ArrayList<>();

            // Filtrar por DNI
            if (dni != null && !dni.isEmpty()) {
                predicates.add(cb.like(root.get("dni"), "%" + dni + "%"));
            }

            // Filtrar por nombre
            if (nombre != null && !nombre.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%"));
            }

            // Filtrar por apellido
            if (apellido != null && !apellido.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("apellido")), "%" + apellido.toLowerCase() + "%"));
            }

            // Agregar un predicado para filtrar los mentores con isDeleted = false
            predicates.add(cb.equal(root.get("isDeleted"), false));

            // Construir la consulta con los predicados
            cq.where(predicates.toArray(new Predicate[0]));

            // Agregar orden ascendente por el código de mentor
            cq.orderBy(cb.asc(root.get("codMentor")));

            // Ejecutar la consulta y obtener la lista de mentores filtrados
            mentores = em.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return mentores;
    }
    
    @Override
    public boolean existeMentorPorDni(String dni){
        EntityManager em = emf.createEntityManager();
        try {
            // Consulta en la base de datos para verificar la existencia del mentor con el DNI proporcionado
            Query query = em.createQuery("SELECT COUNT(e) FROM Mentor e WHERE e.dni = :dni");
            query.setParameter("dni", dni);
            Long count = (Long) query.getSingleResult();
            return count > 0; // Si count es mayor que 0, significa que se encontró al menos un mentor con el DNI proporcionado
        } finally {
            em.close();
        }
    }
}
