package com.concell.system.controladores;

import com.concell.system.mapeadores.requests.VentaRequest;
import com.concell.system.mapeadores.responses.ProductoVendidoResponse;
import com.concell.system.mapeadores.responses.VentaResponse;
import com.concell.system.modelos.Venta;
import com.concell.system.servicios.PdfGeneradorServicio;
import com.concell.system.servicios.VentaServicio;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ventas")
public class VentaController {

  private final VentaServicio ventaServicio;
  private final PdfGeneradorServicio pdfGeneratorService;

  public VentaController(VentaServicio ventaServicio, PdfGeneradorServicio pdfGeneradorServicio) {
    this.ventaServicio = ventaServicio;
    this.pdfGeneratorService = pdfGeneradorServicio;
  }

  @GetMapping
  public ResponseEntity<List<VentaResponse>> listarVentas() {
    List<VentaResponse> ventas = ventaServicio
            .obtenerVentas()
            .stream()
            .map(venta -> new VentaResponse(
                    venta.getIdVenta(),
                    venta.getFecha(),
                    venta.getDescripcionGarantia(),
                    venta.getTipoPago(),
                    venta.getTotal(),
                    venta.getEstado(),
                    venta.getUsuario().getIdUsuario(),
                    venta.getCliente().getIdCliente(),
                    venta.getProductosVendidos().stream()
                            .map(pv -> new ProductoVendidoResponse(
                                    pv.getProducto().getIdProducto(),
                                    pv.getProducto().getNombre(),
                                    pv.getCantidad(),
                                    pv.getPrecioUnitario()
                            ))
                            .collect(Collectors.toList())
            ))
            .toList();
    return ResponseEntity.ok(ventas);
  }

  @GetMapping("/{idVenta}")
  public ResponseEntity<VentaResponse> obtenerVentaPorId(
          @PathVariable("idVenta") Integer idVenta) {
    Venta venta = ventaServicio.obtenerVentaPorId(idVenta);

    VentaResponse ventaResponse = new VentaResponse(
            venta.getIdVenta(),
            venta.getFecha(),
            venta.getDescripcionGarantia(),
            venta.getTipoPago(),
            venta.getTotal(),
            venta.getEstado(),
            venta.getUsuario().getIdUsuario(),
            venta.getCliente().getIdCliente(),
            venta.getProductosVendidos().stream()
                    .map(pv -> new ProductoVendidoResponse(
                            pv.getProducto().getIdProducto(),
                            pv.getProducto().getNombre(),
                            pv.getCantidad(),
                            pv.getPrecioUnitario()
                    ))
                    .collect(Collectors.toList())
    );

    return ResponseEntity.ok(ventaResponse);
  }

  @GetMapping("/activas")
  public ResponseEntity<List<VentaResponse>> obtenerVentasActivas() {
    List<VentaResponse> ventasActivas = ventaServicio
            .obtenerVentasActivas()
            .stream()
            .map(venta -> new VentaResponse(
                    venta.getIdVenta(),
                    venta.getFecha(),
                    venta.getDescripcionGarantia(),
                    venta.getTipoPago(),
                    venta.getTotal(),
                    venta.getEstado(),
                    venta.getUsuario().getIdUsuario(),
                    venta.getCliente().getIdCliente(),
                    venta.getProductosVendidos().stream()
                            .map(pv -> new ProductoVendidoResponse(
                                    pv.getProducto().getIdProducto(),
                                    pv.getProducto().getNombre(),
                                    pv.getCantidad(),
                                    pv.getPrecioUnitario()
                            ))
                            .collect(Collectors.toList())
            ))
            .toList();
    return ResponseEntity.ok(ventasActivas);
  }

  @GetMapping("/fecha")
  public ResponseEntity<List<VentaResponse>> obtenerVentasPorFecha(
          @RequestParam
          @DateTimeFormat(pattern = "dd/MM/yyyy")
          LocalDate startDate,
          @RequestParam
          @DateTimeFormat(pattern = "dd/MM/yyyy")
          LocalDate endDate) {
    List<VentaResponse> ventas = ventaServicio
            .obtenerVentasPorFecha(startDate, endDate)
            .stream()
            .map(venta -> new VentaResponse(
                    venta.getIdVenta(),
                    venta.getFecha(),
                    venta.getDescripcionGarantia(),
                    venta.getTipoPago(),
                    venta.getTotal(),
                    venta.getEstado(),
                    venta.getUsuario().getIdUsuario(),
                    venta.getCliente().getIdCliente(),
                    venta.getProductosVendidos().stream()
                            .map(pv -> new ProductoVendidoResponse(
                                    pv.getProducto().getIdProducto(),
                                    pv.getProducto().getNombre(),
                                    pv.getCantidad(),
                                    pv.getPrecioUnitario()
                            ))
                            .collect(Collectors.toList())
            ))
            .toList();
    return ResponseEntity.ok(ventas);
  }

  @GetMapping("/{idVenta}/factura")
  public ResponseEntity<byte[]> generarFactura(@PathVariable Integer idVenta) {
    try {
      Venta venta = ventaServicio.obtenerVentaPorId(idVenta);
      byte[] pdfBytes = pdfGeneratorService.generateInvoice(venta);

      return ResponseEntity.ok()
              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=factura_" + idVenta + ".pdf")
              .contentType(MediaType.APPLICATION_PDF)
              .body(pdfBytes);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al generar la factura", e);
    }
  }

  @PostMapping
  public ResponseEntity<VentaResponse> crearVenta(@RequestBody VentaRequest request) {
    VentaResponse response = ventaServicio.crearVenta(request);

    return ResponseEntity.ok(response);
  }

  @PutMapping("/{idVenta}")
  public ResponseEntity<VentaResponse> actualizarVenta(
          @PathVariable("idVenta") Integer idVenta,
          @Valid @RequestBody VentaRequest request) {

    VentaResponse response = ventaServicio.actualizarVenta(idVenta, request);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{idVenta}")
  public ResponseEntity<Void> eliminarVenta(
          @PathVariable("idVenta") Integer idVenta) {
    ventaServicio.eliminarCompra(idVenta);

    return ResponseEntity.noContent().build();
  }
}
