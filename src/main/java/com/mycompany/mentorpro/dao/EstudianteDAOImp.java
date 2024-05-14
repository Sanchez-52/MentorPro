package com.mycompany.mentorpro.dao;

import com.mycompany.mentorpro.model.Estudiante;
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

public class EstudianteDAOImp implements EstudianteDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

    public static void main(String[] args) {
        EstudianteDAO estudiantedao = new EstudianteDAOImp();
        Estudiante estudiante = new Estudiante();
        estudiante.setCodEstudiante(11L);
        estudiante.setNombre("Sebastian");
        estudiante.setApellido("Sanchez");
        estudiante.setDni("74297337");
        estudiante.setDireccion("Gutierrez 746");
        estudiante.setCorreo("jsebas02san@gmail.com");
        estudiante.setPasswordHash("8ec67a3b88a530b88e5c5a57537d7df88e733516f850e18f9f9bf749ff6a118e");

        List<Estudiante> estudiantes = estudiantedao.getEstudiantes();
        for (Estudiante v : estudiantes) {
            System.out.println(v.toString());
        }
    }

    @Override
    public void insertarEstudiante(Estudiante estudiante) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void modificarEstudiante(Estudiante estudiante) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Estudiante e = em.find(Estudiante.class, estudiante.getCodEstudiante());
        e.setNombre(estudiante.getNombre());
        e.setApellido(estudiante.getApellido());
        e.setDni(estudiante.getDni());
        e.setCorreo(estudiante.getCorreo());
        e.setDireccion(estudiante.getDireccion());
        e.setPasswordHash(estudiante.getPasswordHash());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void eliminarEstudiante(Long codEstudiante) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Estudiante e = em.find(Estudiante.class, codEstudiante);
        e.setIsDeleted(true);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Estudiante getEstudiante(Long codEstudiante) {
        EntityManager em = emf.createEntityManager();
        List<Estudiante> estudiantes = (List<Estudiante>) em.createQuery("From Estudiante").getResultList();
        return estudiantes.getFirst();
    }
    
    @Override
    public List<Estudiante> getEstudiantes() {
        EntityManager em = emf.createEntityManager();
        List<Estudiante> estudiantes = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Estudiante> cq = cb.createQuery(Estudiante.class);
            Root<Estudiante> root = cq.from(Estudiante.class);

            // Agregar un predicado para filtrar los estudiantes con isDeleted = false
            Predicate isNotDeleted = cb.equal(root.get("isDeleted"), false);
            cq.where(isNotDeleted);

            // Agregar orden ascendente por el código de estudiante
            cq.orderBy(cb.asc(root.get("codEstudiante")));

            // Ejecutar la consulta y obtener la lista de estudiantes
            estudiantes = em.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return estudiantes;
    }

    @Override
    public List<Estudiante> getEstudiantesFiltrados(String dni, String nombre, String apellido) {
        EntityManager em = emf.createEntityManager();
        List<Estudiante> estudiantes = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Estudiante> cq = cb.createQuery(Estudiante.class);
            Root<Estudiante> root = cq.from(Estudiante.class);
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

            // Agregar un predicado para filtrar los estudiantes con isDeleted = false
            predicates.add(cb.equal(root.get("isDeleted"), false));

            // Construir la consulta con los predicados
            cq.where(predicates.toArray(new Predicate[0]));

            // Agregar orden ascendente por el código de estudiante
            cq.orderBy(cb.asc(root.get("codEstudiante")));

            // Ejecutar la consulta y obtener la lista de estudiantes filtrados
            estudiantes = em.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return estudiantes;
    } 

    @Override
    public boolean existeEstudiantePorDni(String dni) {
        EntityManager em = emf.createEntityManager();
        try {
            // Consulta en la base de datos para verificar la existencia del estudiante con el DNI proporcionado
            Query query = em.createQuery("SELECT COUNT(e) FROM Estudiante e WHERE e.dni = :dni");
            query.setParameter("dni", dni);
            Long count = (Long) query.getSingleResult();
            return count > 0; // Si count es mayor que 0, significa que se encontró al menos un estudiante con el DNI proporcionado
        } finally {
            em.close();
        }
    }
}
