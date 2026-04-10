package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.models.Usuario;
import com.libreriaApp.libreria.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UsuarioService implements IUsuarioServices {

    @Autowired
    private IUsuarioRepository usuarioRepo;

    public UsuarioService(IUsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepo.findAll();
    }

    @Override
    public Optional<Usuario> findBy(Long id) {
        return usuarioRepo.findById(id);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepo.deleteById(id);
    }
}
