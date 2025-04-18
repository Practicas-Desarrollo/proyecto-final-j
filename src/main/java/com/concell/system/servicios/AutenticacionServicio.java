package com.concell.system.servicios;

import com.concell.system.mapeadores.requests.LoginRequest;
import com.concell.system.mapeadores.requests.RegistroRequest;
import com.concell.system.mapeadores.responses.RegistroResponse;
import com.concell.system.modelos.Estado;
import com.concell.system.modelos.Rol;
import com.concell.system.modelos.Usuario;
import com.concell.system.repositorios.RolRepositorio;
import com.concell.system.repositorios.UsuarioRepositorio;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionServicio {

  private final UsuarioRepositorio usuarioRepositorio;
  private final RolRepositorio rolRepositorio;
  private final JwtServicio jwtServicio;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;

  public AutenticacionServicio(UsuarioRepositorio usuarioRepositorio,
                                RolRepositorio rolRepositorio,
                                JwtServicio jwtServicio,
                                PasswordEncoder passwordEncoder,
                                AuthenticationManager authenticationManager
                                ) {
    this.usuarioRepositorio = usuarioRepositorio;
    this.rolRepositorio = rolRepositorio;
    this.jwtServicio = jwtServicio;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  public RegistroResponse registrarUsuario(RegistroRequest registroRequest) {
    if (usuarioRepositorio.existsByEmail(registroRequest.email())) {
      throw new RuntimeException("El email ya está registrado");
    }

    Rol rol = rolRepositorio.findById(registroRequest.idRol())
            .orElseThrow(() -> new RuntimeException("Rol no válido"));

    Usuario newUser = new Usuario();
    newUser.setEmail(registroRequest.email());
    newUser.setPassword(passwordEncoder.encode(registroRequest.password()));
    newUser.setEstado(registroRequest.estado());
    newUser.setRol(rol);

    usuarioRepositorio.save(newUser);
    String token = jwtServicio.crearToken(newUser);

    RegistroResponse response = new RegistroResponse();
    response.setMessage("Usuario registrado exitosamente!");
    response.setToken(token);
    return response;
  }

  public RegistroResponse loginUsuario(LoginRequest loginRequest) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequest.email(),
                    loginRequest.password())
    );

    Usuario usuario = usuarioRepositorio.findByEmail(loginRequest.email())
            .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

    if (usuario.getEstado() != Estado.ACTIVO) {
      throw new IllegalStateException("Usuario no activo");
    }

    String token = jwtServicio.crearToken(usuario);

    RegistroResponse response = new RegistroResponse();
    response.setMessage("Usuario logeado con exito!");
    response.setToken(token);
    return response;
  }
}
