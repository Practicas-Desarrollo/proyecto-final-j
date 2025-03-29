package com.concell.system.servicios;

import com.concell.system.mapeadores.requests.ProveedorRequest;
import com.concell.system.mapeadores.responses.ProveedorResponse;
import com.concell.system.modelos.Estado;
import com.concell.system.modelos.Proveedor;
import com.concell.system.repositorios.ProveedorRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServicio {
  private final ProveedorRepositorio proveedorRepositorio;

  public ProveedorServicio(ProveedorRepositorio proveedorRepositorio) {
    this.proveedorRepositorio = proveedorRepositorio;
  }

  public List<Proveedor> obtenerProveedores() {
    return proveedorRepositorio.findAll();
  }

  public ProveedorResponse obtenerProveedorPorId(Integer idProveedor) {
    Proveedor proveedor = proveedorRepositorio.findById(idProveedor)
            .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

    return new ProveedorResponse(
            proveedor.getIdProveedor(),
            proveedor.getNombre(),
            proveedor.getApellidoPaterno(),
            proveedor.getApellidoMaterno(),
            proveedor.getContacto(),
            proveedor.getTipoProducto(),
            proveedor.getNit(),
            proveedor.getEstado());
  }

  public List<Proveedor> obtenerProveedoresActivos() {
    return proveedorRepositorio.findByEstado(Estado.ACTIVO);
  }

  public ProveedorResponse crearProveedor(ProveedorRequest request) {
    Proveedor proveedor = new Proveedor();
    proveedor.setNombre(request.nombre());
    proveedor.setApellidoPaterno(request.apellidoPaterno());
    proveedor.setApellidoMaterno(request.apellidoMaterno());
    proveedor.setContacto(request.contacto());
    proveedor.setTipoProducto(request.tipoProducto());
    proveedor.setEstado(request.estado());

    Proveedor savedProveedor = proveedorRepositorio.save(proveedor);
    return new ProveedorResponse(
            savedProveedor.getIdProveedor(),
            savedProveedor.getNombre(),
            savedProveedor.getApellidoPaterno(),
            savedProveedor.getApellidoMaterno(),
            savedProveedor.getContacto(),
            savedProveedor.getTipoProducto(),
            savedProveedor.getNit(),
            savedProveedor.getEstado());
  }

  public ProveedorResponse actualizarProveedor(Integer idProveedor, ProveedorRequest request) {
    Proveedor proveedor = proveedorRepositorio.findById(idProveedor)
            .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

    proveedor.setNombre(request.nombre());
    proveedor.setApellidoPaterno(request.apellidoPaterno());
    proveedor.setApellidoMaterno(request.apellidoMaterno());
    proveedor.setContacto(request.contacto());
    proveedor.setTipoProducto(request.tipoProducto());
    proveedor.setEstado(request.estado());

    Proveedor updatedProveedor = proveedorRepositorio.save(proveedor);
    return new ProveedorResponse(
            updatedProveedor.getIdProveedor(),
            updatedProveedor.getNombre(),
            updatedProveedor.getApellidoPaterno(),
            updatedProveedor.getApellidoMaterno(),
            updatedProveedor.getContacto(),
            updatedProveedor.getTipoProducto(),
            updatedProveedor.getNit(),
            updatedProveedor.getEstado());
  }

  public void eliminarProveedor(Integer idProveedor) {
    if (!proveedorRepositorio.existsById(idProveedor)) {
      throw new RuntimeException("Proveedor no encontrado");
    }

    proveedorRepositorio.deleteById(idProveedor);
  }
}
