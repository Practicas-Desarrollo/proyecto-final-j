package com.concell.system.modelos;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetalleCompraId implements Serializable {

  @Column(name = "id_compra")
  private Integer idCompra;

  @Column(name = "id_producto")
  private Integer idProducto;

  public DetalleCompraId() {
  }

  public DetalleCompraId(Integer idCompra, Integer idProducto) {
    this.idCompra = idCompra;
    this.idProducto = idProducto;
  }

  public Integer getIdCompra() {
    return idCompra;
  }

  public void setIdCompra(Integer idCompra) {
    this.idCompra = idCompra;
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
    DetalleCompraId that = (DetalleCompraId) o;
    return Objects.equals(idCompra, that.idCompra) &&
            Objects.equals(idProducto, that.idProducto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCompra, idProducto);
  }
}
