package com.instituto.cursoservice.mapper;


import com.instituto.cursoservice.dto.Cursorequest;
import com.instituto.cursoservice.dto.Cursoresponse;
import com.instituto.cursoservice.entity.Curso;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    Curso toentity(Cursorequest cursorequest);
    Cursoresponse toresponsedto(Curso curso);
    List<Cursoresponse> toresponselist(List<Curso> cursos);

}
