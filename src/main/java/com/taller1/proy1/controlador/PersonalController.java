package com.taller1.proy1.controlador;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.taller1.proy1.model.Personal;

import com.taller1.proy1.repository.PersonalRepo;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class PersonalController {
	@Autowired
	public PersonalRepo personalrepo;
	

	@GetMapping({"/api/listaper"})
	
	public List<Personal> listaper(){
		return personalrepo.findAll();
	}
	
	//SEGURIDAD
	
	@PostMapping("/api/login")
	public Personal login2(@RequestParam("login") String login, @RequestParam("clave") String clav ) {
		
		return  personalrepo.verificaCuentasolomio(login,clav);
		//try {
			//String xtoken = getJWTToken(xlogin);
			//System.out.println("este es mi TOKEN generado::"+ xtoken);
		//	per.setToken(xtoken);
		//} catch (Exception e) {
		//	per = null;
	//	}
		//return per;
	}
	

	
	@Transactional
	@PostMapping("/api/addper") 
	public String guardarPer(@RequestBody Personal per) {
		
		//System.out.println("personal::"+per.getNombre()+"-"+per.getAp()+"-"+per.getAm()+"-"+per.getEstado()+"-"+per.getFnac()+"-"+per.getEcivil()+"-"+per.getGenero()+"-"+per.getDirec()+"-"+per.getTipo()+"-"+per.getFoto());
//	System.out.println("datos::"+per.getDatos().getCi());
		//Insertar datos a PERSONAL
		int res1= personalrepo.addPersonal
				(per.getNombre(),
						
						per.getAppaterno(),
						per.getApmaterno(),
				per.getEstado(),
				per.getTipoper(),
				per.getFoto());
		
		
		//Extrae CODPER de la  tabla Personal
		int xcodper= personalrepo.maxPersonal();
		//extrae cod de personal
		System.out.println("maximo::"+xcodper);
		
		//Insertar datos en la tabla DATOS
		int res2= personalrepo.addDatos(xcodper, per.getDatos().getCi());
		
		
		return "OK";
	}
	
	
	
	@Transactional
	@PutMapping("/api/modper/{xcodper}")
	public void modper(@PathVariable int xcodper, @RequestBody Personal per) {
		Personal personal =new Personal();
		personal.setCodper(xcodper);
		personal.setNombre(per.getNombre());
		personal.setAppaterno(per.getAppaterno());
		personal.setApmaterno(per.getApmaterno());
		
		personal.setEstado(per.getEstado());
		personal.setTipoper(per.getTipoper());

		personal.setFoto(per.getFoto());
		personalrepo.save(personal);
		System.out.println("se modificaron datos");
		 
	}
	@DeleteMapping("/api/delpersonal/{xcodper}")
	public void eliminarParalelo(@PathVariable int xcodper)
	{
		personalrepo.deleteById(xcodper);
	}
	
	@GetMapping({"api/listafiltron3"})
	public List<Personal> listaFiltros(@RequestParam("estado") int estado, HttpSession session){
		if(estado == -1)
			return personalrepo.listafiltro(0,1);
		else if (estado != -1)
			return personalrepo.listafiltro(estado,estado);
		else
			return personalrepo.listafiltro(estado,estado);

	}

	
	//genera el TOKEN
		//private String getJWTToken(String username) {
			//String secretKey = "mySecretKey";
		//	List<GrantedAuthority> grantedAuthorities = AuthorityUtils
		//			.commaSeparatedStringToAuthorityList("ROLE_USER");
			
		//	String token = Jwts
		//			.builder()
		//			.setId("softtekJWT")
		//			.setSubject(username)
		//			.claim("authorities",
		//					grantedAuthorities.stream()
			//						.map(GrantedAuthority::getAuthority)
			//						.collect(Collectors.toList()))
			//		.setIssuedAt(new Date(System.currentTimeMillis()))
		//			.setExpiration(new Date(System.currentTimeMillis() + 600000))
			//		.signWith(SignatureAlgorithm.HS512,
		//					secretKey.getBytes()).compact();
		//	return "Bearer " + token;
	//	}
	
}
