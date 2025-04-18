package com.concell.system.controladores;

import com.concell.system.mapeadores.requests.ProveedorRequest;
import com.concell.system.mapeadores.responses.ProveedorResponse;
import com.concell.system.servicios.ProveedorServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

  private final ProveedorServicio proveedorServicio;

  public ProveedorController(ProveedorServicio proveedorServicio) {
    this.proveedorServicio = proveedorServicio;
  }

  @GetMapping
  public ResponseEntity<List<ProveedorResponse>> obtenerProveedores() {
    List<ProveedorResponse> proveedores = proveedorServicio
            .obtenerProveedores()
            .stream()
            .map(proveedor -> new ProveedorResponse(
                    proveedor.getIdProveedor(),
                    proveedor.getNit(),
                    proveedor.getNombre(),
                    proveedor.getApellidoPaterno(),
                    proveedor.getApellidoMaterno(),
                    proveedor.getContacto(),
                    proveedor.getTipoProducto(),
                    proveedor.getEstado())
            ).toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(proveedores);
  }

  @GetMapping("/{idProveedor}")
  public ResponseEntity<ProveedorResponse> obtenerProveedorPorId(
          @PathVariable("idProveedor") Integer idProveedor) {
    ProveedorResponse response = proveedorServicio.obtenerProveedorPorId(idProveedor);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
  }

  @GetMapping("/buscar")
  public ResponseEntity<List<ProveedorResponse>> buscarProveedoresPorNombre(
          @RequestParam String nombre) {
    List<ProveedorResponse> proveedores = proveedorServicio
            .buscarProveedoresPorNombre(nombre)
            .stream()
            .map(proveedor -> new ProveedorResponse(
                    proveedor.getIdProveedor(),
                    proveedor.getNit(),
                    proveedor.getNombre(),
                    proveedor.getApellidoPaterno(),
                    proveedor.getApellidoMaterno(),
                    proveedor.getContacto(),
                    proveedor.getTipoProducto(),
                    proveedor.getEstado())
            ).toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(proveedores);
  }

  @PostMapping
  public ResponseEntity<ProveedorResponse> crearProveedor(
          @RequestBody ProveedorRequest request) {
    ProveedorResponse response = proveedorServicio.crearProveedor(request);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
  }

  @PutMapping("/{idProveedor}")
  public ResponseEntity<ProveedorResponse> actualizarProveedor(
          @PathVariable("idProveedor") Integer idProveedor,
          @RequestBody ProveedorRequest request) {
    ProveedorResponse response = proveedorServicio
            .actualizarProveedor(idProveedor, request);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
  }

  @DeleteMapping("/{idProveedor}")
  public ResponseEntity<Void> eliminarProveedor(
          @PathVariable("idProveedor") Integer idProveedor) {
    proveedorServicio.eliminarProveedor(idProveedor);

    return ResponseEntity.noContent().build();
  }
}
