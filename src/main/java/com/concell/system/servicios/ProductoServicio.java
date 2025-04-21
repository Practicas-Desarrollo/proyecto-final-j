package com.concell.system.servicios;

import com.concell.system.mapeadores.requests.ProductoRequest;
import com.concell.system.mapeadores.responses.ProductoResponse;
import com.concell.system.modelos.Categoria;
import com.concell.system.modelos.Estado;
import com.concell.system.modelos.Producto;
import com.concell.system.repositorios.ProductoRepositorio;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductoServicio {

  private final ProductoRepositorio productoRepositorio;
  private final CategoriaServicio categoriaServicio;
  private static final int STOCK_BAJO = 5;

  public ProductoServicio(ProductoRepositorio productoRepositorio,
                          CategoriaServicio categoriaServicio) {
    this.productoRepositorio = productoRepositorio;
    this.categoriaServicio = categoriaServicio;
  }

  public List<Producto> obtenerProductos() {
    return productoRepositorio.findAll();
  }

  public Producto obtenerProductoPorId(Integer idProducto) {
    return productoRepositorio.findById(idProducto)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
  }

  public List<Producto> obtenerProductosActivos() {
    return productoRepositorio.findByEstado(Estado.ACTIVO);
  }

  public List<Producto> buscarProductosPorNombre(String nombre) {
    return productoRepositorio.findByNombreContainingIgnoreCase(nombre);
  }

  public List<Producto> buscarProductosPorRangoDePrecio(BigDecimal minPrecio, BigDecimal maxPrecio) {
    return productoRepositorio.findByPrecioBetween(minPrecio, maxPrecio);
  }

  public ProductoResponse crearProducto(ProductoRequest productoRequest) {
    Producto producto = new Producto();
    producto.setNombre(productoRequest.nombre());
    producto.setDescripcion(productoRequest.descripcion());
    producto.setPrecio(productoRequest.precio());
    producto.setCantidad(productoRequest.cantidad());
    producto.setEstado(productoRequest.estado());

    if (productoRequest.idCategoria() != null) {
      Categoria categoria = categoriaServicio
              .obtenerCategoriaPorId(productoRequest.idCategoria());
      producto.setCategoria(categoria);
    }

    Producto savedProducto = productoRepositorio.save(producto);

    return new ProductoResponse(
            savedProducto.getIdProducto(),
            savedProducto.getNombre(),
            savedProducto.getDescripcion(),
            savedProducto.getPrecio(),
            savedProducto.getCantidad(),
            savedProducto.getEstado(),
            savedProducto.getCategoria() != null ? savedProducto.getCategoria().getIdCategoria() : null
    );
  }

  public ProductoResponse actualizarProducto(
          Integer idProducto, ProductoRequest productoRequest) {
    Producto producto = productoRepositorio.findById(idProducto)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    producto.setNombre(productoRequest.nombre());
    producto.setDescripcion(productoRequest.descripcion());
    producto.setPrecio(productoRequest.precio());
    producto.setCantidad(productoRequest.cantidad());
    producto.setEstado(productoRequest.estado());

    if (productoRequest.idCategoria() != null) {
      Categoria categoria =
              categoriaServicio.obtenerCategoriaPorId(productoRequest.idCategoria());

      producto.setCategoria(categoria);
    }

    Producto updatedProducto = productoRepositorio.save(producto);

    return new ProductoResponse(
            updatedProducto.getIdProducto(),
            updatedProducto.getNombre(),
            updatedProducto.getDescripcion(),
            updatedProducto.getPrecio(),
            updatedProducto.getCantidad(),
            updatedProducto.getEstado(),
            updatedProducto.getCategoria() != null ? updatedProducto.getCategoria().getIdCategoria() : null
    );
  }

  public ProductoResponse actualizarEstadoProducto(int idProducto) {
    Producto producto = productoRepositorio.findById(idProducto)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    Estado nuevoEstado = (producto.getEstado() == Estado.ACTIVO) ? Estado.INACTIVO : Estado.ACTIVO;
    producto.setEstado(nuevoEstado);

    Producto updatedProducto = productoRepositorio.save(producto);
    return new ProductoResponse(
            updatedProducto.getIdProducto(),
            updatedProducto.getNombre(),
            updatedProducto.getDescripcion(),
            updatedProducto.getPrecio(),
            updatedProducto.getCantidad(),
            updatedProducto.getEstado(),
            updatedProducto.getCategoria() != null ? updatedProducto.getCategoria().getIdCategoria() : null
    );
  }

  public void eliminarProducto(Integer idProducto) {
    if (!productoRepositorio.existsById(idProducto)) {
      throw new RuntimeException("Producto no encontrado");
    }

    productoRepositorio.deleteById(idProducto);
  }
}
