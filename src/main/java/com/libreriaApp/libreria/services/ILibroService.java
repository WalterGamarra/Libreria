package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.DTOs.LibroDetalleDTO;
import com.libreriaApp.libreria.DTOs.LibroTiendaDTO;
import com.libreriaApp.libreria.models.Libro;
import java.util.List;
import java.util.Optional;


public interface ILibroService {

    Libro crearLibro(Libro libro);
    List<Libro> listarLibros();
    Optional<Libro> buscarPorLibro(Long id);
    Libro actualizarLibro(Libro libro);
    void eliminarPorId(Long id);

    List<LibroTiendaDTO> listarParaTienda();
    Optional<LibroDetalleDTO> buscarDetallePorId(Long id);
}