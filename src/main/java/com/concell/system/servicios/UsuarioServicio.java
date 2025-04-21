package com.concell.system.servicios;

import com.concell.system.mapeadores.requests.DetalleUsuarioRequest;
import com.concell.system.mapeadores.responses.DetalleUsuarioResponse;
import com.concell.system.mapeadores.responses.UsuarioResponse;
import com.concell.system.modelos.DetalleUsuario;
import com.concell.system.modelos.Estado;
import com.concell.system.modelos.Rol;
import com.concell.system.modelos.Usuario;
import com.concell.system.repositorios.RolRepositorio;
import com.concell.system.repositorios.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

  private final UsuarioRepositorio usuarioRepositorio;
  private final RolRepositorio rolRepositorio;

  public UsuarioServicio(UsuarioRepositorio usuarioRepositorio,
                          RolRepositorio rolRepositorio) {
    this.usuarioRepositorio = usuarioRepositorio;
    this.rolRepositorio = rolRepositorio;
  }

  public List<Usuario> obtenerUsuarios() {
    return usuarioRepositorio.findAll();
  }

  public Usuario obtenerUsuarioPorId(Integer idUsuario) {
    return usuarioRepositorio.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
  }

  public Optional<UsuarioResponse> obtenerInformacionUsuario(String email) {
    return usuarioRepositorio.findByEmail(email)
            .map(usuario -> new UsuarioResponse(
                    usuario.getIdUsuario(),
                    usuario.getEmail(),
                    usuario.getEstado(),
                    usuario.getRol().getIdRol(),
                    usuario.getDetalleUsuario() != null ?
                            new DetalleUsuarioResponse(
                                    usuario.getIdUsuario(),
                                    usuario.getDetalleUsuario().getFoto(),
                                    usuario.getDetalleUsuario().getNombreUsuario(),
                                    usuario.getDetalleUsuario().getNombre(),
                                    usuario.getDetalleUsuario().getApellidoPaterno(),
                                    usuario.getDetalleUsuario().getApellidoMaterno(),
                                    usuario.getDetalleUsuario().getFechaNacimiento()
                            ) : null
            ));
  }

  public List<Usuario> obtenerUsuariosActivos() {
    return usuarioRepositorio.findByEstado(Estado.ACTIVO);
  }

  public UsuarioResponse actualizarDetalleUsuario(
          Integer idUsuario, DetalleUsuarioRequest request) {
    Usuario usuario = obtenerUsuarioPorId(idUsuario);

    if (usuario.getDetalleUsuario() == null) {
      usuario.setDetalleUsuario(new DetalleUsuario());
      usuario.getDetalleUsuario().setUsuario(usuario);
    }

    DetalleUsuario detalle = usuario.getDetalleUsuario();
    detalle.setFoto(request.foto());
    detalle.setNombre(request.nombre());
    detalle.setApellidoPaterno(request.apellidoPaterno());
    detalle.setApellidoMaterno(request.apellidoMaterno());
    detalle.setFechaNacimiento(request.fechaNacimiento());

    Usuario usuarioActualizado = usuarioRepositorio.save(usuario);
    return new UsuarioResponse(
            usuarioActualizado.getIdUsuario(),
            usuarioActualizado.getEmail(),
            usuarioActualizado.getEstado(),
            usuarioActualizado.getRol().getIdRol(),
            usuarioActualizado.getDetalleUsuario() != null ?
                    new DetalleUsuarioResponse(
                            usuarioActualizado.getIdUsuario(),
                            usuarioActualizado.getDetalleUsuario().getFoto(),
                            usuarioActualizado.getDetalleUsuario().getNombreUsuario(),
                            usuarioActualizado.getDetalleUsuario().getNombre(),
                            usuarioActualizado.getDetalleUsuario().getApellidoPaterno(),
                            usuarioActualizado.getDetalleUsuario().getApellidoMaterno(),
                            usuarioActualizado.getDetalleUsuario().getFechaNacimiento()
                    ) : null
    );
  }

  public UsuarioResponse actualizarEstadoUsuario(Integer idUsuario) {
    Usuario usuario = obtenerUsuarioPorId(idUsuario);

    Estado nuevoEstado = (usuario.getEstado() == Estado.ACTIVO) ? Estado.INACTIVO : Estado.ACTIVO;
    usuario.setEstado(nuevoEstado);

    Usuario usuarioActualizado = usuarioRepositorio.save(usuario);

    return new UsuarioResponse(
            usuarioActualizado.getIdUsuario(),
            usuarioActualizado.getEmail(),
            usuarioActualizado.getEstado(),
            usuarioActualizado.getRol().getIdRol(),
            usuarioActualizado.getDetalleUsuario() != null ?
                    new DetalleUsuarioResponse(
                            usuarioActualizado.getIdUsuario(),
                            usuarioActualizado.getDetalleUsuario().getFoto(),
                            usuarioActualizado.getDetalleUsuario().getNombreUsuario(),
                            usuarioActualizado.getDetalleUsuario().getNombre(),
                            usuarioActualizado.getDetalleUsuario().getApellidoPaterno(),
                            usuarioActualizado.getDetalleUsuario().getApellidoMaterno(),
                            usuarioActualizado.getDetalleUsuario().getFechaNacimiento()
                    ) : null
    );
  }

  public UsuarioResponse actualizarRol(Integer idUsuario, Integer idRol) {
    Usuario usuario = obtenerUsuarioPorId(idUsuario);

    Rol nuevoRol = rolRepositorio.findById(idRol)
            .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));
    usuario.setRol(nuevoRol);

    return new UsuarioResponse(
            usuario.getIdUsuario(),
            usuario.getEmail(),
            usuario.getEstado(),
            usuario.getRol().getIdRol(),
            usuario.getDetalleUsuario() != null ?
                    new DetalleUsuarioResponse(
                            usuario.getIdUsuario(),
                            usuario.getDetalleUsuario().getFoto(),
                            usuario.getDetalleUsuario().getNombreUsuario(),
                            usuario.getDetalleUsuario().getNombre(),
                            usuario.getDetalleUsuario().getApellidoPaterno(),
                            usuario.getDetalleUsuario().getApellidoMaterno(),
                            usuario.getDetalleUsuario().getFechaNacimiento()
                    ) : null
    );
  }

  public void eliminarUsuario(Integer idUsuario) {
    Usuario usuario = usuarioRepositorio.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    usuarioRepositorio.delete(usuario);
  }
}
