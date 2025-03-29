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
public class ProductoCompradoId implements Serializable {

  @Column(name = "id_compra")
  private Integer idCompra;

  @Column(name = "id_producto")
  private Integer idProducto;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductoCompradoId that = (ProductoCompradoId) o;
    return Objects.equals(idCompra, that.idCompra) &&
            Objects.equals(idProducto, that.idProducto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCompra, idProducto);
  }
}
