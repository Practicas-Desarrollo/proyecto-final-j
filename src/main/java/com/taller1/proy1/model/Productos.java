package com.taller1.proy1.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="productos")
public class Productos {

	
	@Id
	@GeneratedValue
	@Column(name="codproducto ")
	private int codproducto ;
	
	
	@Column(name="nombre ")
	private String nombre ;
	
	@Column(name="precio")
	private int precio;
	
	@Column(name="cantidad")
	private int cantidad;
	
	@Column(name="descripcion ")
	private String descripcion ;
	
	@Column(name="estado")
	private int estado;
	

	//relacion 1:n detalleventa
		@OneToMany(mappedBy = "producto")
		private List<detalleventas> detalleventa;

	public int getCodproducto() {
		return codproducto;
	}

	public void setCodproducto(int codproducto) {
		this.codproducto = codproducto;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
