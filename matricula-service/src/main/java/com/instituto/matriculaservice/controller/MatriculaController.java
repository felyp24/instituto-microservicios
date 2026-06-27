package com.instituto.matriculaservice.controller;

import com.instituto.matriculaservice.dto.MatriculaRequestDTO;
import com.instituto.matriculaservice.dto.MatriculaResponseDTO;
import com.instituto.matriculaservice.service.MatriculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> registrarMatricula(@RequestBody MatriculaRequestDTO matriculaRequestDTO) {
        MatriculaResponseDTO matriculaRegistrada = matriculaService.registrarMatricula(matriculaRequestDTO);
        return new ResponseEntity<>(matriculaRegistrada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MatriculaResponseDTO>> listarMatriculas() {
        List<MatriculaResponseDTO> matriculas = matriculaService.listarMatriculas();
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaResponseDTO> buscarMatriculaPorId(@PathVariable Long id) {
        MatriculaResponseDTO matricula = matriculaService.buscarMatriculaPorId(id);
        return ResponseEntity.ok(matricula);
    }

    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<List<MatriculaResponseDTO>> listarMatriculasPorAlumno(@PathVariable Long alumnoId) {
        List<MatriculaResponseDTO> matriculas = matriculaService.listarMatriculasPorAlumno(alumnoId);
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<MatriculaResponseDTO>> listarMatriculasPorCurso(@PathVariable Long cursoId) {
        List<MatriculaResponseDTO> matriculas = matriculaService.listarMatriculasPorCurso(cursoId);
        return ResponseEntity.ok(matriculas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMatricula(@PathVariable Long id) {
        matriculaService.eliminarMatricula(id);
        return ResponseEntity.noContent().build();
    }
}