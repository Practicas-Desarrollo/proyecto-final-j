package com.taller1.proy1.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.proy1.model.Procesos;
import com.taller1.proy1.repository.ProcesosRepo;




@RestController
public class ProcesosController {

	
	@Autowired
	public ProcesosRepo procesorep;
	
	@GetMapping("/api/listaprocesos")
	public List<Procesos> listaProcesos(){
		return procesorep.findAll();
	}
}
