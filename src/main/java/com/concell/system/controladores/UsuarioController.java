package com.concell.system.controladores;

import com.concell.system.mapeadores.requests.DetalleUsuarioRequest;
import com.concell.system.mapeadores.responses.DetalleUsuarioResponse;
import com.concell.system.mapeadores.responses.UsuarioResponse;
import com.concell.system.modelos.Usuario;
import com.concell.system.servicios.UsuarioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@PreAuthorize("hasAuthority('Administrador') or hasAuthority('Propietario de la tienda')")
public class UsuarioController {

  private final UsuarioServicio usuarioServicio;

  public UsuarioController(UsuarioServicio usuarioServicio) {
    this.usuarioServicio = usuarioServicio;
  }

  @GetMapping
  public ResponseEntity<List<UsuarioResponse>> obtenerUsuarios() {
    List<UsuarioResponse> usuarioResponses = usuarioServicio
            .obtenerUsuarios()
            .stream()
            .map( usuario -> new UsuarioResponse(
                    usuario.getIdUsuario(),
                    usuario.getEmail(),
                    usuario.getEstado(),
                    usuario.getRol().getIdRol(),
                    usuario.getDetalleUsuario() != null ?
                            new DetalleUsuarioResponse(
                                    usuario.getIdUsuario(),
                                    usuario.getDetalleUsuario().getFoto(),
                                    usuario.getDetalleUsuario().getNombreUsuario(),
                                    usuario.getDetalleUsuario().getNombre(),
                                    usuario.getDetalleUsuario().getApellidoPaterno(),
                                    usuario.getDetalleUsuario().getApellidoMaterno(),
                                    usuario.getDetalleUsuario().getFechaNacimiento()
                            ) : null
            ))
            .toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(usuarioResponses);
  }

  @GetMapping("/{idUsuario}")
  public ResponseEntity<UsuarioResponse> obtenerUsuarioPorId(
          @PathVariable("idUsuario") Integer idUsuario) {
    Usuario usuario = usuarioServicio.obtenerUsuarioPorId(idUsuario);

    UsuarioResponse usuarioResponse = new UsuarioResponse(
            usuario.getIdUsuario(),
            usuario.getEmail(),
            usuario.getEstado(),
            usuario.getRol().getIdRol(),
            usuario.getDetalleUsuario() != null ?
                    new DetalleUsuarioResponse(
                            usuario.getIdUsuario(),
                            usuario.getDetalleUsuario().getFoto(),
                            usuario.getDetalleUsuario().getNombreUsuario(),
                            usuario.getDetalleUsuario().getNombre(),
                            usuario.getDetalleUsuario().getApellidoPaterno(),
                            usuario.getDetalleUsuario().getApellidoMaterno(),
                            usuario.getDetalleUsuario().getFechaNacimiento()
                    ) : null
    );

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(usuarioResponse);
  }

  @GetMapping("/informacion")
  @PreAuthorize("hasAuthority('Administrador') or hasAuthority('Propietario de la tienda') or hasAuthority('Vendedor')")
  public ResponseEntity<UsuarioResponse> obtenerInformacionUsuario() {
    Authentication authentication = SecurityContextHolder
            .getContext()
            .getAuthentication();
    String email = authentication.getName();

    UsuarioResponse usuario = usuarioServicio
            .obtenerInformacionUsuario(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    return ResponseEntity.ok(usuario);
  }

  @GetMapping("/activos")
  public ResponseEntity<List<UsuarioResponse>> obtenerUsuariosActivos() {
    List<UsuarioResponse> usuarioResponses = usuarioServicio
            .obtenerUsuariosActivos()
            .stream()
            .map( usuario -> new UsuarioResponse(
                    usuario.getIdUsuario(),
                    usuario.getEmail(),
                    usuario.getEstado(),
                    usuario.getRol().getIdRol(),
                    usuario.getDetalleUsuario() != null ?
                            new DetalleUsuarioResponse(
                                    usuario.getIdUsuario(),
                                    usuario.getDetalleUsuario().getFoto(),
                                    usuario.getDetalleUsuario().getNombreUsuario(),
                                    usuario.getDetalleUsuario().getNombre(),
                                    usuario.getDetalleUsuario().getApellidoPaterno(),
                                    usuario.getDetalleUsuario().getApellidoMaterno(),
                                    usuario.getDetalleUsuario().getFechaNacimiento()
                            ) : null
            ))
            .toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(usuarioResponses);
  }

  @PutMapping("/{idUsuario}/estado")
  public ResponseEntity<UsuarioResponse> actualizarEstadoUsuario(
          @PathVariable("idUsuario") int idUsuario) {
    UsuarioResponse usuarioResponse =
            usuarioServicio.actualizarEstadoUsuario(idUsuario);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(usuarioResponse);
  }

  @PutMapping("/{idUsuario}/detalle")
  public ResponseEntity<UsuarioResponse> actualizarDetalleUsuario(
          @PathVariable("idUsuario") Integer idUsuario,
          @Valid @RequestBody DetalleUsuarioRequest request) {
    return ResponseEntity.ok(usuarioServicio.actualizarDetalleUsuario(idUsuario, request));
  }

  @PutMapping("/{idUsuario}/rol")
  public ResponseEntity<UsuarioResponse> actualizarRol(
          @PathVariable("idUsuario") Integer idUsuario,
          Integer idRol) {
    return ResponseEntity.ok(usuarioServicio.actualizarRol(idUsuario, idRol));
  }

  @DeleteMapping("/{idUsuario}")
  public ResponseEntity<Void> eliminarUsuario(
          @PathVariable("idUsuario") Integer idUsuario) {
    usuarioServicio.eliminarUsuario(idUsuario);

    return ResponseEntity.noContent().build();
  }
}
