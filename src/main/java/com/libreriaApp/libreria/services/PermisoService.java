package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.models.usuarios_seguridad.Permiso;
import com.libreriaApp.libreria.repositories.IPermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermisoService implements IPermisoService{

    @Autowired
    private IPermisoRepository permisoRepo;


    @Override
    public List<Permiso> findAll() {
        return permisoRepo.findAll();
    }

    @Override
    public Optional<Permiso> findById(Long id) {
        return permisoRepo.findById(id);
    }

    @Override
    public Permiso save(Permiso permiso) {
        return permisoRepo.save(permiso);
    }

    @Override
    public void deleteById(Long id) {
        permisoRepo.deleteById(id);
    }

    @Override
    public Permiso update(Permiso permiso) {
        return permisoRepo.save(permiso);
    }
}
