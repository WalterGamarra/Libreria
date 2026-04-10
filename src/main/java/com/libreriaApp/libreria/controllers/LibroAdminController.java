package com.libreriaApp.libreria.controllers;


import com.libreriaApp.libreria.models.Libro;
import com.libreriaApp.libreria.services.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/libros")
public class LibroAdminController {

    private ILibroService libroService;

    @Autowired
    public LibroAdminController(ILibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros(){
        List<Libro> listaLibros = libroService.listarLibros();
        return ResponseEntity.ok(listaLibros);
    }

    @GetMapping("{id}")
    public ResponseEntity<Libro> getLibroPorId(@PathVariable Long id){
        return libroService.buscarPorLibro(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Libro> crear(@RequestBody Libro libro) {
        Libro nuevo = libroService.crearLibro(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @RequestBody Libro libro) {
        libro.setIdLibro(id);
        Libro actualizado = libroService.crearLibro(libro);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
