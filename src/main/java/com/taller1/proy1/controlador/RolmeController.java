package com.taller1.proy1.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.proy1.model.Rolme;
import com.taller1.proy1.repository.RolmeRepo;

@RestController
public class RolmeController {

	
	@Autowired
	public RolmeRepo rolmerepo;
	
	@GetMapping({"/api/listarolme"})
	public List<Rolme> listarolme(){
		return rolmerepo.findAll();
	}
	
}
