package com.concell.system.mapeadores.responses;

import com.concell.system.modelos.Estado;

public record ProveedorResponse(
        Integer idProveedor,
        String nombre,
        String apellidoPaterno,
        String apellidoMaterno,
        String nit,
        String contacto,
        String tipoProducto,
        Estado estado
) {}
