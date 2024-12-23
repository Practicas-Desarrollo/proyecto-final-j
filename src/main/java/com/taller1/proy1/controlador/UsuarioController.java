package com.taller1.proy1.controlador;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.proy1.model.Personal;
import com.taller1.proy1.model.Usuario;
import com.taller1.proy1.repository.UsuarioRepo;

@RestController
public class UsuarioController {
	
	@Autowired
	 UsuarioRepo usuariorepo;
	
	@GetMapping({"api/listausu"})
	public List<Usuario> listausu(){
		return usuariorepo.findAll();
	}
	@Transactional
	@PostMapping("/api/addusuario")
	public String guardarusuarios(@RequestBody Personal per) {
		int usu = usuariorepo.addusuarios(per.getUsuario().getLogin(),per.getUsuario().getEstado(),per.getUsuario().getClave(),per.getCodper());
		return "ok";
	}
	
	@Transactional
	@PutMapping("/api/modUsu/{xcodper}")  //UPDATE  
	public int modificarUsuario(@PathVariable int xcodper, @RequestBody Personal per){
		return usuariorepo.modUsuario(per.getUsuario().getClave(), xcodper);

	}

}
