package com.taller1.proy1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taller1.proy1.model.Usurol;
import com.taller1.proy1.model.UsurolPK;
public interface UsurolRepo extends JpaRepository<Usurol, UsurolPK>{
	@Query("SELECT c FROM Usurol c WHERE c.usuario.login = ?1 ")
	List<Usurol> rolesLogin(String xlogin);
}
