package com.taller1.proy1.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="usurol")
public class Usurol {

	
	 @EmbeddedId
		private UsurolPK usurol_id;
	 
	 @ManyToOne
		@MapsId("codr")
		@JoinColumn(name="codr")
		Roles roles;

		@ManyToOne
		@MapsId("login")
		@JoinColumn(name="login")
		Usuario usuario;

		public UsurolPK getRolusu_id() {
			return usurol_id;
		}

		public void setRolusu_id(UsurolPK rolusu_id) {
			this.usurol_id = rolusu_id;
		}

		public Roles getRoles() {
			return roles;
		}

		public void setRoles(Roles roles) {
			this.roles = roles;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		
		
}
