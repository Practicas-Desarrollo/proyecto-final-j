package com.taller1.proy1.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.proy1.model.Datos;

import com.taller1.proy1.repository.DatosRepo;

//esste es el crack
@RestController
public class DatosController {

	@Autowired
	public DatosRepo datorepo;
	
	@GetMapping("/api/listadato")
	public List<Datos> listaDatos(){
		return datorepo.findAll();
	}
	
}
