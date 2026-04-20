package com.libreriaApp.libreria.DTOs;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDTO (@NotBlank String username,
                                   @NotBlank String password) {


}
