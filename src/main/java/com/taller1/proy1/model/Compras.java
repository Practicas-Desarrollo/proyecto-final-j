package com.taller1.proy1.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="compras")
public class Compras {

	
	@Id
	@GeneratedValue
	@Column(name="codcompra")
	private int codcompra  ;
	
	
	@Column(name="tipocom ")
	private String tipocom ;
	
	
	@Column(name="nombrecompra")
	private String nombrecompra  ;
	
	@Column(name="descripcion ")
	private String descripcion ;
	
	@Column(name="costo")
	private int costo;
	
	@Column(name="fecha")
	private String fecha;
	
	@Column(name="estado")
	private int estado;

	@OneToOne
	@JoinColumn(name="codproveedor")
	private Proveedores proveedores;

	
	public Proveedores getProveedores() {
		return proveedores;
	}


	public void setProveedores(Proveedores proveedores) {
		this.proveedores = proveedores;
	}
	

	public int getCodcompra() {
		return codcompra;
	}


	public void setCodcompra(int codcompra) {
		this.codcompra = codcompra;
	}


	


	public String getNombrecompra() {
		return nombrecompra;
	}


	public void setNombrecompra(String nombrecompra) {
		this.nombrecompra = nombrecompra;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getCosto() {
		return costo;
	}


	public void setCosto(int costo) {
		this.costo = costo;
	}


	


	public String getTipocom() {
		return tipocom;
	}


	public void setTipocom(String tipocom) {
		this.tipocom = tipocom;
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
