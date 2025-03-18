package com.concell.system.mapeadores.responses;

import com.concell.system.modelos.Estado;

public record RolResponse(
        Integer idRol,
        String nombre,
        Estado estado
) {}
