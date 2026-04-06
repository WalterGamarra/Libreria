package com.libreriaApp.libreria.controllers;

import com.libreriaApp.libreria.models.Autor;
import com.libreriaApp.libreria.services.IAutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autor")
public class AutorRestController {

    private IAutorService autorService;

    @Autowired
    public AutorRestController(IAutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listarAutor(){
        List<Autor> listaAutor = autorService.findAll();
        return ResponseEntity.ok(listaAutor);
    }

    @GetMapping("{id}")
    public ResponseEntity<Autor> getAutorPorId(@PathVariable Long id){
        return autorService.findBy(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Autor> crear(@RequestBody Autor autor) {
        Autor nuevo = autorService.saveAutor(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> actualizar(@PathVariable Long id, @RequestBody Autor autor) {
        autor.setIdAutor(id);
        Autor actualizado = autorService.saveAutor(autor);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        autorService.deleteAutor(id);
        return ResponseEntity.noContent().build();
    }
}
