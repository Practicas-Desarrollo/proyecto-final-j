package com.concell.system.repositorios;

import com.concell.system.modelos.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import com.concell.system.modelos.Rol;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Integer> {

  Optional<Rol> findByNombre(String nombre);

  List<Rol> findByEstado(Estado estado);

  boolean existsByNombre(String nombre);
}
