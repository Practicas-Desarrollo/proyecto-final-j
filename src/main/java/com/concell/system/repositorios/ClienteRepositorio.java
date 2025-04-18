package com.concell.system.repositorios;

import com.concell.system.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

  Optional<Cliente> findByNit(String nit);

  List<Cliente> findByNombreContainingIgnoreCase(String nombre);

  Optional<Cliente> findByEmail(String email);
}
