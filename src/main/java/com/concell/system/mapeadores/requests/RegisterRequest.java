package com.concell.system.mapeadores.requests;

public record RegisterRequest(
        String email,
        String password
) {
}
