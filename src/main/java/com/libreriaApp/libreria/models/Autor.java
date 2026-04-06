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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "Id_Autor")
    private Long idAutor;

    private String nombre;
    private String apellido;

    @OneToMany(mappedBy = "autor")
    @JsonManagedReference(value = "autor-libros")  // ← este lado SÍ se serializa
    private List<Libro> libros;
}

