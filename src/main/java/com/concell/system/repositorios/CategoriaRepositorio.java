package com.concell.system.repositorios;

import com.concell.system.modelos.Categoria;
import com.concell.system.modelos.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {

  List<Categoria> findByEstado(Estado estado);

  List<Categoria> findByNombreContainingIgnoreCase(String nombre);
}
