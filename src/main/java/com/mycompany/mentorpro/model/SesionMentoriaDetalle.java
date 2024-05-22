package com.mycompany.mentorpro.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sesionMentoriaDetalle")
public class SesionMentoriaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombrementor")
    private String nombreMentor;
    @Column(name = "nombreestudiante")
    private String nombreEstudiante;
    @Column(name = "tiposesion")
    private String tipoSesion;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "hora")
    private Time hora;
    @Column(name = "Duracion")
    private int duracion;
    @Column(name = "estado")
    private String estado;

    public SesionMentoriaDetalle() {
    }

    public SesionMentoriaDetalle(Long id, String nombreMentor, String nombreEstudiante, String tipoSesion, Date fecha, Time hora, int duracion, String estado) {
        this.id = id;
        this.nombreMentor = nombreMentor;
        this.nombreEstudiante = nombreEstudiante;
        this.tipoSesion = tipoSesion;
        this.fecha = fecha;
        this.hora = hora;
        this.duracion = duracion;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreMentor() {
        return nombreMentor;
    }

    public void setNombreMentor(String nombreMentor) {
        this.nombreMentor = nombreMentor;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getTipoSesion() {
        return tipoSesion;
    }

    public void setTipoSesion(String tipoSesion) {
        this.tipoSesion = tipoSesion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "SesionMentoriaDetalle{" + "id=" + id + ", nombreMentor=" + nombreMentor + ", nombreEstudiante=" + nombreEstudiante + ", tipoSesion=" + tipoSesion + ", fecha=" + fecha + ", hora=" + hora + ", duracion=" + duracion + ", estado=" + estado + '}';
    }
}
