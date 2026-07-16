package com.instituto.cursoservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private Integer creditos;

    private Integer vacantes;

    @Column(nullable = false)
    private Boolean estado = true;

    @Column
    private Long docenteId;

    public Curso() {
    }

    public Curso(
            Long id,
            String nombre,
            String descripcion,
            Integer creditos,
            Integer vacantes,
            Boolean estado,
            Long docenteId
    ) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.vacantes = vacantes;
        this.estado = estado;
        this.docenteId = docenteId;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public Integer getVacantes() {
        return vacantes;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Long getDocenteId() {
        return docenteId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public void setVacantes(Integer vacantes) {
        this.vacantes = vacantes;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
    }
}