package com.concell.system.mapeadores.requests;

import com.concell.system.modelos.Estado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record ProveedorRequest(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
        String nombre,

        @NotBlank(message = "El apellido paterno no puede estar vacío")
        @Size(max = 100, message = "El apellido paterno no puede exceder los 100 caracteres")
        String apellidoPaterno,

        @Size(max = 100, message = "El apellido materno no puede exceder los 100 caracteres")
        String apellidoMaterno,

        @NotBlank(message = "El NIT no puede estar vacío")
        @Size(max = 20, message = "El NIT no puede exceder los 20 caracteres")
        String nit,

        @NotBlank(message = "El contacto no puede estar vacío")
        @Size(max = 100, message = "El contacto no puede exceder los 100 caracteres")
        String contacto,

        @Size(max = 100, message = "El tipo de producto no puede exceder los 100 caracteres")
        String tipoProducto,

        @NotNull(message = "El estado no puede ser nulo")
        Estado estado
) {}
