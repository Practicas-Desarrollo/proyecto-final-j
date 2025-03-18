package com.concell.system.modelos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "venta")
@Getter
@Setter
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venta")
	private Integer idVenta;

	@Column(name = "nit")
	private String nit;

	@Column(name = "nombre_cliente")
	private String nombreCliente;

	@Column(name = "monto")
	private BigDecimal monto;

	@Column(name = "descripcion_garantia")
	private String descripcionGarantia;

	@Column(name = "fecha")
	private LocalDate fecha;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private Estado estado;
}
