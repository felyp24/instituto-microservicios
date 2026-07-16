package com.instituto.docenteservice.mapper;

import com.instituto.docenteservice.dto.DocenteRequestDTO;
import com.instituto.docenteservice.dto.DocenteResponseDTO;
import com.instituto.docenteservice.entity.Docente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DocenteMapper {

    Docente toEntity(DocenteRequestDTO requestDTO);

    DocenteResponseDTO toResponseDTO(Docente docente);

    void actualizarEntidad(
            DocenteRequestDTO requestDTO,
            @MappingTarget Docente docente
    );
}