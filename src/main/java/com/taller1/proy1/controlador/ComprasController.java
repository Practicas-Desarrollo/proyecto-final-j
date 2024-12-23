package com.taller1.proy1.controlador;

import java.util.List;

import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.taller1.proy1.model.Compras;
import com.taller1.proy1.model.Productos;
import com.taller1.proy1.model.Proveedores;
import com.taller1.proy1.repository.ComprasRepo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
public class ComprasController {

	@Autowired
   ComprasRepo comprarepo;
	
	@GetMapping({"api/listacompras"})
	public List<Compras> listacompra(){
		return comprarepo.findAll();
	}
	
	@PostMapping("/api/addcompras")
	public void guardarProductos(@RequestBody Compras compra) {
		comprarepo.save(compra);
	}
	
	@PutMapping("/api/modcompra/{xcodcompra}")
	public void modificarproveedores(@PathVariable int xcodcompra, @RequestBody Compras comp) {
		Compras com = new Compras();
		com.setCodcompra(xcodcompra);
		com.setTipocom(comp.getTipocom());
		com.setNombrecompra(comp.getNombrecompra());
		com.setDescripcion(comp.getDescripcion());
		com.setCosto(comp.getCosto());
		com.setFecha(comp.getFecha());
		com.setEstado(comp.getEstado());
		com.setProveedores(comp.getProveedores());
	
		
		comprarepo.save(com);
	}
	
	//falta eliminar
	@DeleteMapping("/api/delcompras/{xcodcompra}")
	public void eliminarCompras(@PathVariable int xcodcompra)
	{
		comprarepo.deleteById(xcodcompra);
	}
}
