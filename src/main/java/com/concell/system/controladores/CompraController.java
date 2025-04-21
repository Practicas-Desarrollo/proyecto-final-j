package com.concell.system.controladores;

import com.concell.system.mapeadores.requests.CompraRequest;
import com.concell.system.mapeadores.responses.CompraResponse;
import com.concell.system.mapeadores.responses.ProductoCompradoResponse;
import com.concell.system.servicios.CompraServicio;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/compras")
@PreAuthorize("hasAuthority('Administrador') or hasAuthority('Propietario de la tienda') or hasAuthority('Vendedor')")
public class CompraController {
  private final CompraServicio compraServicio;

  public CompraController(CompraServicio compraServicio) {
    this.compraServicio = compraServicio;
  }

  @GetMapping
  public ResponseEntity<List<CompraResponse>> obtenerCompras() {
    List<CompraResponse> compras = compraServicio
            .obtenerCompras()
            .stream()
            .map(compra -> new CompraResponse(
                    compra.getIdCompra(),
                    compra.getFecha(),
                    compra.getTipoCompra(),
                    compra.getNombreCompra(),
                    compra.getDescripcion(),
                    compra.getCostoTotal(),
                    compra.getEstado(),
                    compra.getProveedor().getNombre(),
                    compra.getUsuario().getEmail(),
                    compra.getProductosComprados()
                            .stream()
                            .map(pc -> new ProductoCompradoResponse(
                                    pc.getProducto().getIdProducto(),
                                    pc.getProducto().getNombre(),
                                    pc.getCantidad(),
                                    pc.getPrecioUnitario()
                            ))
                            .collect(Collectors.toList())
            ))
            .toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(compras);
  }

  @GetMapping("/{idCompra}")
  public ResponseEntity<CompraResponse> obtenerCompraPorId(
          @PathVariable("idCompra") Integer idCompra) {
    CompraResponse response = compraServicio.obtenerCompraPorId(idCompra);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
  }

  @GetMapping("/activas")
  public ResponseEntity<List<CompraResponse>> obtenerComprasActivas() {
    List<CompraResponse> comprasActivas = compraServicio
            .obtenerComprasActivas()
            .stream()
            .map(compra -> new CompraResponse(
                    compra.getIdCompra(),
                    compra.getFecha(),
                    compra.getTipoCompra(),
                    compra.getNombreCompra(),
                    compra.getDescripcion(),
                    compra.getCostoTotal(),
                    compra.getEstado(),
                    compra.getProveedor().getNombre(),
                    compra.getUsuario().getEmail(),
                    compra.getProductosComprados()
                            .stream()
                            .map(pc -> new ProductoCompradoResponse(
                                    pc.getProducto().getIdProducto(),
                                    pc.getProducto().getNombre(),
                                    pc.getCantidad(),
                                    pc.getPrecioUnitario()
                            ))
                            .collect(Collectors.toList())
            ))
            .toList();
    return ResponseEntity.ok(comprasActivas);
  }

  @GetMapping("/fecha")
  public ResponseEntity<List<CompraResponse>> obtenerComprasPorFecha(
          @RequestParam
          @DateTimeFormat(pattern = "dd/MM/yyyy")
          LocalDate startDate,
          @RequestParam
          @DateTimeFormat(pattern = "dd/MM/yyyy")
          LocalDate endDate) {
    List<CompraResponse> compras = compraServicio
            .obtenerComprasPorFecha(startDate, endDate)
            .stream()
            .map(compra -> new CompraResponse(
                    compra.getIdCompra(),
                    compra.getFecha(),
                    compra.getTipoCompra(),
                    compra.getNombreCompra(),
                    compra.getDescripcion(),
                    compra.getCostoTotal(),
                    compra.getEstado(),
                    compra.getProveedor().getNombre(),
                    compra.getUsuario().getEmail(),
                    compra.getProductosComprados()
                            .stream()
                            .map(pc -> new ProductoCompradoResponse(
                                    pc.getProducto().getIdProducto(),
                                    pc.getProducto().getNombre(),
                                    pc.getCantidad(),
                                    pc.getPrecioUnitario()
                            ))
                            .collect(Collectors.toList())
            ))
            .toList();
    return ResponseEntity.ok(compras);
  }

  @PostMapping
  public ResponseEntity<CompraResponse> crearCompra(@RequestBody CompraRequest request) {
    CompraResponse response = compraServicio.crearCompra(request);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
  }

  @PutMapping("/{idCompra}")
  public ResponseEntity<CompraResponse> actualizarCompra(
          @PathVariable("idCompra") Integer idCompra,
          @RequestBody CompraRequest request) {
    CompraResponse response = compraServicio.actualizarCompra(idCompra, request);

    return ResponseEntity.ok(response);
  }

  @PutMapping("/{idCompra}/estado")
  public ResponseEntity<CompraResponse> actualizarEstadoCompra(
          @PathVariable("idCompra") int idCompra) {
    CompraResponse compraResponse =
            compraServicio.actualizarEstadoCompra(idCompra);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(compraResponse);
  }

  @DeleteMapping("/{idCompra}")
  public ResponseEntity<Void> eliminarCompra(
          @PathVariable("idCompra") Integer idCompra) {
    compraServicio.eliminarCompra(idCompra);

    return ResponseEntity.noContent().build();
  }
}
