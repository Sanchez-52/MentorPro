package com.mycompany.mentorpro.dao;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.mycompany.mentorpro.model.Estudiante;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    //Modulo para generar un informe en pdf
    @Override
    public void generarInformePDF() {
        //Llenado de la lista de estudiantes
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

        //CREACIÓN DEL ARCHIVO PDF
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Formatear la fecha como "yyyyMMdd"
        String fechaFormateada = fechaActual.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        
        //Formatear la fecha como "dd/MM/yyyy"
        String fechaFormateada2 = fechaActual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Construir la ruta del archivo con la fecha
        String filePath = "reports/reporte_" + fechaFormateada + ".pdf";

        PdfDocument pdfDoc = null;
        try {
            pdfDoc = new PdfDocument(new PdfWriter(new FileOutputStream(filePath)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EstudianteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        Document doc = new Document(pdfDoc);

        // Declarar la fuente (se debe manejar IOException)
        PdfFont font = null;
        try {
            font = PdfFontFactory.createFont();
        } catch (IOException e) {
            // Manejar la excepción
            System.err.println("Error al crear la fuente: " + e.getMessage());
        }

        if (font != null) {
            // Agregar el encabezado
            Paragraph header = new Paragraph("MentorPro").setFont(font).setBold().setFontSize(20).setTextAlignment(TextAlignment.CENTER);
            doc.add(header);

            // Agregar la fecha
            Paragraph date = new Paragraph("Lista de estudiantes | Fecha: " + fechaFormateada2).setFont(font).setFontSize(12).setTextAlignment(TextAlignment.CENTER);
            doc.add(date);

            // Agrega una tabla al documento para mostrar los datos de los estudiantes
            float[] columnWidths = {0.05f, 0.1f, 0.15f, 0.2f, 0.2f, 0.3f, 0.3f};
            Table table = new Table(UnitValue.createPercentArray(columnWidths)); // 6 columnas para los datos del estudiante
            table.setFontSize(10f);

            // Agrega encabezados de columna a la tabla
            table.addCell(new Cell().add(new Paragraph("")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Código")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("DNI")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Nombre")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Apellido")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Dirección")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Correo")).setTextAlignment(TextAlignment.CENTER));

            // Agrega filas de datos de estudiantes a la tabla
            int cont = 1;
            for (Estudiante estudiante : estudiantes) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(cont))).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(estudiante.getCodEstudiante().toString())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(estudiante.getDni())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(estudiante.getNombre())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(estudiante.getApellido())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(estudiante.getDireccion())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(estudiante.getCorreo())).setTextAlignment(TextAlignment.CENTER));
                cont++;
            }

            // Agrega la tabla al documento
            doc.add(table);
        }
        
        // Intenta abrir el archivo PDF
        try {
            File archivoPDF = new File(filePath);
            if (archivoPDF.exists()) {
                Desktop.getDesktop().open(archivoPDF);
            } else {
                System.out.println("El archivo PDF no existe en la ruta especificada.");
            }
        } catch (IOException e) {
            System.out.println("Error al intentar abrir el archivo PDF: " + e.getMessage());
        }

        // Cierra el documento
        doc.close();
    }
}
