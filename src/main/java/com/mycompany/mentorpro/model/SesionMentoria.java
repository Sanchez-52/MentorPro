package com.mycompany.mentorpro.model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sesionmentoria")
public class SesionMentoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name="codmentor")
    @Column(name = "codmentor")
    private Long codMentor;
    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name="codestudiante")
    @Column(name = "codestudiante")
    private Long codEstudiante;
    @Column(name = "tiposesion")
    private String tipoSesion;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "hora")
    private LocalTime hora;
    @Column(name = "duracion")
    private int duracion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "isdeleted")
    private boolean isDeleted;

    public SesionMentoria() {
    }

    public SesionMentoria(Long id, Long codMentor, Long codEstudiante, String tipoSesion, LocalDate fecha, LocalTime hora, int duracion, String estado) {
        this.id = id;
        this.codMentor = codMentor;
        this.codEstudiante = codEstudiante;
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

    public Long getCodMentor() {
        return codMentor;
    }

    public void setCodMentor(Long codMentor) {
        this.codMentor = codMentor;
    }

    public Long getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(Long codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public String getTipoSesion() {
        return tipoSesion;
    }

    public void setTipoSesion(String tipoSesion) {
        this.tipoSesion = tipoSesion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
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

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "SesionMentoria{" + "id=" + id + ", codMentor=" + codMentor + ", codEstudiante=" + codEstudiante + ", tipoSesion=" + tipoSesion + ", fecha=" + fecha + ", hora=" + hora + ", duracion=" + duracion + ", estado=" + estado + '}';
    }
}
