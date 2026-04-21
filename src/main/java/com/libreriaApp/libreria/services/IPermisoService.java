package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.models.usuarios_seguridad.Permiso;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IPermisoService {

    List<Permiso> findAll();
    Optional <Permiso> findById(Long id);
    Permiso save (Permiso permiso);
    void deleteById(Long id);
    Permiso update(Permiso permiso);

}
