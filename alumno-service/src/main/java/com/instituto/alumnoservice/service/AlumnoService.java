package com.instituto.alumnoservice.service;

import com.instituto.alumnoservice.dto.AlumnoRequest;
import com.instituto.alumnoservice.dto.AlumnoResponse;

import java.util.List;

public interface AlumnoService {

    AlumnoResponse registraralumno(AlumnoRequest alumnoRequest);
    List<AlumnoResponse> listaralumnos();
    AlumnoResponse buscaralumnoporid(Long id);
    AlumnoResponse actualizaralumno(Long id, AlumnoRequest alumnoRequest);
    void eliminaralumno(Long id);

}
