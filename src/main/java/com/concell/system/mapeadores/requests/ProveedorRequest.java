package com.concell.system.mapeadores.requests;

import com.concell.system.modelos.Estado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record ProveedorRequest(
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

        @NotBlank(message = "El contacto no puede estar vacío")
        @Size(max = 30, message = "El contacto no puede exceder los 30 caracteres")
        String contacto,

        @Size(max = 25, message = "El tipo de producto no puede exceder los 25 caracteres")
        String tipoProducto,

        @NotNull(message = "El estado no puede ser nulo")
        Estado estado
) {}
