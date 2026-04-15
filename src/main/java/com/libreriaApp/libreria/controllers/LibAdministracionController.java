package com.libreriaApp.libreria.controllers;


import com.libreriaApp.libreria.DTOs.LibroCreateDTO;
import com.libreriaApp.libreria.models.Libro;
import com.libreriaApp.libreria.services.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/Tienda/libros")
public class LibAdministracionController {

    private ILibroService libroService;

    @Autowired
    public LibAdministracionController(ILibroService libroService) {
        this.libroService = libroService;
    }


    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros(){
        List<Libro> listaLibros = libroService.listarLibros();
        return ResponseEntity.ok(listaLibros);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    public ResponseEntity<Libro> getLibroPorId(@PathVariable Long id){
        return libroService.buscarPorLibro(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Libro> crear(@RequestBody LibroCreateDTO dto) {
        Libro nuevo = libroService.crearLibro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id,
                                            @RequestBody LibroCreateDTO dto) {

        Libro actualizado = libroService.actualizarLibro(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
