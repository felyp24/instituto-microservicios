package com.instituto.matriculaservice.dto;

public class MatriculaRequestDTO {

    private Long alumnoId;
    private Long cursoId;

    public MatriculaRequestDTO() {
    }

    public MatriculaRequestDTO(Long alumnoId, Long cursoId) {
        this.alumnoId = alumnoId;
        this.cursoId = cursoId;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}