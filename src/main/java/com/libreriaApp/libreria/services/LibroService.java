package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.DTOs.LibroCreateDTO;
import com.libreriaApp.libreria.DTOs.LibroDetalleDTO;
import com.libreriaApp.libreria.DTOs.LibroTiendaDTO;
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
import java.util.stream.Collectors;

@Service
public class LibroService implements ILibroService {


    private ILibrosRepository libroRepo;
    private IAutorRepository autorRepo;
    private ICategoriaRepository categoriaRepo;
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
    public Libro crearLibro(LibroCreateDTO dto) {

        Autor autor = autorRepo.findById(dto.getAutorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        Categoria categoria = categoriaRepo.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        Editorial editorial = editorialRepo.findById(dto.getEditorialId())
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada"));

        Libro libro = new Libro();
        libro.setTitulo(dto.getTitulo());
        libro.setImage(dto.getImage());
        libro.setPrecio(dto.getPrecio());
        libro.setAnioEdicion(dto.getAnioEdicion());
        libro.setAutor(autor);
        libro.setCategoria(categoria);
        libro.setEditorial(editorial);
        libro.setIsbn(dto.getIsbn());

        return libroRepo.save(libro);
    }

    public List<Libro> listarLibros() {
        return libroRepo.findAll();
    }


    public Optional<Libro> buscarPorLibro(Long id) {
        return libroRepo.findById(id);
    }


    @Override
    public Libro actualizarLibro(Long id, LibroCreateDTO dto) {

        Libro libro = libroRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        Autor autor = autorRepo.findById(dto.getAutorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        Categoria categoria = categoriaRepo.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        Editorial editorial = editorialRepo.findById(dto.getEditorialId())
                .orElseThrow(() -> new RuntimeException("Editorial no encontrada"));

        libro.setTitulo(dto.getTitulo());
        libro.setImage(dto.getImage());
        libro.setPrecio(dto.getPrecio());
        libro.setAutor(autor);
        libro.setCategoria(categoria);
        libro.setEditorial(editorial);
        libro.setIsbn(dto.getIsbn());

        return libroRepo.save(libro);
    }
    public void eliminarPorId(Long id) {
        libroRepo.deleteById(id);
    }


    private LibroTiendaDTO toTiendaDTO(Libro libro) {
        LibroTiendaDTO dto = new LibroTiendaDTO();
        dto.setId(libro.getIdLibro());
        dto.setTitulo(libro.getTitulo());
        dto.setAutor(libro.getAutor().getNombre() + " " + libro.getAutor().getApellido());
        dto.setCategoria(libro.getCategoria().getNombreGenero());
        dto.setImagen(libro.getImage());
        dto.setPrecio(libro.getPrecio()); // cuando lo agregues a Libro
        dto.setIsbn(libro.getIsbn());

        return dto;
    }

    private LibroDetalleDTO toDetalleDTO(Libro libro) {
        LibroDetalleDTO dto = new LibroDetalleDTO();
        dto.setId(libro.getIdLibro());
        dto.setTitulo(libro.getTitulo());
        dto.setAutor(libro.getAutor().getNombre() + " " + libro.getAutor().getApellido());
        dto.setCategoria(libro.getCategoria().getNombreGenero());
        dto.setEditorial(libro.getEditorial().getNombre());
        dto.setImagen(libro.getImage());
        dto.setPrecio(libro.getPrecio());
        dto.setIsbn(libro.getIsbn());


        return dto;
    }


    @Override
    public List<LibroTiendaDTO> listarParaTienda() {
        return libroRepo.findAll()
                .stream()
                .map(this::toTiendaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LibroDetalleDTO> buscarDetallePorId(Long id) {
        return libroRepo.findById(id)
                .map(this::toDetalleDTO);
    }
}
