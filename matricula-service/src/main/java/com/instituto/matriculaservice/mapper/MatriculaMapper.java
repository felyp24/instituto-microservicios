package com.instituto.matriculaservice.mapper;

import com.instituto.matriculaservice.dto.MatriculaRequestDTO;
import com.instituto.matriculaservice.dto.MatriculaResponseDTO;
import com.instituto.matriculaservice.entity.Matricula;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatriculaMapper {

    Matricula toEntity(MatriculaRequestDTO matriculaRequestDTO);

    MatriculaResponseDTO toResponseDTO(Matricula matricula);

    List<MatriculaResponseDTO> toResponseDTOList(List<Matricula> matriculas);
}