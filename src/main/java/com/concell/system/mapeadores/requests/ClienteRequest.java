package com.concell.system.mapeadores.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record ClienteRequest(
        @NotBlank(message = "El NIT no puede estar vacío")
        @Size(max = 20, message = "El NIT no puede exceder los 20 caracteres")
        String nit,

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 25, message = "El nombre no puede exceder los 25 caracteres")
        String nombre,

        @NotBlank(message = "El apellido paterno no puede estar vacío")
        @Size(max = 25, message = "El apellido paterno no puede exceder los 25 caracteres")
        String apellidoPaterno,

        @Size(max = 25, message = "El apellido materno no puede exceder los 25 caracteres")
        String apellidoMaterno,

        @Size(max = 50, message = "La dirección no puede exceder los 50 caracteres")
        String direccion,

        @Pattern(regexp = "^[0-9]{7,15}$", message = "El teléfono debe contener solo números (7-15 dígitos)")
        String telefono,

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email debe tener un formato válido")
        @Size(max = 20, message = "El email no puede exceder los 20 caracteres")
        String email
) {}
