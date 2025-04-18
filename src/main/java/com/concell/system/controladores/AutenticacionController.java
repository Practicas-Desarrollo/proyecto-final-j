package com.concell.system.controladores;

import com.concell.system.mapeadores.requests.LoginRequest;
import com.concell.system.mapeadores.requests.RegistroRequest;
import com.concell.system.servicios.AutenticacionServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {

  private final AutenticacionServicio authenticationService;

  public AutenticacionController(AutenticacionServicio autenticacionServicio) {
    this.authenticationService = autenticacionServicio;
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUsuario(@RequestBody LoginRequest loginRequest){
    return ResponseEntity.ok(authenticationService.loginUsuario(loginRequest));
  }


  @PostMapping("/register")
  public ResponseEntity<?> registrarUsuario(@RequestBody RegistroRequest registerRequest){
    return ResponseEntity.ok(authenticationService.registrarUsuario(registerRequest));
  }
}
