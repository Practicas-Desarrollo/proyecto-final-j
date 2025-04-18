package com.concell.system.modelos;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venta")
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
	private List<DetalleVenta> productosVendidos = new ArrayList<>();

	public Venta() {
	}

	public Venta(Integer idVenta, LocalDate fecha, String descripcionGarantia,
	             TipoPago tipoPago, BigDecimal total, Estado estado,
	             Usuario usuario, Cliente cliente, List<DetalleVenta> productosVendidos) {
		this.idVenta = idVenta;
		this.fecha = fecha;
		this.descripcionGarantia = descripcionGarantia;
		this.tipoPago = tipoPago;
		this.total = total;
		this.estado = estado;
		this.usuario = usuario;
		this.cliente = cliente;
		this.productosVendidos = productosVendidos;
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getDescripcionGarantia() {
		return descripcionGarantia;
	}

	public void setDescripcionGarantia(String descripcionGarantia) {
		this.descripcionGarantia = descripcionGarantia;
	}

	public TipoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<DetalleVenta> getProductosVendidos() {
		return productosVendidos;
	}

	public void setProductosVendidos(List<DetalleVenta> productosVendidos) {
		this.productosVendidos = productosVendidos;
	}

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
