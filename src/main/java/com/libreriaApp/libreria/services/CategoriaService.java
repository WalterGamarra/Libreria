package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.models.Categoria;
import com.libreriaApp.libreria.repositories.ICategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService {

    private ICategoriaRepository categoriaRepo;

    public CategoriaService(ICategoriaRepository categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepo.save(categoria);
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepo.findAll();
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepo.findById(id);
    }

    @Override
    public Categoria updateCategoria(Categoria categoria) {
        return categoriaRepo.save(categoria);
    }

    @Override
    public void deleteCategoria(Long id) {
        categoriaRepo.deleteById(id);
    }
}
