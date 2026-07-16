package com.instituto.cursoservice.controller;


import com.instituto.cursoservice.dto.Cursorequest;
import com.instituto.cursoservice.dto.Cursoresponse;
import com.instituto.cursoservice.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<Cursoresponse> registrarcurso(@RequestBody Cursorequest cursorequest){
        Cursoresponse cursoresponse = cursoService.registrarcurso(cursorequest);
        return new ResponseEntity<>(cursoresponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cursoresponse>> listarcursos(){
        List<Cursoresponse> cursos = cursoService.listarcursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cursoresponse> buscarcursoporid(@PathVariable Long id){
        Cursoresponse curso = cursoService.buscarcursoporid(id);
        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cursoresponse> actualizarcurso(@PathVariable Long id, @RequestBody Cursorequest cursorequest){
        Cursoresponse cursoresponse = cursoService.actualizarcurso(id,cursorequest);
        return ResponseEntity.ok(cursoresponse);
    }

    @PutMapping("/{id}/descontar-vacante")
    public ResponseEntity<Cursoresponse> descontarvacante(
            @PathVariable Long id
    ) {
        Cursoresponse curso =
                cursoService.descontarvacante(id);

        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}/devolver-vacante")
    public ResponseEntity<Cursoresponse> devolvervacante(
            @PathVariable Long id
    ) {
        Cursoresponse curso =
                cursoService.devolvervacante(id);

        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarcurso(@PathVariable Long id){
        cursoService.eliminarcurso(id);
        return ResponseEntity.noContent().build();
    }


}
