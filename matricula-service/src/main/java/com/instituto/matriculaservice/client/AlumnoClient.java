package com.instituto.matriculaservice.client;

import com.instituto.matriculaservice.dto.AlumnoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "alumno-service", url = "${services.alumno.url}")
public interface AlumnoClient {

    @GetMapping("/{id}")
    AlumnoResponseDTO buscarAlumnoPorId(@PathVariable("id") Long id);
}