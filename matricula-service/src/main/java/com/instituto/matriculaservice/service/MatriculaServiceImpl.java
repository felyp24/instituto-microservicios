package com.instituto.matriculaservice.service;

import com.instituto.matriculaservice.client.AlumnoClient;
import com.instituto.matriculaservice.client.CursoClient;
import com.instituto.matriculaservice.dto.AlumnoResponseDTO;
import com.instituto.matriculaservice.dto.CursoResponseDTO;
import com.instituto.matriculaservice.dto.MatriculaRequestDTO;
import com.instituto.matriculaservice.dto.MatriculaResponseDTO;
import com.instituto.matriculaservice.entity.Matricula;
import com.instituto.matriculaservice.mapper.MatriculaMapper;
import com.instituto.matriculaservice.repository.MatriculaRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final MatriculaMapper matriculaMapper;
    private final AlumnoClient alumnoClient;
    private final CursoClient cursoClient;

    public MatriculaServiceImpl(
            MatriculaRepository matriculaRepository,
            MatriculaMapper matriculaMapper,
            AlumnoClient alumnoClient,
            CursoClient cursoClient) {

        this.matriculaRepository = matriculaRepository;
        this.matriculaMapper = matriculaMapper;
        this.alumnoClient = alumnoClient;
        this.cursoClient = cursoClient;
    }

    @Override
    public MatriculaResponseDTO registrarMatricula(MatriculaRequestDTO matriculaRequestDTO) {

        AlumnoResponseDTO alumno = obtenerAlumno(matriculaRequestDTO.getAlumnoId());
        CursoResponseDTO curso = obtenerCurso(matriculaRequestDTO.getCursoId());

        if (Boolean.FALSE.equals(alumno.getEstado())) {
            throw new RuntimeException("No se puede matricular. El alumno está inactivo.");
        }

        if (Boolean.FALSE.equals(curso.getEstado())) {
            throw new RuntimeException("No se puede matricular. El curso está inactivo.");
        }

        Matricula matricula = matriculaMapper.toEntity(matriculaRequestDTO);
        matricula.setFechaMatricula(LocalDateTime.now());
        matricula.setEstado(true);

        Matricula matriculaGuardada = matriculaRepository.save(matricula);

        return matriculaMapper.toResponseDTO(matriculaGuardada);
    }

    @Override
    public List<MatriculaResponseDTO> listarMatriculas() {
        List<Matricula> matriculas = matriculaRepository.findAll();
        return matriculaMapper.toResponseDTOList(matriculas);
    }

    @Override
    public MatriculaResponseDTO buscarMatriculaPorId(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + id));

        return matriculaMapper.toResponseDTO(matricula);
    }

    @Override
    public List<MatriculaResponseDTO> listarMatriculasPorAlumno(Long alumnoId) {
        List<Matricula> matriculas = matriculaRepository.findByAlumnoId(alumnoId);
        return matriculaMapper.toResponseDTOList(matriculas);
    }

    @Override
    public List<MatriculaResponseDTO> listarMatriculasPorCurso(Long cursoId) {
        List<Matricula> matriculas = matriculaRepository.findByCursoId(cursoId);
        return matriculaMapper.toResponseDTOList(matriculas);
    }

    @Override
    public void eliminarMatricula(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + id));

        matricula.setEstado(false);
        matriculaRepository.save(matricula);
    }

    private AlumnoResponseDTO obtenerAlumno(Long alumnoId) {
        try {
            return alumnoClient.buscarAlumnoPorId(alumnoId);
        } catch (FeignException e) {
            throw new RuntimeException("No se puede matricular. El alumno no existe o alumno-service no está disponible.");
        }
    }

    private CursoResponseDTO obtenerCurso(Long cursoId) {
        try {
            return cursoClient.buscarCursoPorId(cursoId);
        } catch (FeignException e) {
            throw new RuntimeException("No se puede matricular. El curso no existe o curso-service no está disponible.");
        }
    }
}