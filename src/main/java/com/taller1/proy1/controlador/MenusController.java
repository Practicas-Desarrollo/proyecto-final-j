package com.taller1.proy1.controlador;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.taller1.proy1.model.Menus;
import com.taller1.proy1.repository.MenusRepo;



@RestController
public class MenusController {

	@Autowired
	public MenusRepo menusrep;
	
	@GetMapping("/api/listamenus")
	public List<Menus> listaMenus(){
		return menusrep.findAll();
	}
	

	@PostMapping("/api/addmenus")
	public void guardarAreas(@RequestBody Menus men) {
		menusrep.save(men);
	}
	
	@PutMapping("/api/modmenus/{xcodm}")
	public void modificarAreas(@PathVariable int xcodm, @RequestBody Menus men) {
		Menus me = new Menus();
		me.setCodm(xcodm);
		me.setNombre(men.getNombre());
		me.setEstado(men.getEstado());

		menusrep.save(me);
	}
	
	@DeleteMapping("/api/delmenus/{xcodm}")
	public void eliminarMenus(@PathVariable int xcodm)
	{
		menusrep.deleteById(xcodm);
	}
	
	@GetMapping({"api/listafiltron1"})
	public List<Menus> listaFiltros(@RequestParam("estado") int estado, HttpSession session){
		if(estado == -1)
			return menusrep.listafiltro(0,1);
		else if (estado != -1)
			return menusrep.listafiltro(estado,estado);
		else
			return menusrep.listafiltro(estado,estado);

	}
}
