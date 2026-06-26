package com.instituto.cursoservice.service;

import com.instituto.cursoservice.dto.Cursorequest;
import com.instituto.cursoservice.dto.Cursoresponse;

import java.util.List;

public interface CursoService {

    Cursoresponse registrarcurso(Cursorequest cursorequest);
    List<Cursoresponse> listarcursos();
    Cursoresponse buscarcursoporid(Long id);
    Cursoresponse actualizarcurso(Long id, Cursorequest cursorequest);
    void eliminarcurso(Long id);
}
