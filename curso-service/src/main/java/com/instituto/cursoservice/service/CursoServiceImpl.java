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
import java.time.LocalDate;

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

        String diaSemana =
                obtenerDiaSemana(cursorequest.getFecha());

        cursorequest.setDiaSemana(diaSemana);

        validarCruceHorario(null, cursorequest);

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

        String diaSemana =
                obtenerDiaSemana(cursorequest.getFecha());

        cursorequest.setDiaSemana(diaSemana);

        validarCruceHorario(id, cursorequest);

        Double horasDuracion = calcularHorasDuracion(
                cursorequest.getHoraInicio(),
                cursorequest.getHoraFin()
        );

        curso.setNombre(cursorequest.getNombre());
        curso.setDescripcion(cursorequest.getDescripcion());
        curso.setCreditos(cursorequest.getCreditos());
        curso.setVacantes(cursorequest.getVacantes());
        curso.setDocenteId(cursorequest.getDocenteId());

        curso.setFecha(cursorequest.getFecha());
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

    private String obtenerDiaSemana(LocalDate fecha) {

        if (fecha == null) {
            throw new RuntimeException(
                    "La fecha del curso es obligatoria"
            );
        }

        return switch (fecha.getDayOfWeek()) {
            case MONDAY -> "LUNES";
            case TUESDAY -> "MARTES";
            case WEDNESDAY -> "MIERCOLES";
            case THURSDAY -> "JUEVES";
            case FRIDAY -> "VIERNES";
            case SATURDAY -> "SABADO";
            case SUNDAY -> "DOMINGO";
        };
    }

    private void validarCruceHorario(
            Long cursoIdActual,
            Cursorequest cursorequest
    ) {

        if (cursorequest.getFecha() == null) {
            throw new RuntimeException(
                    "La fecha del curso es obligatoria"
            );
        }

        if (cursorequest.getHoraInicio() == null
                || cursorequest.getHoraFin() == null) {

            throw new RuntimeException(
                    "La hora de inicio y la hora de fin son obligatorias"
            );
        }

        if (!cursorequest.getHoraFin()
                .isAfter(cursorequest.getHoraInicio())) {

            throw new RuntimeException(
                    "La hora de fin debe ser posterior a la hora de inicio"
            );
        }

        if (cursorequest.getAula() == null
                || cursorequest.getAula().isBlank()) {

            throw new RuntimeException(
                    "El aula es obligatoria"
            );
        }

        List<Curso> cursosDeLaFecha =
                cursorepository.findByFechaAndEstadoTrue(
                        cursorequest.getFecha()
                );

        for (Curso cursoExistente : cursosDeLaFecha) {

            /*
             * Durante una actualización, evitamos comparar
             * el curso con él mismo.
             */
            if (cursoIdActual != null
                    && cursoIdActual.equals(cursoExistente.getId())) {
                continue;
            }

            /*
             * Los cursos antiguos pueden tener horarios nulos.
             */
            if (cursoExistente.getHoraInicio() == null
                    || cursoExistente.getHoraFin() == null) {
                continue;
            }

            boolean horariosSuperpuestos =
                    cursorequest.getHoraInicio()
                            .isBefore(cursoExistente.getHoraFin())
                            &&
                            cursorequest.getHoraFin()
                                    .isAfter(cursoExistente.getHoraInicio());

            if (!horariosSuperpuestos) {
                continue;
            }

            boolean mismoDocente =
                    cursorequest.getDocenteId() != null
                            && cursorequest.getDocenteId()
                            .equals(cursoExistente.getDocenteId());

            if (mismoDocente) {
                throw new RuntimeException(
                        "El docente ya tiene el curso "
                                + cursoExistente.getNombre()
                                + " en esa fecha y horario"
                );
            }

            boolean mismaAula =
                    cursoExistente.getAula() != null
                            && cursorequest.getAula()
                            .equalsIgnoreCase(cursoExistente.getAula());

            if (mismaAula) {
                throw new RuntimeException(
                        "El aula "
                                + cursorequest.getAula()
                                + " está ocupada por el curso "
                                + cursoExistente.getNombre()
                                + " en esa fecha y horario"
                );
            }
        }
    }
}