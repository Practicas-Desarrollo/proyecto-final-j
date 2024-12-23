package com.taller1.proy1.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ventas")
public class Ventas {

	@Id
	@GeneratedValue
	@Column(name=" codventas  ")
	private int codventas  ;
	
	@Column(name="nombrecliente  ")
	private String nombrecliente  ;
	
	@Column(name="monto")
	private int monto;
	
	
	@Column(name="nit")
	private int nit;
	
	@Column(name="descripciongarantia   ")
	private String descripciongarantia   ;
	
	
	@Column(name="fecha")
	private String fecha;
	
	@Column(name="estado")
	private int estado;

	
	
	//relacion 1:n detalleventa
		@OneToMany(mappedBy = "venta")
		private List<detalleventas> detalleventa;
		
		
	public int getCodventas() {
		return codventas;
	}

	public void setCodventas(int codventas) {
		this.codventas = codventas;
	}

	public String getNombrecliente() {
		return nombrecliente;
	}

	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public int getNit() {
		return nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public String getDescripciongarantia() {
		return descripciongarantia;
	}

	public void setDescripciongarantia(String descripciongarantia) {
		this.descripciongarantia = descripciongarantia;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	
}
