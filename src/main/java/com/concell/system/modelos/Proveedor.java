package com.concell.system.modelos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "proveedor")
@Getter
@Setter
public class Proveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_proveedor")
	private Integer idProveedor;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido_paterno")
	private String apellidoPaterno;

	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	@Column(name = "contacto")
	private String contacto;

	@Column(name = "tipo_producto")
	private String tipoProducto;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private Estado estado;

	@OneToOne(mappedBy = "proveedor")
	private Compra compra;
}
