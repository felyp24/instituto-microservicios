package com.instituto.matriculaservice.dto;

public class CursoResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Integer creditos;
    private Integer vacantes;
    private Boolean estado;

    public CursoResponseDTO() {
    }

    public CursoResponseDTO(Long id, String nombre, String descripcion, Integer creditos, Integer vacantes, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.vacantes = vacantes;
        this.estado = estado;
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
}