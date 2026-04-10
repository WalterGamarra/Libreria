package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUsuarioServices {

    Usuario save(Usuario usuario);
    List <Usuario> findAll();
    Optional<Usuario> findBy(Long id);
    Usuario updateUsuario(Usuario usuario);
    void deleteUsuario(Long id);


}
