package com.concell.system.mapeadores.requests;

import com.concell.system.modelos.Estado;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public record ProductoRequest(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
        String nombre,

        @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
        String descripcion,

        @NotNull(message = "El precio no puede ser nulo")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor que 0.01")
        BigDecimal precio,

        @NotNull(message = "La cantidad no puede ser nula")
        @Min(value = 0, message = "La cantidad no puede ser negativa")
        Integer cantidad,

        Estado estado,

        Integer idCategoria
) {}
