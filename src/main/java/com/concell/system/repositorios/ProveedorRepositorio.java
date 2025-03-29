package com.concell.system.repositorios;

import com.concell.system.modelos.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import com.concell.system.modelos.Proveedor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, Integer> {

  Optional<Proveedor> findByNombre(String nombre);

  List<Proveedor> findByEstado(Estado estado);
}
