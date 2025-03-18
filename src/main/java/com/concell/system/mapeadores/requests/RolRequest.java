package com.concell.system.mapeadores.requests;

import com.concell.system.modelos.Estado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record RolRequest(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
        String nombre,

        Estado estado
) {}
