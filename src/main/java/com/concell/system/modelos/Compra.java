package com.concell.system.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_compra")
	private Integer idCompra;

	@Column(name = "fecha")
	private LocalDate fecha;

	@Column(name = "tipo_compra")
	private String tipoCompra;

	@Column(name = "nombre_compra")
	private String nombreCompra;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "costo_total")
	private BigDecimal costoTotal;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private Estado estado;

	@ManyToOne
	@JoinColumn(name = "id_proveedor", nullable = false)
	private Proveedor proveedor;

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductoComprado> productosComprados = new ArrayList<>();

	@PrePersist
	@PreUpdate
	public void actualizarCostoTotal() {
		this.costoTotal = calcularCostoTotal();
	}

	public BigDecimal calcularCostoTotal() {
		return productosComprados.stream()
						.map(pc ->
										pc.getPrecioUnitario().multiply(BigDecimal.valueOf(pc.getCantidad())))
						.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
