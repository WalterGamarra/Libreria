package com.libreriaApp.libreria.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEditorial;
    private String nombre;

    @OneToMany(mappedBy = "editorial")
    @JsonManagedReference(value = "editorial-libros")
    private List<Libro> libros;

}
