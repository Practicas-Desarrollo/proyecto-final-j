package com.concell.system.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "producto")
@Getter
@Setter
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private Integer idProducto;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "precio")
	private BigDecimal precio;

	@Column(name = "cantidad")
	private Integer cantidad;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private Estado estado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria", nullable = false)
	@JsonIgnore()
	private Categoria categoria;
}
