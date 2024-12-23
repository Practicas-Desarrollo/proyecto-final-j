package com.taller1.proy1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="procesos")
public class Procesos {
	
	@Id
	@Column(name="codp ")
	private int codp ;
	
	@Column(name="nombre ")
	private String nombre ;
	
	@Column(name="enlace  ")
	private String enlace  ;
	
	@Column(name="ayuda  ")
	private String ayuda  ;
	
	@Column(name="estado")
	private int estado;

	
	public int getCodp() {
		return codp;
	}

	public void setCodp(int codp) {
		this.codp = codp;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public String getAyuda() {
		return ayuda;
	}

	public void setAyuda(String ayuda) {
		this.ayuda = ayuda;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}
