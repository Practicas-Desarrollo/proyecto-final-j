package com.taller1.proy1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taller1.proy1.model.Menus;

public interface MenusRepo extends JpaRepository <Menus, Integer>{

	
	//filtro	
	@Query("SELECT c FROM Menus c WHERE (c.estado=:xest1 OR c.estado=:xest2)")
	List <Menus> listafiltro(int xest1, int xest2);

	
}
