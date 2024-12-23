package com.taller1.proy1.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.taller1.proy1.model.Mepro;
import com.taller1.proy1.repository.MeproRepo;

@RestController
public class MeproController {

	@Autowired
	public MeproRepo meprorepo;
	
	@GetMapping({"/api/listamepro"})
	public List<Mepro> listamenuproceso(){
		return meprorepo.findAll();
	}
}
