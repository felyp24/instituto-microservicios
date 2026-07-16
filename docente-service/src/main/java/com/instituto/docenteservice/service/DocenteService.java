package com.instituto.docenteservice.service;

import com.instituto.docenteservice.dto.DocenteRequestDTO;
import com.instituto.docenteservice.dto.DocenteResponseDTO;

public interface DocenteService {

    DocenteResponseDTO registrar(DocenteRequestDTO requestDTO);

    Iterable<DocenteResponseDTO> listar();

    DocenteResponseDTO buscarPorId(Long id);

    DocenteResponseDTO actualizar(
            Long id,
            DocenteRequestDTO requestDTO
    );

    void desactivar(Long id);
}