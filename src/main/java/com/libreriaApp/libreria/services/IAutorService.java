package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.models.Autor;

import java.util.List;
import java.util.Optional;

public interface IAutorService {

    Autor saveAutor(Autor autor);
    List<Autor> findAll();
    Optional<Autor> findBy(Long id);
    Autor updateAutor(Autor autor);
    void deleteAutor(Long id);
}
