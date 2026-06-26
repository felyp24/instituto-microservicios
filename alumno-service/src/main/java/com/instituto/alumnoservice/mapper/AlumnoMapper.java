package com.instituto.alumnoservice.mapper;


import com.instituto.alumnoservice.dto.AlumnoRequest;
import com.instituto.alumnoservice.dto.AlumnoResponse;
import com.instituto.alumnoservice.entity.Alumno;
import com.instituto.alumnoservice.repository.AlumnoRepository;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlumnoMapper {

    Alumno toEntity(AlumnoRequest alumnoRequest);
    AlumnoResponse toresponsedto(Alumno alumno);
    List<AlumnoResponse> toresponsedtolist(List<Alumno> alumnos);
}
