package com.libreriaApp.libreria.repositories;

import com.libreriaApp.libreria.models.usuarios_seguridad.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository <UserSec, Long>{

    Optional<UserSec> findUserByUsername(String userName);
}
