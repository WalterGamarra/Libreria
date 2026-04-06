package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.models.Autor;
import com.libreriaApp.libreria.repositories.IAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService implements IAutorService {

    private IAutorRepository autorRepo;

    @Autowired
    public AutorService(IAutorRepository autorRepo) {
        this.autorRepo = autorRepo;
    }

    @Override
    public Autor saveAutor(Autor autor) {
        return autorRepo.save(autor);
    }

    @Override
    public List<Autor> findAll() {
        return autorRepo.findAll();
    }

    @Override
    public Optional<Autor> findBy(Long id) {
        return autorRepo.findById(id);
    }

    @Override
    public Autor updateAutor(Autor autor) {
        return autorRepo.save(autor);
    }


    @Override
    public void deleteAutor(Long id) {
        autorRepo.deleteById(id);
    }
}
