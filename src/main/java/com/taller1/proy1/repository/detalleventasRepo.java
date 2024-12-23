package com.taller1.proy1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taller1.proy1.model.DetalleVentaPK;
import com.taller1.proy1.model.Ventas;
import com.taller1.proy1.model.detalleventas;

import antlr.collections.List;

public interface detalleventasRepo  extends JpaRepository <detalleventas,DetalleVentaPK>{
	

	//lista detalle de venta
	//@Query("select d from DetalleVentas d where d.detalleventaID.codventas = ?1")
//	List<detalleVentas> getDetalle(int codventas);
}
