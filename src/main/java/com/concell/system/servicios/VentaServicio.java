package com.concell.system.servicios;

import com.concell.system.modelos.Estado;
import com.concell.system.modelos.Venta;
import com.concell.system.repositorios.VentaRepositorio;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class VentaServicio {
  private final VentaRepositorio ventaRepositorio;

  public VentaServicio(VentaRepositorio ventaRepositorio) {
    this.ventaRepositorio = ventaRepositorio;
  }

  public List<Venta> listarVentas() {
    return ventaRepositorio.findAll();
  }

  @Transactional
  public Venta crearVenta(Venta venta) {
    return ventaRepositorio.save(venta);
  }

  public List<Venta> obtenerVentasActivas() {
    return ventaRepositorio.findByEstado(Estado.ACTIVO);
  }

  public List<Venta> obtenerVentasPorFecha(LocalDate startDate, LocalDate endDate) {
    return ventaRepositorio.findByFechaBetween(startDate, endDate);
  }

  public List<Venta> buscarVentasPorCliente(String nombreCliente) {
    return ventaRepositorio.findByNombreClienteContainingIgnoreCase(nombreCliente);
  }

  public Venta actualizarVenta(Venta venta) {
    if (!ventaRepositorio.existsById(venta.getIdVenta())) {
      throw new RuntimeException("Venta no encontrada");
    }
    return ventaRepositorio.save(venta);
  }

  public void eliminarVenta(Integer idVenta) {
    ventaRepositorio.deleteById(idVenta);
  }
}
