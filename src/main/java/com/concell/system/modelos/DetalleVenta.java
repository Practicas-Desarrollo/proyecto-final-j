package com.concell.system.modelos;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

  @EmbeddedId
  private DetalleVentaId id;

  @ManyToOne
  @MapsId("idVenta")
  @JoinColumn(name = "id_venta")
  private Venta venta;

  @ManyToOne
  @MapsId("idProducto")
  @JoinColumn(name = "id_producto")
  private Producto producto;

  @Column(name = "cantidad")
  private Integer cantidad;

  @Column(name = "precio_unitario")
  private BigDecimal precioUnitario;

  public DetalleVenta() {
  }

  public DetalleVenta(DetalleVentaId id, Venta venta, Producto producto,
                      Integer cantidad, BigDecimal precioUnitario) {
    this.id = id;
    this.venta = venta;
    this.producto = producto;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
  }

  public DetalleVentaId getId() {
    return id;
  }

  public void setId(DetalleVentaId id) {
    this.id = id;
  }

  public Venta getVenta() {
    return venta;
  }

  public void setVenta(Venta venta) {
    this.venta = venta;
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
}
