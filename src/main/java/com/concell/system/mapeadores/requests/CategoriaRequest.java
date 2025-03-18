package com.concell.system.mapeadores.requests;

import com.concell.system.modelos.Estado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public record CategoriaRequest(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
        String nombre,

        Estado estado,

        List<ProductoRequest> productos
) {}
