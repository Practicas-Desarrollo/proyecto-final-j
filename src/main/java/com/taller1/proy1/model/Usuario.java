package com.taller1.proy1.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@Column(name="login")
	private String login;
	
	@Column(name="estado")
	private int estado;
	
	@Column(name="clave")
	private String clave;

	//relacion n:n a usurol(usuarios)
		@OneToMany(mappedBy = "usuario")
		private List<Usurol> usuarioRoles;
		
		@OneToOne
		@JoinColumn(name="codper")
		private Personal Usuario;
		
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
//	@Column(name="codper")
//	private int codper;
	
	
	
}
