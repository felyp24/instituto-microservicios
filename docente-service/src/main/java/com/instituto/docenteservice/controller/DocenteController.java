package com.instituto.docenteservice.controller;

import com.instituto.docenteservice.dto.DocenteRequestDTO;
import com.instituto.docenteservice.dto.DocenteResponseDTO;
import com.instituto.docenteservice.service.DocenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/docentes")
@Tag(
        name = "Docentes",
        description = "Operaciones para gestionar docentes del instituto"
)
public class DocenteController {

    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo docente")
    public ResponseEntity<DocenteResponseDTO> registrar(
            @RequestBody DocenteRequestDTO requestDTO
    ) {
        DocenteResponseDTO docente =
                docenteService.registrar(requestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(docente);
    }

    @GetMapping
    @Operation(summary = "Listar los docentes activos")
    public ResponseEntity<Iterable<DocenteResponseDTO>> listar() {
        return ResponseEntity.ok(docenteService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un docente por su ID")
    public ResponseEntity<DocenteResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                docenteService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los datos de un docente")
    public ResponseEntity<DocenteResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody DocenteRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(
                docenteService.actualizar(id, requestDTO)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desactivar un docente")
    public ResponseEntity<Void> desactivar(
            @PathVariable Long id
    ) {
        docenteService.desactivar(id);

        return ResponseEntity.noContent().build();
    }
}