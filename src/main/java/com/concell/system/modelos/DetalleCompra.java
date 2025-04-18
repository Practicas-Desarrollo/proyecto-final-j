package com.concell.system.modelos;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_compra")
public class DetalleCompra {

  @EmbeddedId
  private DetalleCompraId id;

  @ManyToOne
  @MapsId("idCompra")
  @JoinColumn(name = "id_compra")
  private Compra compra;

  @ManyToOne
  @MapsId("idProducto")
  @JoinColumn(name = "id_producto")
  private Producto producto;

  @Column(name = "cantidad")
  private Integer cantidad;

  @Column(name = "precio_unitario")
  private BigDecimal precioUnitario;

  @Column(name = "estado")
  private Estado estado;

  public DetalleCompra() {
  }

  public DetalleCompra(DetalleCompraId id, Compra compra, Producto producto,
                        Integer cantidad, BigDecimal precioUnitario, Estado estado) {
    this.id = id;
    this.compra = compra;
    this.producto = producto;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
    this.estado = estado;
  }

  public DetalleCompraId getId() {
    return id;
  }

  public void setId(DetalleCompraId id) {
    this.id = id;
  }

  public Compra getCompra() {
    return compra;
  }

  public void setCompra(Compra compra) {
    this.compra = compra;
  }

  public Producto getProducto() {
    return producto;
  }

  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public BigDecimal getPrecioUnitario() {
    return precioUnitario;
  }

  public void setPrecioUnitario(BigDecimal precioUnitario) {
    this.precioUnitario = precioUnitario;
  }

  public Estado getEstado() {
    return estado;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
  }
}
