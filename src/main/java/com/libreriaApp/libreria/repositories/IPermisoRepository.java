package com.libreriaApp.libreria.repositories;


import com.libreriaApp.libreria.models.usuarios_seguridad.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermisoRepository extends JpaRepository<Permiso, Long> {



}
