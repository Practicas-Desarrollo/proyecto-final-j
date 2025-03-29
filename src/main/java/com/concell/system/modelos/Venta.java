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
@Table(name = "venta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venta")
	private Integer idVenta;

	@Column(name = "fecha")
	private LocalDate fecha;

	@Column(name = "descripcion_garantia")
	private String descripcionGarantia;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_pago")
	private TipoPago tipoPago;

	@Column(name = "total")
	private BigDecimal total;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private Estado estado;

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductoVendido> productosVendidos = new ArrayList<>();

	@PrePersist
	@PreUpdate
	public void actualizarTotal() {
		this.total = calcularTotal();
	}

	public BigDecimal calcularTotal() {
		return productosVendidos.stream()
						.map(pv ->
										pv.getPrecioUnitario().multiply(BigDecimal.valueOf(pv.getCantidad())))
						.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
