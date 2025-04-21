package com.concell.system.configuraciones.basedatos;

import com.concell.system.modelos.Estado;
import com.concell.system.modelos.Rol;
import com.concell.system.modelos.Usuario;
import com.concell.system.repositorios.RolRepositorio;
import com.concell.system.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InicializadorDatos {
  @Autowired
  private RolRepositorio rolRepositorio;

  @Autowired
  private UsuarioRepositorio usuarioRepositorio;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostConstruct
  public void inicializar() {
    if (rolRepositorio.count() == 0) {
      Rol admin = new Rol();
      admin.setNombre("Administrador");
      admin.setEstado(Estado.ACTIVO);

      Rol vendedor = new Rol();
      vendedor.setNombre("Vendedor");
      vendedor.setEstado(Estado.ACTIVO);

      Rol propietario = new Rol();
      propietario.setNombre("Propietario de la tienda");
      propietario.setEstado(Estado.ACTIVO);

      List<Rol> rolesGuardados = rolRepositorio
              .saveAll(List.of(admin, vendedor, propietario));

      crearUsuarioAdmin(rolesGuardados.get(0));
    }

    else if (!usuarioRepositorio.existsByEmail("admin@admin.com")) {
      Rol rolAdmin = rolRepositorio.findByNombre("Administrador");
      crearUsuarioAdmin(rolAdmin);
    }
  }

  private void crearUsuarioAdmin(Rol rolAdmin) {
    if (!usuarioRepositorio.existsByEmail("admin@admin.com")) {
      Usuario admin = new Usuario();
      admin.setEmail("admin@admin.com");
      admin.setPassword(passwordEncoder.encode("Admin1234"));
      admin.setEstado(Estado.ACTIVO);
      admin.setRol(rolAdmin);

      usuarioRepositorio.save(admin);
    }
  }
}
