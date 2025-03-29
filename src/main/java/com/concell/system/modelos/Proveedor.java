package com.concell.system.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proveedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

	@Column(name = "nit")
	private String nit;

	@Column(name = "contacto")
	private String contacto;

	@Column(name = "tipo_producto")
	private String tipoProducto;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private Estado estado;

	@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Compra> compras = new ArrayList<>();

	public String obtenerNombreCompleto() {
		return this.nombre + " " + this.apellidoPaterno + " " + this.apellidoMaterno;
	}
}
