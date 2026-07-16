package com.instituto.docenteservice.repository;

import com.instituto.docenteservice.entity.Docente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepository extends CrudRepository<Docente, Long> {

    boolean existsByDni(String dni);

    boolean existsByCorreo(String correo);

    boolean existsByDniAndIdNot(String dni, Long id);

    boolean existsByCorreoAndIdNot(String correo, Long id);

    Iterable<Docente> findByEstadoTrue();
}