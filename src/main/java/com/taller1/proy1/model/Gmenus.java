package com.taller1.proy1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gmenus")
public class Gmenus {
	
	@Id
	@GeneratedValue
	@Column(name="codmenus")
	private int codmenus;
		
	@Column(name="nombre")
	private String nombre;
		
	@Column(name="estado")
	private int estado;

	

	public int getCodmenus() {
		return codmenus;
	}

	public void setCodmenus(int codmenus) {
		this.codmenus = codmenus;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	

}
