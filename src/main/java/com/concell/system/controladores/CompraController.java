package com.concell.system.controladores;

import com.concell.system.modelos.Compra;
import com.concell.system.servicios.CompraServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {
  private final CompraServicio compraServicio;

  public CompraController(CompraServicio compraServicio) {
    this.compraServicio = compraServicio;
  }

  @GetMapping
  public ResponseEntity<List<Compra>> listarCompras() {
    List<Compra> compras = compraServicio.listarCompras();
    return ResponseEntity.ok(compras);
  }

  @PostMapping
  public ResponseEntity<Compra> crearCompra(@RequestBody Compra compra) {
    Compra nuevaCompra = compraServicio.crearCompra(compra);
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCompra);
  }

  @GetMapping("/activas")
  public ResponseEntity<List<Compra>> obtenerComprasActivas() {
    List<Compra> comprasActivas = compraServicio.obtenerComprasActivas();
    return ResponseEntity.ok(comprasActivas);
  }

  @GetMapping("/fecha")
  public ResponseEntity<List<Compra>> obtenerComprasPorFecha(
          @RequestParam LocalDate startDate,
          @RequestParam LocalDate endDate) {
    List<Compra> compras = compraServicio.obtenerComprasPorFecha(startDate, endDate);
    return ResponseEntity.ok(compras);
  }

  @PutMapping("/{idCompra}")
  public ResponseEntity<Compra> actualizarCompra(
          @PathVariable Integer idCompra,
          @RequestBody Compra compra) {
    compra.setIdCompra(idCompra);
    Compra compraActualizada = compraServicio.actualizarCompra(compra);
    return ResponseEntity.ok(compraActualizada);
  }

  @DeleteMapping("/{idCompra}")
  public ResponseEntity<Void> eliminarCompra(@PathVariable Integer idCompra) {
    compraServicio.eliminarCompra(idCompra);
    return ResponseEntity.noContent().build();
  }
}
