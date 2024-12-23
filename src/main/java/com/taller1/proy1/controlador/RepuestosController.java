package com.taller1.proy1.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.taller1.proy1.model.Repuestos;

import com.taller1.proy1.repository.RepuestosRepo;



@RestController
public class RepuestosController {

	
	@Autowired
    public RepuestosRepo repuestorepo;
	
	@GetMapping("/api/listarepuestos")
	public List<Repuestos> listaRepuestos(){
		return repuestorepo.findAll();
	}
	
	@PostMapping("/api/addrepuestos")
	public void guardarRepuestos(@RequestBody Repuestos repu) {
		repuestorepo.save(repu);
	}
	
	
	// put modificar
	@PutMapping("/api/modrepuestos/{xcodtiporep}")
	public void modificarRepuestos(@PathVariable int xcodtiporep, @RequestBody Repuestos repu) {
		Repuestos rep = new Repuestos();
		rep.setCodtiporep(xcodtiporep);
		rep.setNombrerep(repu.getNombrerep());
		rep.setDescripcionrep(repu.getDescripcionrep());
		rep.setEstado(repu.getEstado());

		repuestorepo.save(rep);
		
	}
	
	//falta eliminar
	@DeleteMapping("/api/delrepuestos/{xcodtiporep}")
	public void eliminarRepuestos(@PathVariable int xcodtiporep)
	{
		repuestorepo.deleteById(xcodtiporep);
	}
	
}
