package com.taller1.proy1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.taller1.proy1.model.Compras;

public interface ComprasRepo extends JpaRepository<Compras, Integer>{

}
