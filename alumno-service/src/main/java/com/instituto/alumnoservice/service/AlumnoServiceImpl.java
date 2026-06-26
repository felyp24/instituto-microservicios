package com.instituto.alumnoservice.service;


import com.instituto.alumnoservice.dto.AlumnoRequest;
import com.instituto.alumnoservice.dto.AlumnoResponse;
import com.instituto.alumnoservice.entity.Alumno;
import com.instituto.alumnoservice.mapper.AlumnoMapper;
import com.instituto.alumnoservice.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService{

    private final AlumnoRepository alumnoRepository;
    private final AlumnoMapper alumnoMapper;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository, AlumnoMapper alumnoMapper) {
        this.alumnoRepository = alumnoRepository;
        this.alumnoMapper = alumnoMapper;
    }

    @Override
    public AlumnoResponse registraralumno(AlumnoRequest alumnoRequest) {
        Alumno alumno = alumnoMapper.toEntity(alumnoRequest);
        alumno.setEstado(true);
        Alumno alumno_guardado = alumnoRepository.save(alumno);
        return alumnoMapper.toresponsedto(alumno_guardado);
    }

    @Override
    public List<AlumnoResponse> listaralumnos() {
        List<Alumno> alumnos = alumnoRepository.findAll();

        return alumnoMapper.toresponsedtolist(alumnos);
    }

    @Override
    public AlumnoResponse buscaralumnoporid(Long id) {
        Alumno alumno = alumnoRepository.findById(id).orElseThrow(()-> new RuntimeException("Alumno no encontrado"));


        return alumnoMapper.toresponsedto(alumno);
    }

    @Override
    public AlumnoResponse actualizaralumno(Long id, AlumnoRequest alumnoRequest) {
        Alumno alumno = alumnoRepository.findById(id).orElseThrow(()->new RuntimeException("Alumno no encontrado"));
        alumno.setDni(alumnoRequest.getDni());
        alumno.setNombres(alumnoRequest.getNombres());
        alumno.setApellidos(alumnoRequest.getApellidos());
        alumno.setCorreo(alumnoRequest.getCorreo());
        alumno.setTelefono(alumnoRequest.getTelefono());
        Alumno alumno_actualizado = alumnoRepository.save(alumno);
        return alumnoMapper.toresponsedto(alumno_actualizado);
    }

    @Override
    public void eliminaralumno(Long id) {
        Alumno alumno = alumnoRepository.findById(id).orElseThrow(()-> new RuntimeException("Alumno no encontrado"));
        alumno.setEstado(false);
        alumnoRepository.save(alumno);
    }
}
