package com.concell.system.controladores;

import com.concell.system.modelos.Proveedor;
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
  public ResponseEntity<List<Proveedor>> listarProveedores() {
    List<Proveedor> proveedores = proveedorServicio.listarProveedores();
    return ResponseEntity.ok(proveedores);
  }

  @PostMapping
  public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor proveedor) {
    Proveedor nuevoProveedor = proveedorServicio.crearProveedor(proveedor);
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProveedor);
  }

  @GetMapping("/activos")
  public ResponseEntity<List<Proveedor>> obtenerProveedoresActivos() {
    List<Proveedor> proveedoresActivos = proveedorServicio.obtenerProveedoresActivos();
    return ResponseEntity.ok(proveedoresActivos);
  }

  @GetMapping("/tipo/{tipoProducto}")
  public ResponseEntity<List<Proveedor>> obtenerProveedoresPorTipoProducto(
          @PathVariable String tipoProducto) {
    List<Proveedor> proveedores = proveedorServicio
            .obtenerProveedoresPorTipoProducto(tipoProducto);

    return ResponseEntity.ok(proveedores);
  }

  @PutMapping("/{idProveedor}")
  public ResponseEntity<Proveedor> actualizarProveedor(
          @PathVariable Integer idProveedor,
          @RequestBody Proveedor proveedor) {
    proveedor.setIdProveedor(idProveedor);
    Proveedor proveedorActualizado = proveedorServicio.actualizarProveedor(proveedor);

    return ResponseEntity.ok(proveedorActualizado);
  }

  @DeleteMapping("/{idProveedor}")
  public ResponseEntity<Void> eliminarProveedor(@PathVariable Integer idProveedor) {
    proveedorServicio.eliminarProveedor(idProveedor);
    return ResponseEntity.noContent().build();
  }
}
