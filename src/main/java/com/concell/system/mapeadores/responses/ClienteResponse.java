package com.concell.system.mapeadores.responses;

public record ClienteResponse(
        Integer idCliente,
        String nit,
        String nombre,
        String apellidoPaterno,
        String apellidoMaterno,
        String direccion,
        String telefono,
        String email
) {}
