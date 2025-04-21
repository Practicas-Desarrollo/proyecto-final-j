package com.concell.system.mapeadores.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record ClienteRequest(
        @NotBlank(message = "El NIT no puede estar vacío")
        @Size(max = 20, message = "El NIT no puede exceder los 20 caracteres")
        @Pattern(regexp = "^[0-9]+$", message = "El NIT solo debe contener números")
        String nit,

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 25, message = "El nombre no puede exceder los 25 caracteres")
        @Pattern(regexp = "^[\\p{L} .'-]+$", message = "El nombre solo debe contener letras y espacios")
        String nombre,

        @NotBlank(message = "El apellido paterno no puede estar vacío")
        @Size(max = 25, message = "El apellido paterno no puede exceder los 25 caracteres")
        @Pattern(regexp = "^[\\p{L} .'-]+$", message = "El apellido paterno solo debe contener letras y espacios")
        String apellidoPaterno,

        @Size(max = 25, message = "El apellido materno no puede exceder los 25 caracteres")
        @Pattern(regexp = "^[\\p{L} .'-]*$", message = "El apellido materno solo debe contener letras y espacios")
        String apellidoMaterno,

        @Size(max = 50, message = "La dirección no puede exceder los 50 caracteres")
        @Pattern(
                regexp = "^(?!.*\\d{5,})[\\p{L}0-9 .,'°#-\\/ªºª&-]+(?:\\s*[\\p{L}0-9 .,'°#-\\/ªºª&-]+)*$",
                message = "La dirección contiene caracteres no permitidos"
        )
        String direccion,

        @NotBlank(message = "El teléfono no puede estar vacío")
        @Pattern(regexp = "^[0-9]{7,15}$", message = "El teléfono debe contener solo números (7-15 dígitos)")
        String telefono,

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email debe tener un formato válido")
        @Size(max = 30, message = "El email no puede exceder los 30 caracteres")
        String email
) {}
