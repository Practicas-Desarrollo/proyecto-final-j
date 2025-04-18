package com.concell.system.servicios;

import com.concell.system.mapeadores.requests.CompraRequest;
import com.concell.system.mapeadores.requests.ProductoCompradoRequest;
import com.concell.system.mapeadores.responses.CompraResponse;
import com.concell.system.mapeadores.responses.ProductoCompradoResponse;
import com.concell.system.modelos.*;
import com.concell.system.repositorios.*;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraServicio {

  private final CompraRepositorio compraRepositorio;
  private final ProveedorRepositorio proveedorRepositorio;
  private final UsuarioRepositorio usuarioRepositorio;
  private final ProductoRepositorio productoRepositorio;

  public CompraServicio(CompraRepositorio compraRepositorio,
                        ProveedorRepositorio proveedorRepositorio,
                        UsuarioRepositorio usuarioRepositorio,
                        ProductoRepositorio productoRepositorio) {
    this.compraRepositorio = compraRepositorio;
    this.proveedorRepositorio = proveedorRepositorio;
    this.usuarioRepositorio = usuarioRepositorio;
    this.productoRepositorio = productoRepositorio;
  }

  public List<Compra> obtenerCompras() {
    return compraRepositorio.findAll();
  }

  public CompraResponse obtenerCompraPorId(Integer id) {
    Compra compra = compraRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

    return new CompraResponse(
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
                    .collect(Collectors.toList()));
  }

  public List<Compra> obtenerComprasActivas() {
    return compraRepositorio.findByEstado(Estado.ACTIVO);
  }

  public List<Compra> obtenerComprasPorFecha(LocalDate startDate, LocalDate endDate) {
    return compraRepositorio.findByFechaBetween(startDate, endDate);
  }

  public CompraResponse crearCompra(CompraRequest request) {
    Usuario usuario = usuarioRepositorio.findById(request.idUsuario())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    Proveedor proveedor = proveedorRepositorio.findById(request.idProveedor())
            .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

    Compra compra = new Compra();
    compra.setTipoCompra(request.tipoCompra());
    compra.setNombreCompra(request.nombreCompra());
    compra.setDescripcion(request.descripcion());
    compra.setFecha(request.fecha());
    compra.setEstado(request.estado());
    compra.setProveedor(proveedor);
    compra.setUsuario(usuario);

    for (ProductoCompradoRequest pcRequest : request.productosComprados()) {
      Producto producto = productoRepositorio.findById(pcRequest.idProducto())
              .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + pcRequest.idProducto()));

      DetalleCompraId id = new DetalleCompraId(compra.getIdCompra(), producto.getIdProducto());

      DetalleCompra productoComprado = new DetalleCompra();
      productoComprado.setId(id);
      productoComprado.setCompra(compra);
      productoComprado.setProducto(producto);
      productoComprado.setCantidad(pcRequest.cantidad());
      productoComprado.setPrecioUnitario(pcRequest.precioUnitario());

      compra.getProductosComprados().add(productoComprado);
    }

    Compra savedCompra = compraRepositorio.save(compra);
    return new CompraResponse(
            savedCompra.getIdCompra(),
            savedCompra.getFecha(),
            savedCompra.getTipoCompra(),
            savedCompra.getNombreCompra(),
            savedCompra.getDescripcion(),
            savedCompra.getCostoTotal(),
            savedCompra.getEstado(),
            savedCompra.getProveedor().getNombre(),
            savedCompra.getUsuario().getEmail(),
            savedCompra.getProductosComprados()
                    .stream()
                    .map(pc -> new ProductoCompradoResponse(
                            pc.getProducto().getIdProducto(),
                            pc.getProducto().getNombre(),
                            pc.getCantidad(),
                            pc.getPrecioUnitario()
                    ))
                    .collect(Collectors.toList()));
  }

  public CompraResponse actualizarCompra(Integer idCompra, CompraRequest request) {
    Compra compra = compraRepositorio.findById(idCompra)
            .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

    Proveedor proveedor = proveedorRepositorio.findById(request.idProveedor())
            .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

    compra.setTipoCompra(request.tipoCompra());
    compra.setNombreCompra(request.nombreCompra());
    compra.setDescripcion(request.descripcion());
    compra.setFecha(request.fecha());
    compra.setEstado(request.estado());
    compra.setProveedor(proveedor);

    compra.getProductosComprados().clear();
    for (ProductoCompradoRequest pcRequest : request.productosComprados()) {
      Producto producto = productoRepositorio.findById(pcRequest.idProducto())
              .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + pcRequest.idProducto()));

      DetalleCompraId id = new DetalleCompraId(compra.getIdCompra(), producto.getIdProducto());

      DetalleCompra detalleCompra = new DetalleCompra();
      detalleCompra.setId(id);
      detalleCompra.setCompra(compra);
      detalleCompra.setProducto(producto);
      detalleCompra.setCantidad(pcRequest.cantidad());
      detalleCompra.setPrecioUnitario(pcRequest.precioUnitario());

      compra.getProductosComprados().add(detalleCompra);
    }

    Compra updatedCompra = compraRepositorio.save(compra);
    return new CompraResponse(
            updatedCompra.getIdCompra(),
            updatedCompra.getFecha(),
            updatedCompra.getTipoCompra(),
            updatedCompra.getNombreCompra(),
            updatedCompra.getDescripcion(),
            updatedCompra.getCostoTotal(),
            updatedCompra.getEstado(),
            updatedCompra.getProveedor().getNombre(),
            updatedCompra.getUsuario().getEmail(),
            updatedCompra.getProductosComprados()
                    .stream()
                    .map(pc -> new ProductoCompradoResponse(
                            pc.getProducto().getIdProducto(),
                            pc.getProducto().getNombre(),
                            pc.getCantidad(),
                            pc.getPrecioUnitario()
                    ))
                    .collect(Collectors.toList()));
  }

  public void eliminarCompra(Integer idCompra) {
    compraRepositorio.deleteById(idCompra);
  }
}
