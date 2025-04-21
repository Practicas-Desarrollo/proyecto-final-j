package com.concell.system.repositorios;

import com.concell.system.modelos.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import com.concell.system.modelos.Rol;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Integer> {

  Rol findByNombre(String nombre);

  List<Rol> findByNombreContainingIgnoreCase(String nombre);

  boolean existsByNombre(String nombre);

  List<Rol> findByEstado(Estado estado);
}
