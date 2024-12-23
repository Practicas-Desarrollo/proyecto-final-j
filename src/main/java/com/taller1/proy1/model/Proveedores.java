package com.taller1.proy1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="proveedores")
public class Proveedores {
	@Id
	@GeneratedValue
	@Column(name="codproveedor ")
	private int codproveedor ;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="appaterno")
	private String appaterno;
	
	@Column(name="apmaterno")
	private String apmaterno;
	
	@Column(name="contacto ")
	private int contacto ;
	
	@Column(name="tipoproduc ")
	private String tipoproduc ;
	
	@Column(name="estado")
	private int estado;
	
	@OneToOne(mappedBy="proveedores")
	private Compras compras;
	/*
	public Compras getCompras() {
		return compras;
	}

	public void setCompras(Compras compras) {
		this.compras = compras;
	}
	
*/
	public int getCodproveedor() {
		return codproveedor;
	}

	public void setCodproveedor(int codproveedor) {
		this.codproveedor = codproveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAppaterno() {
		return appaterno;
	}

	public void setAppaterno(String appaterno) {
		this.appaterno = appaterno;
	}

	public String getApmaterno() {
		return apmaterno;
	}

	public void setApmaterno(String apmaterno) {
		this.apmaterno = apmaterno;
	}

	public int getContacto() {
		return contacto;
	}

	public void setContacto(int contacto) {
		this.contacto = contacto;
	}

	public String getTipoproduc() {
		return tipoproduc;
	}

	public void setTipoproduc(String tipoproduc) {
		this.tipoproduc = tipoproduc;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	
	
	
	
	
	
}
