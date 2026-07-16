package com.instituto.cursoservice.client;

import com.instituto.cursoservice.dto.DocenteResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "docente-service",
        url = "${services.docente.url}"
)
public interface DocenteClient {

    @GetMapping("/{id}")
    DocenteResponseDTO buscarDocentePorId(
            @PathVariable("id") Long id
    );
}