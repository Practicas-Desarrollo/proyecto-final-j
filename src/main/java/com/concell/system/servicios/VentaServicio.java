package com.concell.system.servicios;

import com.concell.system.mapeadores.requests.ProductoVendidoRequest;
import com.concell.system.mapeadores.requests.VentaRequest;
import com.concell.system.mapeadores.responses.ProductoVendidoResponse;
import com.concell.system.mapeadores.responses.VentaResponse;
import com.concell.system.modelos.*;
import com.concell.system.repositorios.ClienteRepositorio;
import com.concell.system.repositorios.ProductoRepositorio;
import com.concell.system.repositorios.UsuarioRepositorio;
import com.concell.system.repositorios.VentaRepositorio;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServicio {
  private final VentaRepositorio ventaRepositorio;
  private final ProductoServicio productoServicio;
  private final ProductoRepositorio productoRepositorio;
  private final UsuarioRepositorio usuarioRepositorio;
  private final ClienteRepositorio clienteRepositorio;

  public VentaServicio(VentaRepositorio ventaRepositorio,
                       ProductoServicio productoServicio,
                       ProductoRepositorio productoRepositorio,
                       UsuarioRepositorio usuarioRepositorio,
                       ClienteRepositorio clienteRepositorio) {
    this.ventaRepositorio = ventaRepositorio;
    this.productoServicio = productoServicio;
    this.productoRepositorio = productoRepositorio;
    this.usuarioRepositorio = usuarioRepositorio;
    this.clienteRepositorio = clienteRepositorio;
  }

  public List<Venta> obtenerVentas() {
    return ventaRepositorio.findAll();
  }

  public Venta obtenerVentaPorId(Integer idVenta) {
    return ventaRepositorio.findById(idVenta)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
  }

  public List<Venta> obtenerVentasActivas() {
    return ventaRepositorio.findByEstado(Estado.ACTIVO);
  }

  public List<Venta> obtenerVentasPorFecha(LocalDate startDate, LocalDate endDate) {
    return ventaRepositorio.findByFechaBetween(startDate, endDate);
  }

  public VentaResponse crearVenta(VentaRequest request) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();

    Usuario usuario = usuarioRepositorio.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    Cliente cliente = clienteRepositorio.findById(request.idCliente())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

    Venta venta = new Venta();
    venta.setFecha(request.fecha());
    venta.setDescripcionGarantia(request.descripcionGarantia());
    venta.setTipoPago(request.tipoPago());
    venta.setEstado(request.estado());
    venta.setUsuario(usuario);
    venta.setCliente(cliente);

    for (ProductoVendidoRequest pvRequest : request.productosVendidos()) {
      Producto producto = productoRepositorio.findById(pvRequest.idProducto())
              .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + pvRequest.idProducto()));

      if (producto.getCantidad() < pvRequest.cantidad()) {
        throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
      }

      producto.setCantidad(producto.getCantidad() - pvRequest.cantidad());
      productoRepositorio.save(producto);

      productoServicio.verificarStockBajo(producto);

      ProductoVendidoId id = new ProductoVendidoId(venta.getIdVenta(), producto.getIdProducto());

      ProductoVendido productoVendido = new ProductoVendido();
      productoVendido.setId(id);
      productoVendido.setVenta(venta);
      productoVendido.setProducto(producto);
      productoVendido.setCantidad(pvRequest.cantidad());
      productoVendido.setPrecioUnitario(pvRequest.precioUnitario());

      venta.getProductosVendidos().add(productoVendido);
    }

    Venta savedVenta = ventaRepositorio.save(venta);
    return new VentaResponse(
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
  }

  public VentaResponse actualizarVenta(Integer idVenta, VentaRequest request) {
    Venta venta = ventaRepositorio.findById(idVenta)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + idVenta));

    venta.setFecha(request.fecha());
    venta.setDescripcionGarantia(request.descripcionGarantia());
    venta.setTipoPago(request.tipoPago());
    venta.setEstado(request.estado());

    if (!venta.getCliente().getIdCliente().equals(request.idCliente())) {
      Cliente nuevoCliente = clienteRepositorio.findById(request.idCliente())
              .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + request.idCliente()));
      venta.setCliente(nuevoCliente);
    }

    venta.getProductosVendidos().clear();

    for (ProductoVendidoRequest pvRequest : request.productosVendidos()) {
      Producto producto = productoRepositorio.findById(pvRequest.idProducto())
              .orElseThrow(() -> new RuntimeException(
                      "Producto no encontrado con ID: " + pvRequest.idProducto()
              ));

      if (producto.getCantidad() < pvRequest.cantidad()) {
        throw new RuntimeException(
                "Stock insuficiente para el producto ID: " + producto.getIdProducto() +
                        ". Stock actual: " + producto.getCantidad()
        );
      }

      producto.setCantidad(producto.getCantidad() - pvRequest.cantidad());
      productoRepositorio.save(producto);

      ProductoVendidoId id = new ProductoVendidoId(venta.getIdVenta(), producto.getIdProducto());

      ProductoVendido productoVendido = new ProductoVendido();
      productoVendido.setId(id);
      productoVendido.setVenta(venta);
      productoVendido.setProducto(producto);
      productoVendido.setCantidad(pvRequest.cantidad());
      productoVendido.setPrecioUnitario(pvRequest.precioUnitario());

      venta.getProductosVendidos().add(productoVendido);
    }

    Venta ventaActualizada = ventaRepositorio.save(venta);
    return new VentaResponse(
            ventaActualizada.getIdVenta(),
            ventaActualizada.getFecha(),
            ventaActualizada.getDescripcionGarantia(),
            ventaActualizada.getTipoPago(),
            ventaActualizada.getTotal(),
            ventaActualizada.getEstado(),
            venta.getUsuario().getIdUsuario(),
            venta.getCliente().getIdCliente(),
            ventaActualizada.getProductosVendidos().stream()
                    .map(pv -> new ProductoVendidoResponse(
                            pv.getProducto().getIdProducto(),
                            pv.getProducto().getNombre(),
                            pv.getCantidad(),
                            pv.getPrecioUnitario()
                    ))
                    .collect(Collectors.toList())
    );
  }

  public void eliminarCompra(Integer idVenta) {
    ventaRepositorio.deleteById(idVenta);
  }
}
