package com.libreriaApp.libreria.DTOs;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

// DTO más completo para la página de detalle de un libro.
// El cliente hace click en un libro y ve esta información.
@Getter
@Setter
public class LibroDetalleDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String categoria;
    private String editorial;
    private String imagen;
    private BigDecimal precio;
}