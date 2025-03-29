package com.concell.system.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoVendidoId implements Serializable {

  @Column(name = "id_venta")
  private Integer idVenta;

  @Column(name = "id_producto")
  private Integer idProducto;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductoVendidoId that = (ProductoVendidoId) o;
    return Objects.equals(idVenta, that.idVenta) &&
            Objects.equals(idProducto, that.idProducto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idVenta, idProducto);
  }
}
