package com.libreriaApp.libreria.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)  // guarda "PENDIENTE" en la BD, no un número
    private EstadoVenta estado;

    private BigDecimal total;

    // Muchas ventas pertenecen a un cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonBackReference(value = "cliente-ventas")
    private Cliente cliente;

    // Una venta tiene muchos detalles
    @OneToMany(mappedBy = "venta")
    @JsonManagedReference(value = "venta-detalles")
    private List<DetalleVenta> detalles;
}