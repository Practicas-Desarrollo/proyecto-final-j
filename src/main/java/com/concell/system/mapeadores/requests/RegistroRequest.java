package com.concell.system.mapeadores.requests;

import com.concell.system.modelos.Estado;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;

public record RegistroRequest(
        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email debe tener un formato válido")
        @Schema(example = "usuario@usuario.com")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 6, max = 50, message = "La contraseña debe tener entre 6 y 50 caracteres")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$",
                message = "La contraseña debe contener al menos 1 mayúscula, 1 minúscula, 1 número y 1 carácter especial"
        )
        String password,

        Estado estado,

        Integer idRol
) {}
