package com.concell.system.mapeadores.requests;

import com.concell.system.modelos.Estado;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public record CompraRequest(
        @NotNull(message = "La fecha no puede ser nula")
        @PastOrPresent(message = "La fecha debe ser hoy o en el pasado")
        LocalDate fecha,

        @NotBlank(message = "El tipo de compra no puede estar vacío")
        @Size(max = 50, message = "El tipo de compra no puede exceder los 50 caracteres")
        String tipoCompra,

        @NotBlank(message = "El nombre de compra no puede estar vacío")
        @Size(max = 100, message = "El nombre de compra no puede exceder los 100 caracteres")
        String nombreCompra,

        @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
        String descripcion,

        @NotNull(message = "El estado no puede ser nulo")
        Estado estado,

        @NotBlank(message = "El nombre del proveedor no puede estar vacío")
        Integer idProveedor,

        Integer idUsuario,

        List<ProductoCompradoRequest> productosComprados
) {}
