package com.concell.system.controladores;

import com.concell.system.modelos.Usuario;
import com.concell.system.repositorios.UsuarioRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  private UsuarioRepositorio usuarioRepositorio;

  public UsuarioController(UsuarioRepositorio usuarioRepositorio) {
    this.usuarioRepositorio = usuarioRepositorio;
  }

  @GetMapping
  public ResponseEntity<?> obtenerInformacionUsuario() {
    Authentication authentication = SecurityContextHolder
            .getContext()
            .getAuthentication();
    String email = authentication.getName();

    Usuario usuario = usuarioRepositorio
            .findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    return ResponseEntity.ok(usuario);
  }
}
