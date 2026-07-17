package com.instituto.cursoservice.repository;

import com.instituto.cursoservice.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface Cursorepository extends JpaRepository<Curso, Long> {

    List<Curso> findByFechaAndEstadoTrue(LocalDate fecha);
}