package com.instituto.cursoservice.dto;
import java.time.LocalTime;

public class Cursorequest {

    private String nombre;
    private String descripcion;
    private Integer creditos;
    private Integer vacantes;
    private Long docenteId;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String aula;
    private String periodo;

    public Cursorequest() {
    }

    public Cursorequest(String nombre, String descripcion, Integer creditos, Integer vacantes, Long docenteId, String diaSemana, LocalTime horaInicio, LocalTime horaFin, String aula, String periodo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.vacantes = vacantes;
        this.docenteId = docenteId;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.aula = aula;
        this.periodo = periodo;
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
