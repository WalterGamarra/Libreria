package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.models.usuarios_seguridad.Rol;
import com.libreriaApp.libreria.repositories.IPermisoRepository;
import com.libreriaApp.libreria.repositories.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService implements IRolService{

    @Autowired
    private IRolRepository rolRepo;

    @Autowired
    private IPermisoRepository permisoRepo;

    @Override
    public List<Rol> findAll() {
        return rolRepo.findAll();
    }

    @Override
    public Optional<Rol> findById(Long id) {
        return rolRepo.findById(id);
    }

    @Override
    public Rol save(Rol rol) {
        return rolRepo.save(rol);
    }

    @Override
    public void deleteById(Long id) {
        rolRepo.deleteById(id);
    }

    @Override
    public Rol update(Rol rol) {
        return rolRepo.save(rol);
    }
}
