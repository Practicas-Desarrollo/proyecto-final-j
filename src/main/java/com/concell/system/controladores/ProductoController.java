package com.concell.system.controladores;

import com.concell.system.mapeadores.requests.ProductoRequest;
import com.concell.system.mapeadores.responses.ProductoResponse;
import com.concell.system.modelos.Producto;
import com.concell.system.servicios.ProductoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

  private final ProductoServicio productoServicio;

  public ProductoController(ProductoServicio productoServicio) {
    this.productoServicio = productoServicio;
  }

  @GetMapping
  public ResponseEntity<List<ProductoResponse>> obtenerProductos() {
    List<ProductoResponse> productos = productoServicio
            .obtenerProductos()
            .stream()
            .map(producto -> new ProductoResponse(
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getCantidad(),
                    producto.getEstado(),
                    producto.getCategoria() != null ? producto.getCategoria().getIdCategoria() : null
            ))
            .toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(productos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductoResponse> obtenerProductoPorId(
          @PathVariable("id") Integer idProducto) {
    Producto producto = productoServicio.obtenerProductoPorId(idProducto);

    ProductoResponse response = new ProductoResponse(
            producto.getIdProducto(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getCantidad(),
            producto.getEstado(),
            producto.getCategoria() != null ? producto.getCategoria().getIdCategoria() : null
    );

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
  }

  @GetMapping("/activos")
  public ResponseEntity<List<ProductoResponse>> obtenerProductosActivos() {
    List<ProductoResponse> productos = productoServicio
            .obtenerProductosActivos()
            .stream()
            .map(producto -> new ProductoResponse(
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getCantidad(),
                    producto.getEstado(),
                    producto.getCategoria() != null ? producto.getCategoria().getIdCategoria() : null
            ))
            .toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(productos);
  }

  @GetMapping("/buscar")
  public ResponseEntity<List<ProductoResponse>> buscarProductosPorNombre(
          @RequestParam String nombre) {
    List<ProductoResponse> productos = productoServicio.
            buscarProductosPorNombre(nombre)
            .stream()
            .map(producto -> new ProductoResponse(
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getCantidad(),
                    producto.getEstado(),
                    producto.getCategoria() != null ? producto.getCategoria().getIdCategoria() : null
            ))
            .toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(productos);
  }

  public ResponseEntity<List<ProductoResponse>> buscarProductosPorRangoDePrecio(
          @RequestParam BigDecimal minPrecio,
          @RequestParam BigDecimal maxPrecio) {
    List<ProductoResponse> productos = productoServicio
            .buscarProductosPorRangoDePrecio(minPrecio, maxPrecio)
            .stream()
            .map(producto -> new ProductoResponse(
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getCantidad(),
                    producto.getEstado(),
                    producto.getCategoria() != null ? producto.getCategoria().getIdCategoria() : null
            ))
            .toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(productos);
  }

  @PostMapping
  public ResponseEntity<ProductoResponse> crearProducto(
          @RequestBody ProductoRequest productoRequest) {
    ProductoResponse productoResponse = productoServicio.crearProducto(productoRequest);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(productoResponse);
  }

  @PutMapping("/{idProducto}")
  public ResponseEntity<ProductoResponse> actualizarProducto(
          @PathVariable("idProducto") Integer idProducto,
          @RequestBody ProductoRequest productoRequest) {
    ProductoResponse productoResponse =
            productoServicio.actualizarProducto(idProducto, productoRequest);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(productoResponse);
  }

  @PutMapping("/{idProducto}/estado")
  public ResponseEntity<ProductoResponse> actualizarEstadoProducto(
          @PathVariable("idProducto") int idProducto) {
    ProductoResponse productoResponse =
            productoServicio.actualizarEstadoProducto(idProducto);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(productoResponse);
  }

  @DeleteMapping("/{idProducto}")
  public ResponseEntity<Void> eliminarProducto(
          @PathVariable("idProducto") Integer idProducto) {
    productoServicio.eliminarProducto(idProducto);

    return ResponseEntity.noContent().build();
  }
}
