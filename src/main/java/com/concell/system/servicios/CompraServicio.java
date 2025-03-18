package com.concell.system.servicios;

import com.concell.system.modelos.Compra;
import com.concell.system.modelos.Estado;
import com.concell.system.repositorios.CompraRepositorio;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class CompraServicio {
  private final CompraRepositorio compraRepositorio;

  public CompraServicio(CompraRepositorio compraRepositorio) {
    this.compraRepositorio = compraRepositorio;
  }

  public List<Compra> listarCompras() {
    return compraRepositorio.findAll();
  }

  @Transactional
  public Compra crearCompra(Compra compra) {
    return compraRepositorio.save(compra);
  }

  public List<Compra> obtenerComprasActivas() {
    return compraRepositorio.findByEstado(Estado.ACTIVO);
  }

  public List<Compra> obtenerComprasPorFecha(LocalDate startDate, LocalDate endDate) {
    return compraRepositorio.findByFechaBetween(startDate, endDate);
  }

  public Compra actualizarCompra(Compra compra) {
    if (!compraRepositorio.existsById(compra.getIdCompra())) {
      throw new RuntimeException("Compra no encontrada");
    }
    return compraRepositorio.save(compra);
  }

  public void eliminarCompra(Integer idCompra) {
    compraRepositorio.deleteById(idCompra);
  }
}
