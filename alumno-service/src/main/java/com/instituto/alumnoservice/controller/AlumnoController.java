package com.instituto.alumnoservice.controller;


import com.instituto.alumnoservice.dto.AlumnoRequest;
import com.instituto.alumnoservice.dto.AlumnoResponse;
import com.instituto.alumnoservice.entity.Alumno;
import com.instituto.alumnoservice.service.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public ResponseEntity<List<AlumnoResponse>> listaralumnos(){
        List<AlumnoResponse> lista = alumnoService.listaralumnos();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<AlumnoResponse> registraralumno(@RequestBody AlumnoRequest dto){
        AlumnoResponse alumnoregistrado = alumnoService.registraralumno(dto);
        return new ResponseEntity<>(alumnoregistrado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponse> buscaralumnoporid(@PathVariable Long id){

        AlumnoResponse alumno = alumnoService.buscaralumnoporid(id);
        return ResponseEntity.ok(alumno);

    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoResponse> actualizaralumno(@PathVariable Long id, @RequestBody AlumnoRequest dto){
        AlumnoResponse alumno_actualizado = alumnoService.actualizaralumno(id,dto);
        return ResponseEntity.ok(alumno_actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaralumno(@PathVariable Long id){
        alumnoService.eliminaralumno(id);
        return ResponseEntity.noContent().build();
    }

}
