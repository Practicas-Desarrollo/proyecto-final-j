package com.taller1.proy1.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="detalleventas")
public class detalleventas {

	
	@EmbeddedId
	private DetalleVentaPK detalleventaID = new DetalleVentaPK();
	
	//relacion 1:N detalle venta(ventas)
		@ManyToOne
		@MapsId("codventas")
		@JoinColumn(name = "codventas")
		Ventas venta;
		
		//relacion N:N detalleventa (productos)
		@ManyToOne
		@MapsId("codproducto")
		@JoinColumn(name = "codproducto")
		Productos producto;
		
		@Column(name = "cantidad")
		private int cantidad;
		

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}

		public Ventas getVenta() {
			return venta;
		}

		public void setVenta(Ventas venta) {
			this.venta = venta;
		}

		public Productos getProducto() {
			return producto;
		}

		public void setProducto(Productos producto) {
			this.producto = producto;
		}

		public detalleventas() {
			super();
			// TODO Auto-generated constructor stub
		}

		public detalleventas(DetalleVentaPK detalleventaID, Ventas venta, Productos producto, int cantidad) {
			super();
			this.detalleventaID = detalleventaID;
			this.venta = venta;
			this.producto = producto;
			this.cantidad = cantidad;
		}

	
	
}
