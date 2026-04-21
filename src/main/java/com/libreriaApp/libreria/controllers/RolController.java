package com.libreriaApp.libreria.controllers;

import com.libreriaApp.libreria.models.usuarios_seguridad.Permiso;
import com.libreriaApp.libreria.models.usuarios_seguridad.Rol;
import com.libreriaApp.libreria.services.IPermisoService;
import com.libreriaApp.libreria.services.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/rol")
public class RolController {

    @Autowired
    private IRolService rolService;// Inyectar dependencia

    @Autowired
    private IPermisoService permisoService;



    @GetMapping
    public ResponseEntity<List<Rol>> getAllRol(){
        List<Rol> roles = rolService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Rol> getRolById(@PathVariable Long id){
        Optional<Rol> rol = rolService.findById(id);
        return rol.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol){
        Set<Permiso> listaPermiso = new HashSet<Permiso>();
        Permiso leerPermiso;

        for (Permiso per : rol.getPermisos()){
            leerPermiso = permisoService.findById(per.getId()).orElse(null);

            if (leerPermiso != null){
               listaPermiso.add(leerPermiso);
            }
        }
        rol.setPermisos(listaPermiso);
        Rol nuevoRol = rolService.save(rol);
        return ResponseEntity.ok(nuevoRol);
    }




}
