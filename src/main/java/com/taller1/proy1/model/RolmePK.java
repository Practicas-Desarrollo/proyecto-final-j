package com.taller1.proy1.model;

import java.io.Serializable;
import java.util.Objects;

public class RolmePK implements Serializable{

	protected int codm;
	protected int codr;
	public int getCodm() {
		return codm;
	}
	public void setCodm(int codm) {
		this.codm = codm;
	}
	public int getCodr() {
		return codr;
	}
	public void setCodr(int codr) {
		this.codr = codr;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codm, codr);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolmePK other = (RolmePK) obj;
		return codm == other.codm && codr == other.codr;
	}
	
	
}
