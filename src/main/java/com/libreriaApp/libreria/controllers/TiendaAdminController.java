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

//Controlador para administracion
@RestController
@RequestMapping("/api/v1/tienda/admin/libros")
public class TiendaAdminController {

    private ILibroService libroService;

    @Autowired
    public TiendaAdminController(ILibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Libro>> listarLibros(){
        return ResponseEntity.ok(libroService.listarLibros());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")

    public ResponseEntity<Libro> getLibroPorId(@PathVariable Long id){
        return libroService.buscarPorLibro(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // SOLO ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Libro> crear(@RequestBody LibroCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(libroService.crearLibro(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id,
                                            @RequestBody LibroCreateDTO dto) {
        return ResponseEntity.ok(libroService.actualizarLibro(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}