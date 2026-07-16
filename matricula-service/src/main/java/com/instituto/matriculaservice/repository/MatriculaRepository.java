package com.instituto.matriculaservice.repository;

import com.instituto.matriculaservice.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository
        extends JpaRepository<Matricula, Long> {

    List<Matricula> findByAlumnoId(Long alumnoId);

    List<Matricula> findByCursoId(Long cursoId);

    boolean existsByAlumnoIdAndCursoIdAndEstadoTrue(
            Long alumnoId,
            Long cursoId
    );
}