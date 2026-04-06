package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.models.Editorial;
import com.libreriaApp.libreria.repositories.IEditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialService implements IEditorialService {

    @Autowired
    private IEditorialRepository editorialRepo;

    public EditorialService(IEditorialRepository editorialRepo) {
        this.editorialRepo = editorialRepo;
    }

    @Override
    public Editorial create(Editorial editorial) {
        return editorialRepo.save(editorial);
    }

    @Override
    public List<Editorial> findAll() {
        return editorialRepo.findAll();
    }

    @Override
    public Optional<Editorial> findById(Long id) {
        return editorialRepo.findById(id);
    }

    @Override
    public Editorial updateEditorial(Editorial editorial) {
        return editorialRepo.save(editorial);
    }

    @Override
    public void delete(Long id) {
        editorialRepo.deleteById(id);
    }


}
