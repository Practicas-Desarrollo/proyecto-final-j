package com.taller1.proy1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

@Entity
@Table(name="datos")
public class Datos {
	
	
	
	@Id
	@Column(name="ci")
	private String ci;

	

	public String getCi() {
		return ci;
	}



	public void setCi(String ci) {
		this.ci = ci;
	}



	@OneToOne
	@JoinColumn(name="codper")
	private Personal personal;




	
	
	
	

}
 