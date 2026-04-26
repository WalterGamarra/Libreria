package com.libreriaApp.libreria.controllers;

import com.libreriaApp.libreria.DTOs.UsuarioCreateDTO;
import com.libreriaApp.libreria.DTOs.UsuarioResponseDTO;
import com.libreriaApp.libreria.models.usuarios_seguridad.Rol;
import com.libreriaApp.libreria.models.usuarios_seguridad.UserSec;
import com.libreriaApp.libreria.services.IRolService;
import com.libreriaApp.libreria.services.IUsuarioServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Tag(name = "Usuarios", description = "Gestión de usuarios")
@RestController
@RequestMapping("/api/v1/usuario")
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioController {

    @Autowired
    private IRolService rolService;
    @Autowired
    private IUsuarioServices usuarioServices;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsuario() {

        List<UserSec> usuarios = usuarioServices.findAll();

        List<UsuarioResponseDTO> response = usuarios.stream().map(user ->
                new UsuarioResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getRolesList().stream()
                                .map(rol -> rol.getRole()) // o getName()
                                .collect(java.util.stream.Collectors.toSet())
                )
        ).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@PathVariable Long id){

        Optional<UserSec> usuarioOpt = usuarioServices.findBy(id);

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserSec user = usuarioOpt.get();

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRolesList().stream()
                        .map(rol -> rol.getRole()) // ⚠️ o getName()
                        .collect(java.util.stream.Collectors.toSet())
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@RequestBody UsuarioCreateDTO dto){

        UserSec usuario = new UserSec();

        Set<Rol> roles = new HashSet<>();

        for (Long rolId : dto.getRolesIds()) {
            rolService.findById(rolId).ifPresent(roles::add);
        }

        usuario.setUsername(dto.getUsername());
        usuario.setRolesList(roles);
        usuario.setPassword(usuarioServices.encriptPassword(dto.getPassword()));
        usuario.setEnabled(true);
        usuario.setAccountNotExpired(true);
        usuario.setCredentialNotExpired(true);
        usuario.setAccountNotLocked(true);

        UserSec nuevoUsuario = usuarioServices.save(usuario);

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                nuevoUsuario.getId(),
                nuevoUsuario.getUsername(),
                roles.stream().map(Rol::getRole).collect(java.util.stream.Collectors.toSet())
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        usuarioServices.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
