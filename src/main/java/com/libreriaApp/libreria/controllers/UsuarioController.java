package com.libreriaApp.libreria.controllers;

import com.libreriaApp.libreria.models.usuarios_seguridad.Rol;
import com.libreriaApp.libreria.models.usuarios_seguridad.UserSec;
import com.libreriaApp.libreria.services.IRolService;
import com.libreriaApp.libreria.services.IUsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private IRolService rolService;
    @Autowired
    private IUsuarioServices usuarioServices;

    @GetMapping
    public ResponseEntity <List <UserSec>> getAllUsuario(){
        List <UserSec> usuario = usuarioServices.findAll();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity <UserSec> getUsuarioById(@PathVariable Long id){
        Optional <UserSec> usuario = usuarioServices.findBy(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity <UserSec> crearUsuario(@RequestBody UserSec usuario){
        Set<Rol> listaRol = new HashSet<Rol>();
        Rol leerRol;

        //Encriptar contraseña
        usuario.setPassword(usuarioServices.encriptPassword(usuario.getPassword()));

        for (Rol rol : usuario.getRolesList()){
            leerRol = rolService.findById(rol.getId()).orElse(null);

            if (leerRol != null){
                listaRol.add(leerRol);
            }
        }
        if (!listaRol.isEmpty()){
            usuario.setRolesList(listaRol);
            UserSec nuevoUsuario = usuarioServices.save(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        }
        return null;
    }





}
