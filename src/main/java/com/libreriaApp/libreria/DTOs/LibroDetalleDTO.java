package com.libreriaApp.libreria.DTOs;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

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
    private String isbn;

}