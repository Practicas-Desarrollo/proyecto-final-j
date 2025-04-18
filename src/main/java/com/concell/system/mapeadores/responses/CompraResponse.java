package com.concell.system.mapeadores.responses;

import com.concell.system.modelos.Estado;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record CompraResponse(
        Integer idCompra,
        LocalDate fecha,
        String tipoCompra,
        String nombreCompra,
        String descripcion,
        BigDecimal costo,
        Estado estado,
        String nombreProveedor,
        String nombreUsuario,
        List<ProductoCompradoResponse> productosComprados
) {}
