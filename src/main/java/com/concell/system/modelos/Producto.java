package com.concell.system.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "producto")
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

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetalleCompra> productosComprados = new ArrayList<>();

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetalleVenta> productosVendidos = new ArrayList<>();

	public Producto() {
	}

	public Producto(Integer idProducto, String nombre, String descripcion,
	                BigDecimal precio, Integer cantidad, Estado estado,
	                Categoria categoria, List<DetalleCompra> productosComprados,
	                List<DetalleVenta> productosVendidos) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = cantidad;
		this.estado = estado;
		this.categoria = categoria;
		this.productosComprados = productosComprados;
		this.productosVendidos = productosVendidos;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<DetalleCompra> getProductosComprados() {
		return productosComprados;
	}

	public void setProductosComprados(List<DetalleCompra> productosComprados) {
		this.productosComprados = productosComprados;
	}

	public List<DetalleVenta> getProductosVendidos() {
		return productosVendidos;
	}

	public void setProductosVendidos(List<DetalleVenta> productosVendidos) {
		this.productosVendidos = productosVendidos;
	}
}
