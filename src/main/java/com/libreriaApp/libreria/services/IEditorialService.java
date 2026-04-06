package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.models.Editorial;

import java.util.List;
import java.util.Optional;

public interface IEditorialService {

    Editorial create(Editorial editorial);
    List<Editorial> findAll();
    Optional <Editorial> findById(Long id);
    Editorial updateEditorial (Editorial editorial);
    void delete (Long id);
}
