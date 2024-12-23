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

import com.taller1.proy1.model.Menus;
import com.taller1.proy1.model.Roles;
import com.taller1.proy1.repository.RolesRepo;



@RestController
public class RolesController {
	
	@Autowired
	public RolesRepo rolesrep;
	
	@GetMapping("/api/listaroles")
	public List<Roles> listaRoles(){
		return rolesrep.findAll();
	}

	@PostMapping("/api/addroles")
	public void guardarRoles(@RequestBody Roles rol) {
		rolesrep.save(rol);
	}
	

	
	@PutMapping("/api/modroles/{xcodr}")
	public void modificarRoles(@PathVariable int xcodr, @RequestBody Roles rol) {
		Roles ro = new Roles();
		ro.setCodr(xcodr);
		ro.setNombre(rol.getNombre());
		ro.setEstado(rol.getEstado());

		rolesrep.save(ro);
	}
	
	@DeleteMapping("/api/modroles/{xcodr}")
	public void eliminaRoles(@PathVariable int xcodr)
	{
		rolesrep.deleteById(xcodr);
	}
}
