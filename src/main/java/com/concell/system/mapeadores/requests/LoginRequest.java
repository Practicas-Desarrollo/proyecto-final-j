package com.concell.system.mapeadores.requests;

public record LoginRequest(
        String email,
        String password
) {
}
