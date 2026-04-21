package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.models.usuarios_seguridad.UserSec;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUsuarioServices {

    UserSec save(UserSec usuario);
    List <UserSec> findAll();
    Optional<UserSec> findBy(Long id);
    UserSec updateUsuario(UserSec usuario);
    void deleteUsuario(Long id);
    public String encriptPassword(String password);


}
