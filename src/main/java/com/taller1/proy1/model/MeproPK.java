package com.taller1.proy1.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class MeproPK implements Serializable{

	
	protected int codm;
	protected int codp;
	public int getCodm() {
		return codm;
	}
	public void setCodm(int codm) {
		this.codm = codm;
	}
	public int getCodp() {
		return codp;
	}
	public void setCodp(int codp) {
		this.codp = codp;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codm, codp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeproPK other = (MeproPK) obj;
		return codm == other.codm && codp == other.codp;
	}
	
	
}
