package com.instituto.cursoservice.dto;
import java.time.LocalTime;

public class Cursoresponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer creditos;
    private Integer vacantes;
    private Boolean estado;
    private Long docenteId;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Double horasDuracion;
    private String aula;
    private String periodo;

    public Cursoresponse() {
    }

    public Cursoresponse(Long id, String nombre, String descripcion, Integer creditos, Integer vacantes, Boolean estado, Long docenteId, String diaSemana, LocalTime horaInicio, LocalTime horaFin, Double horasDuracion, String aula, String periodo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.vacantes = vacantes;
        this.estado = estado;
        this.docenteId = docenteId;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.horasDuracion = horasDuracion;
        this.aula = aula;
        this.periodo = periodo;
    }

    public Long getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
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

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Double getHorasDuracion() {
        return horasDuracion;
    }

    public void setHorasDuracion(Double horasDuracion) {
        this.horasDuracion = horasDuracion;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
