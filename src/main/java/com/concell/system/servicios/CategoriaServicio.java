package com.concell.system.servicios;

import com.concell.system.mapeadores.requests.CategoriaRequest;
import com.concell.system.mapeadores.requests.ProductoRequest;
import com.concell.system.mapeadores.responses.CategoriaResponse;
import com.concell.system.mapeadores.responses.ProductoResponse;
import com.concell.system.modelos.Categoria;
import com.concell.system.modelos.Estado;
import com.concell.system.modelos.Producto;
import com.concell.system.repositorios.CategoriaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServicio {
  private final CategoriaRepositorio categoriaRepositorio;

  public CategoriaServicio(CategoriaRepositorio categoriaRepositorio) {
    this.categoriaRepositorio = categoriaRepositorio;
  }

  public List<Categoria> obtenerCategorias() {
    return categoriaRepositorio.findAll();
  }

  public Categoria obtenerCategoriaPorId(Integer idCategoria) {
    return categoriaRepositorio
            .findById(idCategoria)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
  }

  public List<Categoria> obtenerCategoriasActivas() {
    return categoriaRepositorio.findByEstado(Estado.ACTIVO);
  }

  public List<Categoria> buscarCategoriasPorNombre(String nombre) {
    return categoriaRepositorio.findByNombreContainingIgnoreCase(nombre);
  }

  public CategoriaResponse crearCategoria(CategoriaRequest categoriaRequest) {
    Categoria categoria = new Categoria();
    categoria.setNombre(categoriaRequest.nombre());
    categoria.setEstado(categoriaRequest.estado());

    if (categoriaRequest.productos() != null && !categoriaRequest.productos().isEmpty()) {
      for (ProductoRequest productoRequest : categoriaRequest.productos()) {
        Producto producto = new Producto();
        producto.setNombre(productoRequest.nombre());
        producto.setDescripcion(productoRequest.descripcion());
        producto.setPrecio(productoRequest.precio());
        producto.setCantidad(productoRequest.cantidad());
        producto.setEstado(productoRequest.estado());
        producto.setCategoria(categoria);

        categoria.getProductos().add(producto);
      }
    }

    Categoria savedCategoria = categoriaRepositorio.save(categoria);

    return new CategoriaResponse(
            savedCategoria.getIdCategoria(),
            savedCategoria.getNombre(),
            savedCategoria.getEstado(),
            savedCategoria.getProductos().stream()
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
    );
  }

  public CategoriaResponse actualizarCategoria(Integer idCategoria, CategoriaRequest categoriaRequest) {
    Categoria categoria = obtenerCategoriaPorId(idCategoria);
    categoria.setNombre(categoriaRequest.nombre());
    categoria.setEstado(categoriaRequest.estado());

    if (categoriaRequest.productos() != null && !categoriaRequest.productos().isEmpty()) {
      for (ProductoRequest productoRequest : categoriaRequest.productos()) {
        Producto producto = new Producto();
        producto.setNombre(productoRequest.nombre());
        producto.setDescripcion(productoRequest.descripcion());
        producto.setPrecio(productoRequest.precio());
        producto.setCantidad(productoRequest.cantidad());
        producto.setEstado(productoRequest.estado());
        producto.setCategoria(categoria);

        categoria.getProductos().add(producto);
      }
    }

    Categoria updatedCategoria = categoriaRepositorio.save(categoria);

    return new CategoriaResponse(
            updatedCategoria.getIdCategoria(),
            updatedCategoria.getNombre(),
            updatedCategoria.getEstado(),
            updatedCategoria.getProductos().stream()
                    .map(producto -> new ProductoResponse(
                            producto.getIdProducto(),
                            producto.getNombre(),
                            producto.getDescripcion(),
                            producto.getPrecio(),
                            producto.getCantidad(),
                            producto.getEstado(),
                            updatedCategoria.getIdCategoria()
                    ))
                    .toList()
    );
  }

  public CategoriaResponse actualizarEstadoCategoria(int idCategoria) {
    Categoria categoria = categoriaRepositorio
            .findById(idCategoria)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

    Estado nuevoEstado = (categoria.getEstado() == Estado.ACTIVO) ? Estado.INACTIVO : Estado.ACTIVO;
    categoria.setEstado(nuevoEstado);

    Categoria updatedCategoria = categoriaRepositorio.save(categoria);
    return new CategoriaResponse(
            updatedCategoria.getIdCategoria(),
            updatedCategoria.getNombre(),
            updatedCategoria.getEstado(),
            updatedCategoria.getProductos().stream()
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
    );
  }

  public void eliminarCategoria(Integer idCategoria) {
    if (!categoriaRepositorio.existsById(idCategoria)) {
      throw new RuntimeException("Categoria no encontrado");
    }

    categoriaRepositorio.deleteById(idCategoria);
  }
}
