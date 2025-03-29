package com.concell.system.modelos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "producto_vendido")
@Getter
@Setter
public class ProductoVendido {

  @EmbeddedId
  private ProductoVendidoId id;

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
}
