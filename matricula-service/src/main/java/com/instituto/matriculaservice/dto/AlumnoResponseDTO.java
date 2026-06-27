package com.instituto.matriculaservice.dto;

public class AlumnoResponseDTO {

    private Long id;
    private String dni;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private Boolean estado;

    public AlumnoResponseDTO() {
    }

    public AlumnoResponseDTO(Long id, String dni, String nombres, String apellidos, String correo, String telefono, Boolean estado) {
        this.id = id;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}