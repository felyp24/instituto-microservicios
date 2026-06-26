package com.instituto.cursoservice.dto;

public class Cursoresponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer creditos;
    private Integer vacantes;
    private Boolean estado;

    public Cursoresponse() {
    }

    public Cursoresponse(Long id, Boolean estado, Integer vacantes, Integer creditos, String descripcion, String nombre) {
        this.id = id;
        this.estado = estado;
        this.vacantes = vacantes;
        this.creditos = creditos;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getVacantes() {
        return vacantes;
    }

    public void setVacantes(Integer vacantes) {
        this.vacantes = vacantes;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
