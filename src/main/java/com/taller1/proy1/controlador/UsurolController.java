package com.taller1.proy1.controlador;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.taller1.proy1.model.Usurol;
import com.taller1.proy1.repository.UsurolRepo;

@RestController
public class UsurolController {

	
	@Autowired
	public UsurolRepo usurolrepo;
	
	
	@GetMapping({"api/listausurol"})
	
	public List<Usurol> listausurol(){
		return usurolrepo.findAll();
	}
	
	@PostMapping("api/Usurol_login")
	public List<Usurol> Usurol_login(@RequestParam("login") String login, HttpSession session){
		//System.out.println("llego este login::: "+login);
		return usurolrepo.rolesLogin(login);
	}
	
}
