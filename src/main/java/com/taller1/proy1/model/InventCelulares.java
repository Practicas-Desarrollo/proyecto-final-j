package com.taller1.proy1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="inventCelulares")
public class InventCelulares {
	
	@Id
	@GeneratedValue
	@Column(name="codInventCel   ")
	private int codInventCel   ;
	
	@Column(name="nombre ")
	private String nombre ;
	
	@Column(name="precio")
	private int precio;
	
	@Column(name="cantidad")
	private int cantidad;
	
	@Column(name="estado")
	private int estado;
	
	@ManyToOne
	@MapsId("codproducto")
	@JoinColumn(name="codproducto")
	Productos producto;

	public int getCodInventCel() {
		return codInventCel;
	}

	public void setCodInventCel(int codInventCel) {
		this.codInventCel = codInventCel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	
	
	
}
