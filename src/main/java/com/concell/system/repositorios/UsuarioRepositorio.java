package com.concell.system.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.concell.system.modelos.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByEmail(String email);
}
