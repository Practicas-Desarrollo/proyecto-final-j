package com.concell.system.modelos;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetalleVentaId implements Serializable {

  @Column(name = "id_venta")
  private Integer idVenta;

  @Column(name = "id_producto")
  private Integer idProducto;

  public DetalleVentaId() {
  }

  public DetalleVentaId(Integer idVenta, Integer idProducto) {
    this.idVenta = idVenta;
    this.idProducto = idProducto;
  }

  public Integer getIdVenta() {
    return idVenta;
  }

  public void setIdVenta(Integer idVenta) {
    this.idVenta = idVenta;
  }

  public Integer getIdProducto() {
    return idProducto;
  }

  public void setIdProducto(Integer idProducto) {
    this.idProducto = idProducto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DetalleVentaId that = (DetalleVentaId) o;
    return Objects.equals(idVenta, that.idVenta) &&
            Objects.equals(idProducto, that.idProducto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idVenta, idProducto);
  }
}
