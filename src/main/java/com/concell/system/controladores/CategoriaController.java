package com.concell.system.controladores;

import com.concell.system.mapeadores.requests.CategoriaRequest;
import com.concell.system.mapeadores.responses.CategoriaResponse;
import com.concell.system.mapeadores.responses.ProductoResponse;
import com.concell.system.modelos.Categoria;
import com.concell.system.servicios.CategoriaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

  private final CategoriaServicio categoriaServicio;

  public CategoriaController(CategoriaServicio categoriaServicio) {
    this.categoriaServicio = categoriaServicio;
  }

  @GetMapping
  public ResponseEntity<List<CategoriaResponse>> obtenerCategorias() {
    List<CategoriaResponse> categorias = categoriaServicio
            .obtenerCategorias()
            .stream()
            .map(categoria -> new CategoriaResponse(
                    categoria.getIdCategoria(),
                    categoria.getNombre(),
                    categoria.getEstado(),
                    categoria.getProductos().stream()
                            .map(producto -> new ProductoResponse(
                                    producto.getIdProducto(),
                                    producto.getNombre(),
                                    producto.getDescripcion(),
                                    producto.getPrecio(),
                                    producto.getCantidad(),
                                    producto.getEstado(),
                                    producto.getCategoria().getIdCategoria()
                            ))
                            .toList()
            ))
            .toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(categorias);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoriaResponse> obtenerCategoriaPorId(
          @PathVariable("id") Integer id) {
    Categoria categoria = categoriaServicio.obtenerCategoriaPorId(id);

    CategoriaResponse categoriaResponse = new CategoriaResponse(
            categoria.getIdCategoria(),
            categoria.getNombre(),
            categoria.getEstado(),
            categoria.getProductos().stream()
                    .map(producto -> new ProductoResponse(
                            producto.getIdProducto(),
                            producto.getNombre(),
                            producto.getDescripcion(),
                            producto.getPrecio(),
                            producto.getCantidad(),
                            producto.getEstado(),
                            producto.getCategoria().getIdCategoria()
                    )).toList()
    );

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(categoriaResponse);
  }

  @GetMapping("/activas")
  public ResponseEntity<List<CategoriaResponse>> obtenerCategoriasActivas() {
    List<CategoriaResponse> categorias = categoriaServicio
            .obtenerCategoriasActivas()
            .stream()
            .map(categoria -> new CategoriaResponse(
                    categoria.getIdCategoria(),
                    categoria.getNombre(),
                    categoria.getEstado(),
                    categoria.getProductos().stream()
                            .map(producto -> new ProductoResponse(
                                    producto.getIdProducto(),
                                    producto.getNombre(),
                                    producto.getDescripcion(),
                                    producto.getPrecio(),
                                    producto.getCantidad(),
                                    producto.getEstado(),
                                    producto.getCategoria().getIdCategoria()
                            )).toList()
            ))
            .toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(categorias);
  }

  @GetMapping("/buscar")
  public ResponseEntity<List<CategoriaResponse>> buscarCategoriasPorNombre(
          @RequestParam String nombre) {
    List<CategoriaResponse> categorias = categoriaServicio
            .buscarCategoriasPorNombre(nombre)
            .stream()
            .map(categoria -> new CategoriaResponse(
                    categoria.getIdCategoria(),
                    categoria.getNombre(),
                    categoria.getEstado(),
                    categoria.getProductos().stream()
                            .map(producto -> new ProductoResponse(
                                    producto.getIdProducto(),
                                    producto.getNombre(),
                                    producto.getDescripcion(),
                                    producto.getPrecio(),
                                    producto.getCantidad(),
                                    producto.getEstado(),
                                    producto.getCategoria().getIdCategoria()
                            )).toList()
            ))
            .toList();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(categorias);
  }

  @PostMapping
  public ResponseEntity<CategoriaResponse> crearCategoria(
          @RequestBody CategoriaRequest categoriaRequest) {
    CategoriaResponse nuevaCategoria =
            categoriaServicio.crearCategoria(categoriaRequest);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(nuevaCategoria);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoriaResponse> actualizarCategoria(
          @PathVariable("id") Integer idCategoria,
          @RequestBody CategoriaRequest categoriaRequest) {
    CategoriaResponse categoriaResponse =
            categoriaServicio.actualizarCategoria(idCategoria, categoriaRequest);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(categoriaResponse);
  }

  @PutMapping("/{idCategoria}/estado")
  public ResponseEntity<CategoriaResponse> actualizarEstadoCategoria(
          @PathVariable("idCategoria") int idCategoria) {
    CategoriaResponse categoriaResponse =
            categoriaServicio.actualizarEstadoCategoria(idCategoria);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(categoriaResponse);
  }

  @DeleteMapping("/{idCategoria}")
  public ResponseEntity<Void> eliminarCategoria(
          @PathVariable("idCategoria") Integer idCategoria) {
    categoriaServicio.eliminarCategoria(idCategoria);

    return ResponseEntity.noContent().build();
  }
}
