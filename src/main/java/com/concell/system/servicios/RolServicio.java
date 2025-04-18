package com.concell.system.servicios;

import com.concell.system.mapeadores.requests.RolRequest;
import com.concell.system.mapeadores.responses.RolResponse;
import com.concell.system.modelos.Estado;
import com.concell.system.modelos.Rol;
import com.concell.system.repositorios.RolRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServicio {

  private final RolRepositorio rolRepositorio;

  public RolServicio(RolRepositorio rolRepositorio) {
    this.rolRepositorio = rolRepositorio;
  }

  public List<Rol> obtenerRoles() {
    return rolRepositorio.findAll();
  }

  public Rol obtenerRolPorId(int idRol) {
    return rolRepositorio
            .findById(idRol)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
  }

  public List<Rol> obtenerRolesActivos() {
    return rolRepositorio.findByEstado(Estado.ACTIVO);
  }

  public List<Rol> buscarRolesPorNombre(String nombre) {
    return rolRepositorio.findByNombreContainingIgnoreCase(nombre);
  }

  public RolResponse crearRol(RolRequest rolRequest) {
    if (rolRepositorio.existsByNombre(rolRequest.nombre())) {
      throw new RuntimeException("El nombre del rol ya existe");
    }

    Rol rol = new Rol();
    rol.setNombre(rolRequest.nombre());
    rol.setEstado(rolRequest.estado());

    Rol savedRol = rolRepositorio.save(rol);

    return new RolResponse(
            savedRol.getIdRol(),
            savedRol.getNombre(),
            savedRol.getEstado()
    );
  }

  public RolResponse actualizarRol(int idRol, RolRequest rolRequest) {
    Rol existingRol = rolRepositorio.findById(idRol)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

    existingRol.setNombre(rolRequest.nombre());
    existingRol.setEstado(rolRequest.estado());

    Rol updatedRol = rolRepositorio.save(existingRol);

    return new RolResponse(
            updatedRol.getIdRol(),
            updatedRol.getNombre(),
            updatedRol.getEstado()
    );
  }

  public RolResponse actualizarEstadoRol(int idRol) {
    Rol rol = rolRepositorio
            .findById(idRol)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

    Estado nuevoEstado = (rol.getEstado() == Estado.ACTIVO) ? Estado.INACTIVO : Estado.ACTIVO;
    rol.setEstado(nuevoEstado);

    Rol updatedRol = rolRepositorio.save(rol);
    return new RolResponse(
            updatedRol.getIdRol(),
            updatedRol.getNombre(),
            updatedRol.getEstado()
    );
  }

  public void eliminarRol(Integer idRol) {
    if (!rolRepositorio.existsById(idRol)) {
      throw new RuntimeException("Rol no encontrado");
    }

    rolRepositorio.deleteById(idRol);
  }
}
