package com.concell.system.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "producto_comprado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoComprado {

  @EmbeddedId
  private ProductoCompradoId id;

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
}
