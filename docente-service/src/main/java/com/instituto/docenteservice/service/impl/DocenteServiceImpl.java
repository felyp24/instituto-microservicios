package com.instituto.docenteservice.service.impl;

import com.instituto.docenteservice.dto.DocenteRequestDTO;
import com.instituto.docenteservice.dto.DocenteResponseDTO;
import com.instituto.docenteservice.entity.Docente;
import com.instituto.docenteservice.mapper.DocenteMapper;
import com.instituto.docenteservice.repository.DocenteRepository;
import com.instituto.docenteservice.service.DocenteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocenteServiceImpl implements DocenteService {

    private final DocenteRepository docenteRepository;
    private final DocenteMapper docenteMapper;

    public DocenteServiceImpl(
            DocenteRepository docenteRepository,
            DocenteMapper docenteMapper
    ) {
        this.docenteRepository = docenteRepository;
        this.docenteMapper = docenteMapper;
    }

    @Override
    public DocenteResponseDTO registrar(DocenteRequestDTO requestDTO) {

        if (docenteRepository.existsByDni(requestDTO.getDni())) {
            throw new RuntimeException(
                    "Ya existe un docente registrado con el DNI: "
                            + requestDTO.getDni()
            );
        }

        if (docenteRepository.existsByCorreo(requestDTO.getCorreo())) {
            throw new RuntimeException(
                    "Ya existe un docente registrado con el correo: "
                            + requestDTO.getCorreo()
            );
        }

        Docente docente = docenteMapper.toEntity(requestDTO);
        docente.setEstado(true);

        Docente docenteGuardado = docenteRepository.save(docente);

        return docenteMapper.toResponseDTO(docenteGuardado);
    }

    @Override
    public Iterable<DocenteResponseDTO> listar() {

        Iterable<Docente> docentes =
                docenteRepository.findByEstadoTrue();

        List<DocenteResponseDTO> respuesta = new ArrayList<>();

        docentes.forEach(docente ->
                respuesta.add(
                        docenteMapper.toResponseDTO(docente)
                )
        );

        return respuesta;
    }

    @Override
    public DocenteResponseDTO buscarPorId(Long id) {

        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No se encontró el docente con ID: " + id
                        )
                );

        return docenteMapper.toResponseDTO(docente);
    }

    @Override
    public DocenteResponseDTO actualizar(
            Long id,
            DocenteRequestDTO requestDTO
    ) {

        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No se encontró el docente con ID: " + id
                        )
                );

        if (Boolean.FALSE.equals(docente.getEstado())) {
            throw new RuntimeException(
                    "El docente con ID " + id + " se encuentra inactivo"
            );
        }

        if (docenteRepository.existsByDniAndIdNot(
                requestDTO.getDni(),
                id
        )) {
            throw new RuntimeException(
                    "Otro docente ya utiliza el DNI: "
                            + requestDTO.getDni()
            );
        }

        if (docenteRepository.existsByCorreoAndIdNot(
                requestDTO.getCorreo(),
                id
        )) {
            throw new RuntimeException(
                    "Otro docente ya utiliza el correo: "
                            + requestDTO.getCorreo()
            );
        }

        docenteMapper.actualizarEntidad(requestDTO, docente);

        Docente docenteActualizado =
                docenteRepository.save(docente);

        return docenteMapper.toResponseDTO(docenteActualizado);
    }

    @Override
    public void desactivar(Long id) {

        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No se encontró el docente con ID: " + id
                        )
                );

        if (Boolean.FALSE.equals(docente.getEstado())) {
            throw new RuntimeException(
                    "El docente ya se encuentra inactivo"
            );
        }

        docente.setEstado(false);
        docenteRepository.save(docente);
    }
}