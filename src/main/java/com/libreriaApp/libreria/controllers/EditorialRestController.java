package com.libreriaApp.libreria.controllers;

import com.libreriaApp.libreria.models.Editorial;
import com.libreriaApp.libreria.models.Libro;
import com.libreriaApp.libreria.repositories.IEditorialRepository;
import com.libreriaApp.libreria.services.IEditorialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Editoriales", description = "CRUD de editoriales")
@RestController
@RequestMapping("/api/v1/tienda/admin/editorial")
public class EditorialRestController {

    private IEditorialService editorialService;

    public EditorialRestController(IEditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping
    public ResponseEntity<List<Editorial>> listarEditorial(){
        List<Editorial> listaEditorial = editorialService.findAll();
        return ResponseEntity.ok(listaEditorial);
    }

    @GetMapping("{id}")
    public ResponseEntity<Editorial> getEditorialPorId(@PathVariable Long id){
        return editorialService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Editorial> crear(@RequestBody Editorial editorial) {
        Editorial nueva = editorialService.create(editorial);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editorial> actualizar(@PathVariable Long id, @RequestBody Editorial editorial) {
        editorial.setIdEditorial(id);
        Editorial actualizada = editorialService.updateEditorial(editorial);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        editorialService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
