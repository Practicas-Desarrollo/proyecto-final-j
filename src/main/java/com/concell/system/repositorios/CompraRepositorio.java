package com.concell.system.repositorios;

import com.concell.system.modelos.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import com.concell.system.modelos.Compra;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompraRepositorio extends JpaRepository<Compra, Integer> {

  List<Compra> findByEstado(Estado estado);

  List<Compra> findByFechaBetween(LocalDate startDate, LocalDate endDate);
}
