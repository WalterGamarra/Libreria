package com.libreriaApp.libreria.controllers;

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

    // 📚 Listar libros (público)
    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        return ResponseEntity.ok(libroService.listarLibros());
    }

    // 🔍 Ver detalle de libro
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroPorId(@PathVariable Long id) {
        return libroService.buscarPorLibro(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}