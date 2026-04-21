package com.libreriaApp.libreria.controllers;


import com.libreriaApp.libreria.models.usuarios_seguridad.Permiso;
import com.libreriaApp.libreria.services.IPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/permisos")
public class PermisoController {

    @Autowired
    private IPermisoService permisoService;

    @GetMapping
    public ResponseEntity<List<Permiso>> getAllPermiso(){
        List<Permiso> permiso = permisoService.findAll();
        return ResponseEntity.ok(permiso);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Permiso> getPermisoById(@PathVariable Long id){
        Optional<Permiso> permiso = permisoService.findById(id);
        return permiso.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Permiso> createPermiso(@RequestBody Permiso permiso){
        Permiso nuevoPermiso = permisoService.save(permiso);
        return ResponseEntity.ok(nuevoPermiso);
    }


}
