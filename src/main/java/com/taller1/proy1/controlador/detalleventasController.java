package com.taller1.proy1.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.proy1.model.Productos;
import com.taller1.proy1.model.detalleventas;
import com.taller1.proy1.repository.GProductosRepo;
import com.taller1.proy1.repository.detalleventasRepo;



@RestController
public class detalleventasController {

	
	@Autowired
	private detalleventasRepo detalleventasrepos;
	
	@Autowired
	private GProductosRepo prorepos;
	
	@GetMapping({"api/listadetalleventas"})
	public List<detalleventas> ListaDetalleVenta(){
		return detalleventasrepos.findAll();
	}
	
	@PostMapping("/api/adddetallearray")
	public void guardarDetalleArray(@RequestBody List<detalleventas> detalleve) {
		//este es un for each
		for (detalleventas detalleVenta : detalleve) {
			System.out.println(detalleVenta);
			int codpr =  detalleVenta.getProducto().getCodproducto();
			Productos pr = prorepos.getById(codpr);
			pr.setCantidad(pr.getCantidad()-detalleVenta.getCantidad());
			prorepos.save(pr);
			detalleventasrepos.save(detalleVenta);
		}
	}
	
}
