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

import com.taller1.proy1.model.Productos;
import com.taller1.proy1.model.Ventas;
import com.taller1.proy1.repository.GProductosRepo;
import com.taller1.proy1.repository.VentasRepo;

@RestController
public class VentasController {

	@Autowired
    public VentasRepo ventasrepo;
	
	
	@GetMapping("/api/listaventas")
	public List<Ventas>listaventas(){
		return ventasrepo.findAll();
	}
	
	
	@PostMapping("/api/addventas")
	public Ventas guardarMenus(@RequestBody Ventas ventas) {
		
		Ventas temp = ventasrepo.save(ventas);
		
		return temp;
	}
	
	
	
	// put modificar
	@PutMapping("/api/modventas/{xcodventas}")
	public void modificarVentas(@PathVariable int xcodventas, @RequestBody Ventas vent) {
		Ventas ven = new Ventas();
		
		ven.setCodventas(xcodventas);
		ven.setNombrecliente(vent.getNombrecliente());
		ven.setMonto(vent.getMonto());
		ven.setNit(vent.getNit());
		ven.setDescripciongarantia(vent.getDescripciongarantia());
		ven.setFecha(vent.getFecha());
		ven.setEstado(vent.getEstado());
		ventasrepo.save(ven);
		
		
	}
	
	
	//falta eliminar
		@DeleteMapping("/api/delventas/{xcodventas}")
		public void eliminarVentas(@PathVariable int xcodventas)
		{
			ventasrepo.deleteById(xcodventas);
		}
	
}
