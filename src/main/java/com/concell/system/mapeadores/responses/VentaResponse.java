package com.concell.system.mapeadores.responses;

import com.concell.system.modelos.Estado;
import com.concell.system.modelos.TipoPago;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record VentaResponse(
        Integer idVenta,
        LocalDate fecha,
        String descripcionGarantia,
        TipoPago tipoPago,
        BigDecimal total,
        Estado estado,
        Integer idUsuario,
        String nombreUsuario,
        Integer idCliente,
        String nombreCliente,
        List<ProductoVendidoResponse> productosVendidos
) {}
