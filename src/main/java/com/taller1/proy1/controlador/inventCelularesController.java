package com.taller1.proy1.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.proy1.model.InventCelulares;
import com.taller1.proy1.repository.InventCelularesRepo;


@RestController
public class inventCelularesController {

	
	@Autowired
    public InventCelularesRepo  inventCelrep;
	
	@GetMapping("/api/listacelulares")
	public List<InventCelulares> listacelulares(){
		return inventCelrep.findAll();
	}
	
	
	
}
