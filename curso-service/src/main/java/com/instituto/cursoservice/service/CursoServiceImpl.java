package com.instituto.cursoservice.service;


import com.instituto.cursoservice.dto.Cursorequest;
import com.instituto.cursoservice.dto.Cursoresponse;
import com.instituto.cursoservice.entity.Curso;
import com.instituto.cursoservice.mapper.CursoMapper;
import com.instituto.cursoservice.repository.Cursorepository;
import org.springframework.stereotype.Service;
import com.instituto.cursoservice.client.DocenteClient;
import com.instituto.cursoservice.dto.DocenteResponseDTO;
import feign.FeignException;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService{

    private final Cursorepository cursorepository;
    private final CursoMapper cursoMapper;
    private final DocenteClient docenteClient;


    public CursoServiceImpl(
            Cursorepository cursoRepository,
            CursoMapper cursoMapper,
            DocenteClient docenteClient
    ) {
        this.cursorepository = cursoRepository;
        this.cursoMapper = cursoMapper;
        this.docenteClient = docenteClient;
    }

    @Override
    public Cursoresponse registrarcurso(Cursorequest cursorequest) {
        Curso curso = cursoMapper.toentity(cursorequest);
        curso.setEstado(true);
        Curso cursoguardado = cursorepository.save(curso);
        return cursoMapper.toresponsedto(cursoguardado);
    }

    @Override
    public List<Cursoresponse> listarcursos() {
        List<Curso> cursos = cursorepository.findAll();
        return cursoMapper.toresponselist(cursos);
    }

    @Override
    public Cursoresponse buscarcursoporid(Long id) {
        Curso curso = cursorepository.findById(id).orElseThrow(()-> new RuntimeException("Curso no encontrado"));
        return cursoMapper.toresponsedto(curso);
    }

    @Override
    public Cursoresponse actualizarcurso(Long id, Cursorequest cursorequest) {
        Curso curso = cursorepository.findById(id).orElseThrow(()-> new RuntimeException("Curso no encontrado"));
        curso.setNombre(cursorequest.getNombre());
        curso.setDescripcion(cursorequest.getDescripcion());
        curso.setCreditos(cursorequest.getCreditos());
        curso.setVacantes(cursorequest.getVacantes());
        Curso cursoactualizado = cursorepository.save(curso);
        return cursoMapper.toresponsedto(cursoactualizado);
    }

    @Override
    public void eliminarcurso(Long id) {
        Curso curso = cursorepository.findById(id).orElseThrow(()->new RuntimeException("Curso no encontrado"));
        curso.setEstado(false);
        cursorepository.save(curso);

    }

    private DocenteResponseDTO validarDocente(Long docenteId) {

        if (docenteId == null) {
            throw new RuntimeException(
                    "Debe seleccionar un docente para el curso"
            );
        }

        try {
            DocenteResponseDTO docente =
                    docenteClient.buscarDocentePorId(docenteId);

            if (docente == null) {
                throw new RuntimeException(
                        "No se encontró el docente con ID: " + docenteId
                );
            }

            if (Boolean.FALSE.equals(docente.getEstado())) {
                throw new RuntimeException(
                        "El docente con ID " + docenteId + " está inactivo"
                );
            }

            return docente;

        } catch (FeignException e) {
            throw new RuntimeException(
                    "No se pudo encontrar o consultar al docente con ID: "
                            + docenteId
            );
        }
    }
}
