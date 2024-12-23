package com.taller1.proy1.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="repuestos")
public class Repuestos {

	
	@Id
	@GeneratedValue
	@Column(name="codtiporep")
	private int codtiporep ;
	
	
	@Column(name="nombrerep")
	private String nombrerep  ;
	
	@Column(name="descripcionrep  ")
	private String descripcionrep  ;
	
	
	@Column(name="estado")
	private int estado;


	public int getCodtiporep() {
		return codtiporep;
	}


	public void setCodtiporep(int codtiporep) {
		this.codtiporep = codtiporep;
	}


	public String getNombrerep() {
		return nombrerep;
	}


	public void setNombrerep(String nombrerep) {
		this.nombrerep = nombrerep;
	}




	public String getDescripcionrep() {
		return descripcionrep;
	}


	public void setDescripcionrep(String descripcionrep) {
		this.descripcionrep = descripcionrep;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}



	
	
}
