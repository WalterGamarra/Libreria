package com.libreriaApp.libreria.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateDTO {

    private String username;
    private String password;
    private Set<Long> rolesIds;


}