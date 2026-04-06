package com.libreriaApp.libreria.controllers;

import com.libreriaApp.libreria.models.Categoria;
import com.libreriaApp.libreria.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaRestController  {

    private ICategoriaService categoriaService;

    @Autowired
    public CategoriaRestController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {
        Categoria nueva = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategoria(){
        List<Categoria> listaCategorias = categoriaService.findAll();
        return ResponseEntity.ok(listaCategorias);
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> getLibroPorId(@PathVariable Long id){
        return categoriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        categoria.setIdCategoria(id);
        Categoria actualizada = categoriaService.save(categoria);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id){
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}