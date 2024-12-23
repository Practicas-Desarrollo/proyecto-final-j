package com.taller1.proy1.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.taller1.proy1.model.Gmenus;
import com.taller1.proy1.repository.GmenusRepo;
import com.taller1.proy1.repository.MenusRepo;

public class GmenusController {

	@Autowired
	public GmenusRepo gmenusrepo;
	
	
	@GetMapping({"api/listaGmenus"})
	public List<Gmenus> listaAreas(){
		return gmenusrepo.findAll();
	}
	

	
	//put
}
