package com.concell.system.mapeadores.responses;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DetalleUsuarioResponse(
        Integer idUsuario,
        String foto,
        String nombreUsuario,
        String nombre,
        String apellidoPaterno,
        String apellidoMaterno,
        LocalDate fechaNacimiento
//        LocalDateTime fechaActualizacion
) {}
