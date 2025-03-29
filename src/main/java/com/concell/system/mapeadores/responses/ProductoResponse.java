package com.concell.system.mapeadores.responses;

import com.concell.system.modelos.Estado;

import java.math.BigDecimal;

public record ProductoResponse(
        Integer idProducto,
        String nombre,
        String descripcion,
        BigDecimal precio,
        Integer cantidad,
        Estado estado,
        Integer idCategoria
) {}
