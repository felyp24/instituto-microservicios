package com.instituto.cursoservice.service;

import com.instituto.cursoservice.client.DocenteClient;
import com.instituto.cursoservice.dto.Cursorequest;
import com.instituto.cursoservice.dto.Cursoresponse;
import com.instituto.cursoservice.dto.DocenteResponseDTO;
import com.instituto.cursoservice.entity.Curso;
import com.instituto.cursoservice.mapper.CursoMapper;
import com.instituto.cursoservice.repository.Cursorepository;
import feign.FeignException;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalTime;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final Cursorepository cursorepository;
    private final CursoMapper cursoMapper;
    private final DocenteClient docenteClient;

    public CursoServiceImpl(
            Cursorepository cursorepository,
            CursoMapper cursoMapper,
            DocenteClient docenteClient
    ) {
        this.cursorepository = cursorepository;
        this.cursoMapper = cursoMapper;
        this.docenteClient = docenteClient;
    }

    @Override
    public Cursoresponse registrarcurso(
            Cursorequest cursorequest
    ) {
        validarDocente(cursorequest.getDocenteId());

        Double horasDuracion = calcularHorasDuracion(
                cursorequest.getHoraInicio(),
                cursorequest.getHoraFin()
        );

        Curso curso = cursoMapper.toentity(cursorequest);

        curso.setHorasDuracion(horasDuracion);
        curso.setEstado(true);

        Curso cursoguardado =
                cursorepository.save(curso);

        return cursoMapper.toresponsedto(cursoguardado);
    }
    @Override
    public List<Cursoresponse> listarcursos() {

        List<Curso> cursos = cursorepository.findAll();

        return cursoMapper.toresponselist(cursos);
    }

    @Override
    public Cursoresponse buscarcursoporid(Long id) {

        Curso curso = cursorepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Curso no encontrado")
                );

        return cursoMapper.toresponsedto(curso);
    }

    @Override
    public Cursoresponse actualizarcurso(
            Long id,
            Cursorequest cursorequest
    ) {
        Curso curso = cursorepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Curso no encontrado")
                );

        if (Boolean.FALSE.equals(curso.getEstado())) {
            throw new RuntimeException(
                    "No se puede actualizar un curso inactivo"
            );
        }

        validarDocente(cursorequest.getDocenteId());

        Double horasDuracion = calcularHorasDuracion(
                cursorequest.getHoraInicio(),
                cursorequest.getHoraFin()
        );

        curso.setNombre(cursorequest.getNombre());
        curso.setDescripcion(cursorequest.getDescripcion());
        curso.setCreditos(cursorequest.getCreditos());
        curso.setVacantes(cursorequest.getVacantes());
        curso.setDocenteId(cursorequest.getDocenteId());

        curso.setDiaSemana(cursorequest.getDiaSemana());
        curso.setHoraInicio(cursorequest.getHoraInicio());
        curso.setHoraFin(cursorequest.getHoraFin());
        curso.setHorasDuracion(horasDuracion);
        curso.setAula(cursorequest.getAula());
        curso.setPeriodo(cursorequest.getPeriodo());

        Curso cursoactualizado =
                cursorepository.save(curso);

        return cursoMapper.toresponsedto(cursoactualizado);
    }

    @Override
    public Cursoresponse descontarvacante(Long id) {

        Curso curso = cursorepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Curso no encontrado")
                );

        if (Boolean.FALSE.equals(curso.getEstado())) {
            throw new RuntimeException(
                    "No se puede descontar una vacante de un curso inactivo"
            );
        }

        if (curso.getVacantes() == null || curso.getVacantes() <= 0) {
            throw new RuntimeException(
                    "El curso no tiene vacantes disponibles"
            );
        }

        curso.setVacantes(curso.getVacantes() - 1);

        Curso cursoactualizado =
                cursorepository.save(curso);

        return cursoMapper.toresponsedto(cursoactualizado);
    }

    @Override
    public void eliminarcurso(Long id) {

        Curso curso = cursorepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Curso no encontrado")
                );

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
                        "El docente con ID "
                                + docenteId
                                + " está inactivo"
                );
            }

            return docente;

        } catch (FeignException e) {
            throw new RuntimeException(
                    "No se pudo consultar al docente con ID: "
                            + docenteId
            );
        }
    }

    @Override
    public Cursoresponse devolvervacante(Long id) {

        Curso curso = cursorepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Curso no encontrado")
                );

        if (curso.getVacantes() == null) {
            curso.setVacantes(1);
        } else {
            curso.setVacantes(curso.getVacantes() + 1);
        }

        Curso cursoactualizado =
                cursorepository.save(curso);

        return cursoMapper.toresponsedto(cursoactualizado);
    }

    private Double calcularHorasDuracion(
            LocalTime horaInicio,
            LocalTime horaFin
    ) {
        if (horaInicio == null || horaFin == null) {
            throw new RuntimeException(
                    "La hora de inicio y la hora de fin son obligatorias"
            );
        }

        if (!horaFin.isAfter(horaInicio)) {
            throw new RuntimeException(
                    "La hora de fin debe ser posterior a la hora de inicio"
            );
        }

        long minutos = Duration.between(
                horaInicio,
                horaFin
        ).toMinutes();

        return minutos / 60.0;
    }
}