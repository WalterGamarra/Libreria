package com.libreriaApp.libreria.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "Id_Libro")
    private Long idLibro;

    private String titulo;
    private String image;
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer anioEdicion;

    @Column(unique = true)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    @JsonBackReference(value = "autor-libros")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonBackReference(value = "categoria-libros")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "editorial_id")
    @JsonBackReference(value = "editorial-libros")
    private Editorial editorial;


}

