package com.concell.system.mapeadores.responses;

import java.math.BigDecimal;

public record ProductoVendidoResponse(
        Integer idProducto,
        String nombreProducto,
        Integer cantidad,
        BigDecimal precioUnitario
) {}
