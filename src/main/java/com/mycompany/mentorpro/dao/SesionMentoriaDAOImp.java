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
import com.mycompany.mentorpro.model.SesionMentoria;
import com.mycompany.mentorpro.model.SesionMentoriaDetalle;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        SesionMentoria sesion = em.find(SesionMentoria.class, sesionMentoria.getId());
        sesion.setCodMentor(sesionMentoria.getCodMentor());
        sesion.setCodEstudiante(sesionMentoria.getCodEstudiante());
        sesion.setFecha(sesionMentoria.getFecha());
        sesion.setHora(sesionMentoria.getHora());
        sesion.setDuracion(sesionMentoria.getDuracion());
        sesion.setEstado(sesionMentoria.getEstado());
        sesion.setTipoSesion(sesionMentoria.getTipoSesion());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void eliminarSesionMentoria(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        SesionMentoria sesion = em.find(SesionMentoria.class, id);
        sesion.setIsDeleted(true);
        em.getTransaction().commit();
        em.close();
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
        EntityManager em = emf.createEntityManager();
        SesionMentoria sesion = new SesionMentoria();
        
        try {
            sesion = em.find(SesionMentoria.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return sesion;
    }

    //Módulo para generar informe PDF
    @Override
    public void generarInformePDF() {
        //Llenado de la lista de estudiantes
        EntityManager em = emf.createEntityManager();
        List<SesionMentoriaDetalle> sesiones = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<SesionMentoriaDetalle> cq = cb.createQuery(SesionMentoriaDetalle.class);
            Root<SesionMentoriaDetalle> root = cq.from(SesionMentoriaDetalle.class);

            // Agregar orden ascendente por el código de estudiante
            cq.orderBy(cb.asc(root.get("fecha")));

            // Ejecutar la consulta y obtener la lista de estudiantes
            sesiones = em.createQuery(cq).getResultList();
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
        String filePath = "reports/sesiones_" + fechaFormateada + ".pdf";

        PdfDocument pdfDoc = null;
        try {
            pdfDoc = new PdfDocument(new PdfWriter(new FileOutputStream(filePath)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SesionMentoriaDAOImp.class.getName()).log(Level.SEVERE, null, ex);
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
            Paragraph date = new Paragraph("Lista de sesiones | Fecha: " + fechaFormateada2).setFont(font).setFontSize(12).setTextAlignment(TextAlignment.CENTER);
            doc.add(date);

            // Agrega una tabla al documento para mostrar los datos de los estudiantes
            float[] columnWidths = {0.05f, 0.2f, 0.2f, 0.15f, 0.1f, 0.1f, 0.05f, 0.2f};
            Table table = new Table(UnitValue.createPercentArray(columnWidths)); // 8 columnas para los datos del estudiante
            table.setFontSize(8f);

            // Agrega encabezados de columna a la tabla
            table.addCell(new Cell().add(new Paragraph("")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Mentor")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Estudiante")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Modalidad")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Fecha")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Hora")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Duracion")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Estado")).setTextAlignment(TextAlignment.CENTER));

            // Agrega filas de datos de estudiantes a la tabla
            int cont = 1;
            for (SesionMentoriaDetalle sesion : sesiones) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(cont))).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(sesion.getNombreMentor().toString())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(sesion.getNombreEstudiante())).setTextAlignment(TextAlignment.CENTER));
                String modalidad = sesion.getTipoSesion().equals("V") ? "Virtual" : "Presencial";
                table.addCell(new Cell().add(new Paragraph(modalidad)).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(sesion.getFecha().toString())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(sesion.getHora().toString())).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(sesion.getDuracion()))).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(sesion.getEstado())).setTextAlignment(TextAlignment.CENTER));
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
