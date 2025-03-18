package com.concell.system.servicios;

import com.concell.system.modelos.Estado;
import com.concell.system.modelos.Proveedor;
import com.concell.system.repositorios.ProveedorRepositorio;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProveedorServicio {
  private final ProveedorRepositorio proveedorRepositorio;

  public ProveedorServicio(ProveedorRepositorio proveedorRepositorio) {
    this.proveedorRepositorio = proveedorRepositorio;
  }

  public List<Proveedor> listarProveedores() {
    return proveedorRepositorio.findAll();
  }

  @Transactional
  public Proveedor crearProveedor(Proveedor proveedor) {
    return proveedorRepositorio.save(proveedor);
  }

  public List<Proveedor> obtenerProveedoresActivos() {
    return proveedorRepositorio.findByEstado(Estado.ACTIVO);
  }

  public List<Proveedor> obtenerProveedoresPorTipoProducto(String tipoProducto) {
    return proveedorRepositorio.findByTipoProducto(tipoProducto);
  }

  public Proveedor actualizarProveedor(Proveedor proveedor) {
    if (!proveedorRepositorio.existsById(proveedor.getIdProveedor())) {
      throw new RuntimeException("Proveedor no encontrado");
    }
    return proveedorRepositorio.save(proveedor);
  }

  public void eliminarProveedor(Integer idProveedor) {
    proveedorRepositorio.deleteById(idProveedor);
  }
}
