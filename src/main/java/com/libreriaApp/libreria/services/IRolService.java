package com.libreriaApp.libreria.services;


import com.libreriaApp.libreria.models.usuarios_seguridad.Rol;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IRolService {

    List<Rol> findAll();
    Optional<Rol> findById(Long id);
    Rol save (Rol rol);
    void deleteById(Long id);
    Rol update(Rol rol);

}
