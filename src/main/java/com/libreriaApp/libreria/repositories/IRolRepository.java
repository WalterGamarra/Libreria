package com.libreriaApp.libreria.repositories;

import com.libreriaApp.libreria.models.usuarios_seguridad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository <Rol, Long> {
}
