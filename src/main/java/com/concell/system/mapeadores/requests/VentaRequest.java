package com.concell.system.mapeadores.requests;

import com.concell.system.modelos.Estado;
import com.concell.system.modelos.TipoPago;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public record VentaRequest(
        @NotNull(message = "La fecha no puede ser nula")
        @PastOrPresent(message = "La fecha debe ser hoy o en el pasado")
        LocalDate fecha,

        @Size(max = 500, message = "La descripción de garantía no puede exceder los 500 caracteres")
        String descripcionGarantia,

        @NotNull(message = "El tipo de pago no puede ser nulo")
        TipoPago tipoPago,

        @NotNull(message = "El estado no puede ser nulo")
        Estado estado,

        @NotNull(message = "El ID del usuario no puede ser nulo")
        @Positive(message = "El ID del usuario debe ser positivo")
        Integer idUsuario,

        @NotNull(message = "El ID del cliente no puede ser nulo")
        @Positive(message = "El ID del cliente debe ser positivo")
        Integer idCliente,

        List<ProductoVendidoRequest> productosVendidos
) {}
