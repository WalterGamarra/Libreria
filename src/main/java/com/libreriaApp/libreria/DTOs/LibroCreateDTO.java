package com.libreriaApp.libreria.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LibroCreateDTO {
    private String titulo;
    private String image;
    private BigDecimal precio;
    private Integer anioEdicion;
    private Long autorId;
    private Long categoriaId;
    private Long editorialId;
    private String isbn;

}
