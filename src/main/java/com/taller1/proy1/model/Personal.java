package com.taller1.proy1.model;



import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="personal")
public class Personal {

	@Id
	@GeneratedValue
	@Column(name="codper")
	private int codper;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="appaterno")
	private String appaterno;
	
	@Column(name="apmaterno")
	private String apmaterno;
	
	
	@Column(name="estado")
	private int estado;
	
	@Column(name="tipoper")
	private String tipoper;

	
	@Column(name="foto")
	private String foto;


	
	//@Column(name="token")
//	private String token;
	
	@OneToOne(mappedBy="Usuario")
	private Usuario usuario;
	
	
	//@OneToMany
	//@JoinColumn(name="codper")
	
	
		
@OneToOne(mappedBy="personal")
	private Datos datos;


	public int getCodper() {
		return codper;
	}


	public void setCodper(int codper) {
		this.codper = codper;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	

	public String getAppaterno() {
		return appaterno;
	}


	public void setAppaterno(String appaterno) {
		this.appaterno = appaterno;
	}


	public String getApmaterno() {
		return apmaterno;
	}


	public void setApmaterno(String apmaterno) {
		this.apmaterno = apmaterno;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}


	public String getTipoper() {
		return tipoper;
	}


	public void setTipoper(String tipoper) {
		this.tipoper = tipoper;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Datos getDatos() {
		return datos;
	}


	public void setDatos(Datos datos) {
		this.datos = datos;
	}


	

	
	//public String getToken() {
	//	return token;
	//}

	//public void setToken(String token) {
	//	this.token = token;
	//}
	
	
	



	
	
	
}
