package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.models.usuarios_seguridad.UserSec;
import com.libreriaApp.libreria.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioServices {


    private final IUsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepo, PasswordEncoder passwordEncoder) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encriptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public UserSec save(UserSec usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    public List<UserSec> findAll() {
        return usuarioRepo.findAll();
    }

    @Override
    public Optional<UserSec> findBy(Long id) {
        return usuarioRepo.findById(id);
    }

    @Override
    public UserSec updateUsuario(UserSec usuario) {
        return usuarioRepo.save(usuario);
    }
    @Override
    public void deleteUsuario(Long id) {
        UserSec usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.getRolesList().clear();
        usuarioRepo.save(usuario);
        usuarioRepo.deleteById(id);
    }
}
