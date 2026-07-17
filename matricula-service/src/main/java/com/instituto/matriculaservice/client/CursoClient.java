package com.instituto.matriculaservice.client;

import com.instituto.matriculaservice.dto.CursoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(
        name = "curso-service",
        path = "/api/cursos"
)
public interface CursoClient {

    @GetMapping("/{id}")
    CursoResponseDTO buscarCursoPorId(
            @PathVariable("id") Long id
    );

    @PutMapping("/{id}/descontar-vacante")
    CursoResponseDTO descontarVacante(
            @PathVariable("id") Long id
    );

    @PutMapping("/{id}/devolver-vacante")
    CursoResponseDTO devolverVacante(
            @PathVariable("id") Long id
    );
}