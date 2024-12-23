package com.taller1.proy1.model;
import java.io.Serializable;
import java.util.Objects;

public class DetalleVentaPK implements Serializable {

	
	int codventas;
	int codproducto;
	
	
	public DetalleVentaPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DetalleVentaPK(int codventas, int codproducto) {
		super();
		this.codventas = codventas;
		this.codproducto = codproducto;
	}

	public int getCodventas() {
		return codventas;
	}

	public void setCodventas(int codventas) {
		this.codventas = codventas;
	}

	public int getCodproducto() {
		return codproducto;
	}

	public void setCodproducto(int codproducto) {
		this.codproducto = codproducto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codproducto, codventas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleVentaPK other = (DetalleVentaPK) obj;
		return codproducto == other.codproducto && codventas == other.codventas;
	}
	
	
	
}
