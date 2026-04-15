package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.DTOs.LibroCreateDTO;
import com.libreriaApp.libreria.DTOs.LibroDetalleDTO;
import com.libreriaApp.libreria.DTOs.LibroTiendaDTO;
import com.libreriaApp.libreria.models.Libro;
import java.util.List;
import java.util.Optional;


public interface ILibroService {

    Libro crearLibro(LibroCreateDTO dto);

    List<Libro> listarLibros();
    Optional<Libro> buscarPorLibro(Long id);
    Libro actualizarLibro(Long id, LibroCreateDTO dto);

    void eliminarPorId(Long id);

    List<LibroTiendaDTO> listarParaTienda();
    Optional<LibroDetalleDTO> buscarDetallePorId(Long id);
}