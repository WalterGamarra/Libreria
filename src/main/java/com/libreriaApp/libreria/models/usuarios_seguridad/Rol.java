package com.libreriaApp.libreria.models.usuarios_seguridad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "roles")
public class Rol {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String role;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable (name = "roles_permisos", joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permisos_id"))
        private Set <Permiso> permisos = new HashSet<>();

}