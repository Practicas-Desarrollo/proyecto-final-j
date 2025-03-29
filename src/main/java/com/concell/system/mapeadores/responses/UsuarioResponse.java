package com.concell.system.mapeadores.responses;

import com.concell.system.modelos.Estado;

public record UsuarioResponse(
        Integer idUsuario,
        String email,
        Estado estado,
        Integer idRol,
        DetalleUsuarioResponse detalleUsuario
) {}
