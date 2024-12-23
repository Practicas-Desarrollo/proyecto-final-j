package com.taller1.proy1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taller1.proy1.model.Mepro;
import com.taller1.proy1.model.MeproPK;

public interface MeproRepo extends JpaRepository<Mepro, MeproPK>{

	
}
