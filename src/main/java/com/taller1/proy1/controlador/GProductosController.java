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
import com.taller1.proy1.repository.GProductosRepo;

@RestController
public class GProductosController {
	
	@Autowired
    public GProductosRepo   productorep;
	
	@GetMapping("/api/listaproductos")
	public List<Productos> listaProductos(){
		return productorep.findAll();
	}
	
	@PostMapping("/api/addproductos")
	public void guardarProductos(@RequestBody Productos produc) {
		productorep.save(produc);
	}
	
	// put modificar
	@PutMapping("/api/modproductos/{xcodproducto}")
	public void modificarProductos(@PathVariable int xcodproducto, @RequestBody Productos produc) {
		Productos pro = new Productos();
		pro.setCodproducto(xcodproducto);
		pro.setNombre(produc.getNombre());
		pro.setPrecio(produc.getPrecio());
		pro.setCantidad(produc.getCantidad());
		pro.setEstado(produc.getEstado());
		pro.setDescripcion(produc.getDescripcion());
		productorep.save(pro);
		
	}
	
	
	//falta eliminar
	@DeleteMapping("/api/delproductos/{xcodproducto}")
	public void eliminarProductos(@PathVariable int xcodproducto)
	{
		productorep.deleteById(xcodproducto);
	}

	
	

}
