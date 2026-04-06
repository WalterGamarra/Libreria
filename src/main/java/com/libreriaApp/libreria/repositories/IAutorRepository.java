package com.libreriaApp.libreria.repositories;

import com.libreriaApp.libreria.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAutorRepository extends JpaRepository <Autor, Long> {


}
