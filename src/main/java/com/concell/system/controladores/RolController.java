package com.concell.system.controladores;

import com.concell.system.mapeadores.requests.RolRequest;
import com.concell.system.mapeadores.responses.RolResponse;
import com.concell.system.modelos.Rol;
import com.concell.system.servicios.RolServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@PreAuthorize("hasAuthority('Administrador') or hasAuthority('Propietario de la tienda')")
public class RolController {

  private final RolServicio rolServicio;

  public RolController(RolServicio rolServicio) {
    this.rolServicio = rolServicio;
  }

  @GetMapping
  public ResponseEntity<List<RolResponse>> obtenerRoles() {
    List<RolResponse> roles = rolServicio
            .obtenerRoles()
            .stream()
            .map(rol -> new RolResponse(
                    rol.getIdRol(),
                    rol.getNombre(),
                    rol.getEstado()
            )).toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(roles);
  }

  @GetMapping("/{idRol}")
  public ResponseEntity<RolResponse> obtenerRolPorId(
          @PathVariable("idRol") Integer idRol) {
    Rol rol = rolServicio.obtenerRolPorId(idRol);

    RolResponse rolResponse = new RolResponse(
            rol.getIdRol(),
            rol.getNombre(),
            rol.getEstado()
    );

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(rolResponse);
  }

  @GetMapping("/activos")
  public ResponseEntity<List<RolResponse>> obtenerRolesActivos() {
    List<RolResponse> roles = rolServicio
            .obtenerRolesActivos()
            .stream()
            .map(rol -> new RolResponse(
                    rol.getIdRol(),
                    rol.getNombre(),
                    rol.getEstado()
            )).toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(roles);
  }

  @GetMapping("/buscar")
  public ResponseEntity<List<RolResponse>> buscarRolesPorNombre(
          @RequestParam String nombre) {
    List<RolResponse> roles = rolServicio
            .buscarRolesPorNombre(nombre)
            .stream()
            .map(rol -> new RolResponse(
                    rol.getIdRol(),
                    rol.getNombre(),
                    rol.getEstado()
            )).toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(roles);
  }

  @PostMapping
  public ResponseEntity<RolResponse> crearRol(@RequestBody RolRequest rolRequest) {
    RolResponse rolResponse = rolServicio.crearRol(rolRequest);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(rolResponse);
  }

  @PutMapping("/{idRol}")
  public ResponseEntity<RolResponse> actualizarRol(
          @PathVariable("idRol") int idRol,
          @RequestBody RolRequest rolRequest) {
    RolResponse rolResponse = rolServicio.actualizarRol(idRol, rolRequest);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(rolResponse);
  }

  @PutMapping("/{idRol}/estado")
  public ResponseEntity<RolResponse> actualizarEstadoRol(
          @PathVariable("idRol") int idRol) {
    RolResponse rolResponse = rolServicio.actualizarEstadoRol(idRol);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(rolResponse);
  }

  @DeleteMapping("/{idRol}")
  public ResponseEntity<Void> eliminarRol(@PathVariable("idRol") int idRol) {
    rolServicio.eliminarRol(idRol);
    return ResponseEntity.noContent().build();
  }
}
