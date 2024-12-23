package com.taller1.proy1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taller1.proy1.model.Usuario;

public interface UsuarioRepo extends JpaRepository <Usuario, String> {
	
	
	@Modifying
	@Query(value="insert into usuarios(login,estado,clave,codper) values (:login,:estado,:clave,:codper)", nativeQuery = true )
	public int addusuarios(
			@Param("login") String login, 
			@Param("estado") int estado, 
			@Param("clave") String clave,
			
			@Param("codper") int codper);
	
	
	@Modifying
	@Query(value = "update usuarios set clave=?1 where codper=?2", nativeQuery = true)
	public int modUsuario(@Param("clave") String xpassword, @Param("codper") int xcodper);
}
