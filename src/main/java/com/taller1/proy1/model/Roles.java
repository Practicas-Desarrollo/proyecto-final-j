package com.taller1.proy1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Roles {

	
	@Id
	@GeneratedValue
	@Column(name="codr ")
	private int codr ;
	
	@Column(name="nombre ")
	private String nombre ;
	
	@Column(name="estado")
	private int estado;

	public int getCodr() {
		return codr;
	}

	public void setCodr(int codr) {
		this.codr = codr;
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
