package com.concell.system.servicios;

import com.concell.system.modelos.Usuario;
import com.concell.system.repositorios.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicio {
  private final UsuarioRepositorio usuarioRepositorio;

  public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
    this.usuarioRepositorio = usuarioRepositorio;
  }

  public Optional<Usuario> findByEmail(String email) {
    return usuarioRepositorio.findByEmail(email);
  }
}
