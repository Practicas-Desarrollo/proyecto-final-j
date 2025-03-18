package com.concell.system.repositorios;

import com.concell.system.modelos.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import com.concell.system.modelos.Venta;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepositorio extends JpaRepository <Venta, Integer> {

  List<Venta> findByEstado(Estado estado);

  List<Venta> findByFechaBetween(LocalDate startDate, LocalDate endDate);

  List<Venta> findByNombreClienteContainingIgnoreCase(String nombreCliente);
}
