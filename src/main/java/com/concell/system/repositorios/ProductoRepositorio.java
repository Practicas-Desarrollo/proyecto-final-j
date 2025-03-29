package com.concell.system.repositorios;

import com.concell.system.modelos.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import com.concell.system.modelos.Producto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepositorio extends JpaRepository <Producto, Integer> {

  List<Producto> findByEstado(Estado estado);

  Optional<Producto> findByNombre(String nombre);

  List<Producto> findByNombreContainingIgnoreCase(String nombre);

  List<Producto> findByPrecioBetween(BigDecimal minPrecio, BigDecimal maxPrecio);
}
