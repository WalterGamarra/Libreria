package com.libreriaApp.libreria.controllers;

import com.libreriaApp.libreria.DTOs.LibroDetalleDTO;
import com.libreriaApp.libreria.DTOs.LibroTiendaDTO;
import com.libreriaApp.libreria.models.Libro;
import com.libreriaApp.libreria.services.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tienda/libros")
public class TiendaPublicaController {

    private ILibroService libroService;

    @Autowired
    public TiendaPublicaController(ILibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<LibroTiendaDTO>> listarLibros() {
        return ResponseEntity.ok(libroService.listarParaTienda());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDetalleDTO> getLibroPorId(@PathVariable Long id) {
        return libroService.buscarDetallePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}