package com.concell.system.modelos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "compra")
@Getter
@Setter
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_compra")
	private Integer idCompra;

	@Column(name = "tipo_compra")
	private String tipoCompra;

	@Column(name = "nombre_compra")
	private String nombreCompra;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "costo")
	private BigDecimal costo;

	@Column(name = "fecha")
	private LocalDate fecha;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private Estado estado;

	@OneToOne
	@JoinColumn(name = "id_proveedor")
	private Proveedor proveedor;
}
