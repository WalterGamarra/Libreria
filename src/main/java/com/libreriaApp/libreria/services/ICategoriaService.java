package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.models.Autor;
import com.libreriaApp.libreria.models.Categoria;
import java.util.List;
import java.util.Optional;

public interface ICategoriaService {

    Categoria save(Categoria categoria);
    List<Categoria> findAll();
    Optional<Categoria> findById(Long id);
    Categoria updateCategoria(Categoria categoria );
    void deleteCategoria (Long id);

}
