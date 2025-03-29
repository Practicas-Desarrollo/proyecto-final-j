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
  private final JwtServicio jwtService;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;

  public AutenticacionServicio(UsuarioRepositorio usuarioRepositorio,
                                RolRepositorio rolRepositorio,
                                JwtServicio jwtService,
                                PasswordEncoder passwordEncoder,
                                AuthenticationManager authenticationManager
                                ) {
    this.usuarioRepositorio = usuarioRepositorio;
    this.rolRepositorio = rolRepositorio;
    this.jwtService = jwtService;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  public RegistroResponse registerUser(RegistroRequest registroRequest) {
    if (usuarioRepositorio.existsByEmail(registroRequest.email())) {
      throw new RuntimeException("El email ya está registrado");
    }

    Rol rol = rolRepositorio.findById(registroRequest.idRol())
            .orElseThrow(() -> new RuntimeException("Rol no válido"));

    Usuario newUser = Usuario
            .builder()
            .email(registroRequest.email())
            .password(passwordEncoder.encode(registroRequest.password()))
            .estado(registroRequest.estado())
            .rol(rol)
            .build();

    usuarioRepositorio.save(newUser);
    String token = jwtService.crearToken(newUser);

    return RegistroResponse
            .builder()
            .message("Usuario registrado exitosamente!")
            .token(token)
            .build();
  }

  public RegistroResponse loginUser(LoginRequest loginRequest) {
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

    String token = jwtService.crearToken(usuario);

    return RegistroResponse
            .builder()
            .message("Usuario logeado con exito!")
            .token(token)
            .build();
  }
}
