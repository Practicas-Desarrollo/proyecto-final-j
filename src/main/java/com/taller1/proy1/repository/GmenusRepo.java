package com.taller1.proy1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taller1.proy1.model.Gmenus;

@Repository
public interface GmenusRepo extends JpaRepository<Gmenus,Integer>{

}
