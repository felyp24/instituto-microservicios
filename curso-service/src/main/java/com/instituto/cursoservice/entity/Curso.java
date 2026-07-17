package com.instituto.cursoservice.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @Column
    private String diaSemana;

    @Column
    private LocalTime horaInicio;

    @Column
    private LocalTime horaFin;

    @Column
    private Double horasDuracion;

    @Column
    private String aula;

    @Column
    private String periodo;

    @Column
    private LocalDate fecha;

    public Curso() {
    }

    public Curso(Long id, String nombre, String descripcion, Integer creditos, Boolean estado, Integer vacantes, Long docenteId, String diaSemana, LocalTime horaInicio, LocalTime horaFin, Double horasDuracion, String aula, String periodo, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.estado = estado;
        this.vacantes = vacantes;
        this.docenteId = docenteId;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.horasDuracion = horasDuracion;
        this.aula = aula;
        this.periodo = periodo;
        this.fecha = fecha;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}