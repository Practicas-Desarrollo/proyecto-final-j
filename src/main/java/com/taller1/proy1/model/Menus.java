package com.taller1.proy1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menus")
public class Menus {
	
	@Id
	@GeneratedValue
	@Column(name="codm ")
	private int codm ;

	
	@Column(name="nombre ")
	private String nombre ;
	
	@Column(name="estado")
	private int estado;

	public int getCodm() {
		return codm;
	}

	public void setCodm(int codm) {
		this.codm = codm;
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
