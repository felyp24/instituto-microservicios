package com.instituto.cursoservice.dto;

public class Cursorequest {

    private String nombre;
    private String descripcion;
    private Integer creditos;
    private Integer vacantes;
    private Long docenteId;

    public Cursorequest() {
    }

    public Cursorequest(Long docenteId, Integer vacantes, Integer creditos, String descripcion, String nombre) {
        this.docenteId = docenteId;
        this.vacantes = vacantes;
        this.creditos = creditos;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public Long getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getVacantes() {
        return vacantes;
    }

    public void setVacantes(Integer vacantes) {
        this.vacantes = vacantes;
    }
}
