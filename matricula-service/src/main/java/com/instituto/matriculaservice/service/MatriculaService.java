package com.instituto.matriculaservice.service;

import com.instituto.matriculaservice.dto.MatriculaRequestDTO;
import com.instituto.matriculaservice.dto.MatriculaResponseDTO;

import java.util.List;

public interface MatriculaService {

    MatriculaResponseDTO registrarMatricula(MatriculaRequestDTO matriculaRequestDTO);

    List<MatriculaResponseDTO> listarMatriculas();

    MatriculaResponseDTO buscarMatriculaPorId(Long id);

    List<MatriculaResponseDTO> listarMatriculasPorAlumno(Long alumnoId);

    List<MatriculaResponseDTO> listarMatriculasPorCurso(Long cursoId);

    void eliminarMatricula(Long id);
}