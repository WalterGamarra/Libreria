package com.libreriaApp.libreria.DTOs;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

// DTO liviano para mostrar en el catálogo de la tienda.
// Solo contiene lo que el cliente necesita ver en la lista.
@Getter
@Setter
public class LibroTiendaDTO {

    private Long id;
    private String titulo;
    private String autor;       // "Gabriel García Márquez" ya armado como String
    private String categoria;   // "Literatura" ya armado como String
    private String imagen;
    private BigDecimal precio;
}