package com.libreriaApp.libreria.controllers;

import com.libreriaApp.libreria.services.IUsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setup")
public class SetupController {

    @Autowired
    private IUsuarioServices usuarioServices;

    @GetMapping("/fix-admin")
    public ResponseEntity<String> fixAdmin() {
        String hash = usuarioServices.encriptPassword("admin123");
        return ResponseEntity.ok(hash);
    }
}