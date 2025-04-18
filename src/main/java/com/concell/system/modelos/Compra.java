package com.concell.system.modelos;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compra")
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
	private List<DetalleCompra> productosComprados = new ArrayList<>();

	public Compra() {
	}

	public Compra(Integer idCompra, LocalDate fecha, String tipoCompra,
	              String nombreCompra, String descripcion, BigDecimal costoTotal,
	              Estado estado, Proveedor proveedor, Usuario usuario,
	              List<DetalleCompra> productosComprados) {
		this.idCompra = idCompra;
		this.fecha = fecha;
		this.tipoCompra = tipoCompra;
		this.nombreCompra = nombreCompra;
		this.descripcion = descripcion;
		this.costoTotal = costoTotal;
		this.estado = estado;
		this.proveedor = proveedor;
		this.usuario = usuario;
		this.productosComprados = productosComprados;
	}

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getTipoCompra() {
		return tipoCompra;
	}

	public void setTipoCompra(String tipoCompra) {
		this.tipoCompra = tipoCompra;
	}

	public String getNombreCompra() {
		return nombreCompra;
	}

	public void setNombreCompra(String nombreCompra) {
		this.nombreCompra = nombreCompra;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(BigDecimal costoTotal) {
		this.costoTotal = costoTotal;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<DetalleCompra> getProductosComprados() {
		return productosComprados;
	}

	public void setProductosComprados(List<DetalleCompra> productosComprados) {
		this.productosComprados = productosComprados;
	}

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
