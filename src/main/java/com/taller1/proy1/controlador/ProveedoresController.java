package com.taller1.proy1.controlador;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.proy1.model.Proveedores;
import com.taller1.proy1.repository.GProveedoresRepo;

@RestController
public class ProveedoresController {
	
	
	@Autowired
	public GProveedoresRepo proveedoresrepo;
	
	
	@GetMapping("/api/listaproveedores")
	public List<Proveedores>listaproveedores(){
		return proveedoresrepo.findAll();
		
	}
	

	@PostMapping("/api/addproveedores")
	public void guardarproveedores(@RequestBody Proveedores prov) {
		proveedoresrepo.save(prov);
		
		
	}
	
	@PutMapping("/api/modproveedores/{xcodProveedor}")
	public void modificarproveedores(@PathVariable int xcodProveedor, @RequestBody Proveedores prov) {
		Proveedores pro = new Proveedores();
		pro.setCodproveedor(xcodProveedor);
		pro.setNombre(prov.getNombre());
		pro.setAppaterno(prov.getAppaterno());
		pro.setApmaterno(prov.getApmaterno());
		pro.setContacto(prov.getContacto());
		pro.setTipoproduc(prov.getTipoproduc());
		pro.setEstado(prov.getEstado());
		
		proveedoresrepo.save(pro);
	}
	
	
		@DeleteMapping("/api/delproveedores/{xcodProveedor}")
		
		public void eliminarProveedor(@PathVariable int xcodProveedor) {
			proveedoresrepo.deleteById(xcodProveedor);
			
		}
	
	
	
}
