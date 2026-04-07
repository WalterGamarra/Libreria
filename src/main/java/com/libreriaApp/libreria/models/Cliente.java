package com.libreriaApp.libreria.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nombre;
    private String apellido;
    private String telefono;   // ← String, no int (puede tener +54, espacios, etc)
    private String email;
    private String password;

    // Un cliente puede tener muchas ventas
    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference(value = "cliente-ventas")
    private List<Venta> ventas;
}