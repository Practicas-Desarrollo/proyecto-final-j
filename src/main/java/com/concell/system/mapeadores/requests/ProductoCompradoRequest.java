package com.concell.system.mapeadores.requests;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public record ProductoCompradoRequest(
        @NotNull(message = "El ID del producto no puede ser nulo")
        @Positive(message = "El ID del producto debe ser positivo")
        Integer idProducto,

        @NotNull(message = "La cantidad no puede ser nula")
        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        Integer cantidad,

        @NotNull(message = "El precio unitario no puede ser nulo")
        @DecimalMin(value = "0.01", message = "El precio unitario debe ser mayor que 0")
        BigDecimal precioUnitario
) {}
