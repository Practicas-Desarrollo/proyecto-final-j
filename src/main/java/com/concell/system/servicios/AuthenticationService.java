package com.concell.system.servicios;

import com.concell.system.mapeadores.requests.LoginRequest;
import com.concell.system.mapeadores.requests.RegisterRequest;
import com.concell.system.mapeadores.responses.AuthResponse;
import com.concell.system.modelos.Usuario;
import com.concell.system.repositorios.UsuarioRepositorio;
import com.sun.jdi.InternalException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private final UsuarioRepositorio usuarioRepositorio;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthenticationService(UsuarioRepositorio usuarioRepositorio,
                               AuthenticationManager authenticationManager,
                               PasswordEncoder passwordEncoder,
                               JwtService jwtService) {
    this.usuarioRepositorio = usuarioRepositorio;
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }


  public AuthResponse registerUser(RegisterRequest registerRequest) {
    Usuario newUser = Usuario
            .builder()
//            .fullName(registerUserDto.fullName())
            .email(registerRequest.email())
            .password(passwordEncoder.encode(registerRequest.password()))
            .build();

    usuarioRepositorio.save(newUser);
    String token = jwtService.crearToken(newUser);

    return AuthResponse
            .builder()
            .message("Usuario registrado exitosamente!")
            .token(token)
            .build();
  }

  public AuthResponse loginUser(LoginRequest loginRequest) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequest.email(),
                    loginRequest.password())
    );

    UserDetails userDetails = usuarioRepositorio
            .findByEmail(loginRequest.email())
            .orElseThrow(()-> new InternalException("No se encontro al usuario"));

    String token = jwtService.crearToken(userDetails);

    return AuthResponse
            .builder()
            .message("Usuario logeado con exito!")
            .token(token)
            .build();
  }
}
