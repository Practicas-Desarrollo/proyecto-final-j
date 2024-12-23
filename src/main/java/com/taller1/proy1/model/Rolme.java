package com.taller1.proy1.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="rolme")
public class Rolme {

	
	 @EmbeddedId
		private RolmePK rolme_id;
	    
		@ManyToOne
		@MapsId("codr")
		@JoinColumn(name="codr")
		Roles rol;
		
		@ManyToOne
		@MapsId("codm")
		@JoinColumn(name="codm")
		Menus menu;
		
		public RolmePK getRolme_id() {
			return rolme_id;
		}
		public void setRolme_id(RolmePK rolme_id) {
			this.rolme_id = rolme_id;
		}
		public Roles getRol() {
			return rol;
		}
		public void setRol(Roles rol) {
			this.rol = rol;
		}
		public Menus getMenu() {
			return menu;
		}
		public void setMenu(Menus menu) {
			this.menu = menu;
		}
		
		
}
