package com.instituto.cursoservice.repository;

import com.instituto.cursoservice.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cursorepository  extends JpaRepository<Curso,Long> {
}
