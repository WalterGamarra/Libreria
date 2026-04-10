package com.libreriaApp.libreria.controllers;

import com.libreriaApp.libreria.DTOs.LibroDetalleDTO;
import com.libreriaApp.libreria.DTOs.LibroTiendaDTO;
import com.libreriaApp.libreria.services.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tienda/libros")
public class LibroTiendaController {

    private ILibroService libroService;

    @Autowired
    public LibroTiendaController(ILibroService libroService) {
        this.libroService = libroService;
    }

    // Lista todos los libros en formato liviano para el catálogo
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<LibroTiendaDTO>> listar() {
        return ResponseEntity.ok(libroService.listarParaTienda());
    }

    // Detalle completo de un libro específico
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LibroDetalleDTO> detalle(@PathVariable Long id) {
        return libroService.buscarDetallePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}