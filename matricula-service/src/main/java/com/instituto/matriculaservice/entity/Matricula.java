package com.instituto.matriculaservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "matriculas")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long alumnoId;
    private Long cursoId;
    private LocalDateTime fechaMatricula;
    private Boolean estado;

    public Matricula() {
    }

    public Matricula(Long id, Long alumnoId, Long cursoId, LocalDateTime fechaMatricula, Boolean estado) {
        this.id = id;
        this.alumnoId = alumnoId;
        this.cursoId = cursoId;
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public LocalDateTime getFechaMatricula() {
        return fechaMatricula;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public void setFechaMatricula(LocalDateTime fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}