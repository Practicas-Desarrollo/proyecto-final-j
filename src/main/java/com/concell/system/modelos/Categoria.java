package com.concell.system.modelos;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_categoria")
  private Integer idCategoria;

  @Column(name = "nombre")
  private String nombre;

  @Enumerated(EnumType.STRING)
  @Column(name = "estado")
  private Estado estado;

  @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Producto> productos = new ArrayList<>();

  public Categoria() {
  }

  public Categoria(Integer idCategoria, String nombre, Estado estado, List<Producto> productos) {
    this.idCategoria = idCategoria;
    this.nombre = nombre;
    this.estado = estado;
    this.productos = productos;
  }

  public Integer getIdCategoria() {
    return this.idCategoria;
  }

  public void setIdCategoria(Integer idCategoria) {
    this.idCategoria = idCategoria;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Estado getEstado() {
    return this.estado;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
  }

  public List<Producto> getProductos() {
    return this.productos;
  }

  public void setProductos(List<Producto> productos) {
    this.productos = productos;
  }
}
