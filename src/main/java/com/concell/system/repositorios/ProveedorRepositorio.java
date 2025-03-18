package com.concell.system.repositorios;

import com.concell.system.modelos.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import com.concell.system.modelos.Proveedor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, Integer> {

  List<Proveedor> findByEstado(Estado estado);

  List<Proveedor> findByTipoProducto(String tipoProducto);
}
