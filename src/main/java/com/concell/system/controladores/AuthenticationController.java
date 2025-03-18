package com.concell.system.controladores;

import com.concell.system.mapeadores.requests.LoginRequest;
import com.concell.system.mapeadores.requests.RegisterRequest;
import com.concell.system.servicios.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUsuario(@RequestBody LoginRequest loginRequest){
    return ResponseEntity
            .ok(authenticationService.loginUser(loginRequest));
  }


  @PostMapping("/register")
  public ResponseEntity<?> registrarUsuario(@RequestBody RegisterRequest registerRequest){
    return ResponseEntity.
            ok(authenticationService.registerUser(registerRequest));
  }
}
