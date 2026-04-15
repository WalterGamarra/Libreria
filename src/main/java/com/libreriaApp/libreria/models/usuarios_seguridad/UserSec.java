package com.libreriaApp.libreria.models.usuarios_seguridad;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSec   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    private boolean enabled; //Confirma si esta disponible
    private boolean accountNotExpired; //Confirma que cuenta no esta expirada
    private boolean credentialNotExpired; //Confirma que no expiro
    private boolean accountNotLocked; // Confirma que no esta bloqueada

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //Estrategia para carga de datos/ eageer para cargar todos los roles
    @JoinTable(name ="user_roles", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))


    private Set<Rol> rolesList = new HashSet<>(); //Set para que no registre repetidos



}