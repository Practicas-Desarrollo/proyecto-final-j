package com.taller1.proy1.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="mepro")
public class Mepro {
	
	@EmbeddedId
	private MeproPK mepro_id;
	
	@ManyToOne
	@MapsId("codp")
	@JoinColumn(name="codp")
	Procesos procesos;

	@ManyToOne
	@MapsId("codm")
	@JoinColumn(name="codm")
	Menus menus;

	public MeproPK getMepro_id() {
		return mepro_id;
	}

	public void setMepro_id(MeproPK mepro_id) {
		this.mepro_id = mepro_id;
	}

	public Procesos getProcesos() {
		return procesos;
	}

	public void setProcesos(Procesos procesos) {
		this.procesos = procesos;
	}

	public Menus getMenus() {
		return menus;
	}

	public void setMenus(Menus menus) {
		this.menus = menus;
	}
	
	
}
