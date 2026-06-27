package com.instituto.matriculaservice.client;

import com.instituto.matriculaservice.dto.CursoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "curso-service", url = "${services.curso.url}")
public interface CursoClient {

    @GetMapping("/{id}")
    CursoResponseDTO buscarCursoPorId(@PathVariable("id") Long id);
}