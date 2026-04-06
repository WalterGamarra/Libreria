package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.models.Autor;
import com.libreriaApp.libreria.models.Categoria;
import com.libreriaApp.libreria.models.Editorial;
import com.libreriaApp.libreria.models.Libro;
import com.libreriaApp.libreria.repositories.IAutorRepository;
import com.libreriaApp.libreria.repositories.ICategoriaRepository;
import com.libreriaApp.libreria.repositories.IEditorialRepository;
import com.libreriaApp.libreria.repositories.ILibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService implements ILibroService{


    private ILibrosRepository libroRepo;
    private IAutorRepository autorRepo;          // ← agregar
    private ICategoriaRepository categoriaRepo;  // ← agregar
    private IEditorialRepository editorialRepo;

    @Autowired
    public LibroService(
            ILibrosRepository libroRepo,
            IAutorRepository autorRepo,
            ICategoriaRepository categoriaRepo,
            IEditorialRepository editorialRepo) {
        this.libroRepo = libroRepo;
        this.autorRepo = autorRepo;
        this.categoriaRepo = categoriaRepo;
        this.editorialRepo = editorialRepo;
    }

    @Override
    public Libro crearLibro(Libro libro) {
        Autor autor = autorRepo.findById(libro.getAutor().getIdAutor())
                .orElseThrow(() -> new RuntimeException("Autor con id "
                        + libro.getAutor().getIdAutor() + " no encontrado"));
        Categoria categoria = categoriaRepo.findById(libro.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria con id "
                        + libro.getCategoria().getIdCategoria() + " no encontrada"));
        Editorial editorial = editorialRepo.findById(libro.getEditorial().getIdEditorial())
                .orElseThrow(() -> new RuntimeException("Editorial con id "
                        + libro.getEditorial().getIdEditorial() + " no encontrada"));
        libro.setAutor(autor);
        libro.setCategoria(categoria);
        libro.setEditorial(editorial);
        return libroRepo.save(libro);
    }


    public List<Libro> listarLibros() {
        return libroRepo.findAll();
    }


    public Optional<Libro> buscarPorLibro(Long id) {
        return libroRepo.findById(id);
    }


    public Libro actualizarLibro(Libro libro){
        return libroRepo.save(libro);
    }




    public void eliminarPorId(Long id) {
        libroRepo.deleteById(id);
    }




}
