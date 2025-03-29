package com.concell.system.mapeadores.responses;

import com.concell.system.modelos.Estado;

import java.util.List;

public record CategoriaResponse(
        Integer idCategoria,
        String nombre,
        Estado estado,
        List<ProductoResponse> productos
) {}
