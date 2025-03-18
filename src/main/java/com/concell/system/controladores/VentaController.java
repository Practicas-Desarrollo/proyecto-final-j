package com.concell.system.controladores;

import com.concell.system.modelos.Venta;
import com.concell.system.servicios.VentaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

  private final VentaServicio ventaServicio;

  public VentaController(VentaServicio ventaServicio) {
    this.ventaServicio = ventaServicio;
  }

  @GetMapping
  public ResponseEntity<List<Venta>> listarVentas() {
    List<Venta> ventas = ventaServicio.listarVentas();
    return ResponseEntity.ok(ventas);
  }

  @PostMapping
  public ResponseEntity<Venta> crearVenta(@RequestBody Venta venta) {
    Venta nuevaVenta = ventaServicio.crearVenta(venta);
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
  }

  @GetMapping("/activas")
  public ResponseEntity<List<Venta>> obtenerVentasActivas() {
    List<Venta> ventasActivas = ventaServicio.obtenerVentasActivas();
    return ResponseEntity.ok(ventasActivas);
  }

  @GetMapping("/fecha")
  public ResponseEntity<List<Venta>> obtenerVentasPorFecha(
          @RequestParam LocalDate startDate,
          @RequestParam LocalDate endDate) {
    List<Venta> ventas = ventaServicio.obtenerVentasPorFecha(startDate, endDate);
    return ResponseEntity.ok(ventas);
  }

  @GetMapping("/cliente")
  public ResponseEntity<List<Venta>> buscarVentasPorCliente(
          @RequestParam String nombreCliente) {
    List<Venta> ventas = ventaServicio.buscarVentasPorCliente(nombreCliente);
    return ResponseEntity.ok(ventas);
  }

  @PutMapping("/{idVenta}")
  public ResponseEntity<Venta> actualizarVenta(
          @PathVariable Integer idVenta,
          @RequestBody Venta venta) {
    venta.setIdVenta(idVenta);
    Venta ventaActualizada = ventaServicio.actualizarVenta(venta);
    return ResponseEntity.ok(ventaActualizada);
  }

  @DeleteMapping("/{idVenta}")
  public ResponseEntity<Void> eliminarVenta(@PathVariable Integer idVenta) {
    ventaServicio.eliminarVenta(idVenta);
    return ResponseEntity.noContent().build();
  }
}
