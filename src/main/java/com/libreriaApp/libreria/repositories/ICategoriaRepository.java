package com.libreriaApp.libreria.repositories;

import com.libreriaApp.libreria.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

}
