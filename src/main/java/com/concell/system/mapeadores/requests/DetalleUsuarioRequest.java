package com.concell.system.mapeadores.requests;

import javax.validation.constraints.*;
import java.time.LocalDate;

public record DetalleUsuarioRequest(
        @Size(max = 255, message = "La URL de la foto no puede exceder 255 caracteres")
        @Pattern(regexp = "^(https?://).*", message = "La foto debe ser una URL válida")
        String foto,

        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        @Size(max = 15, message = "El nombre de usuario no puede exceder 15 caracteres")
        String nombreUsuario,

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
        String nombre,

        @NotBlank(message = "El apellido paterno no puede estar vacío")
        @Size(max = 100, message = "El apellido paterno no puede exceder 100 caracteres")
        String apellidoPaterno,

        @NotBlank(message = "El apellido materno no puede estar vacío")
        @Size(max = 100, message = "El apellido materno no puede exceder 100 caracteres")
        String apellidoMaterno,

        @Past(message = "La fecha de nacimiento debe ser en el pasado")
        @NotNull(message = "La fecha de nacimiento es requerida")
        LocalDate fechaNacimiento
) {}
