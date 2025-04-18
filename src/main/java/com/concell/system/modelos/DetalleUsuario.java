package com.concell.system.modelos;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "detalle_usuario")
public class DetalleUsuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_detalle_usuario")
  private Integer idDetalleUsuario;

  @OneToOne
  @JoinColumn(name = "id_usuario", nullable = false)
  private Usuario usuario;

  @Column(name = "foto")
  private String foto;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "nombre_usuario")
  private String nombreUsuario;

  @Column(name = "apellido_paterno")
  private String apellidoPaterno;

  @Column(name = "apellido_materno")
  private String apellidoMaterno;

  @Column(name = "fecha_nacimiento")
  private LocalDate fechaNacimiento;

  public DetalleUsuario() {
  }

  public DetalleUsuario(Integer idDetalleUsuario, Usuario usuario, String foto,
                        String nombre, String nombreUsuario, String apellidoPaterno,
                        String apellidoMaterno, LocalDate fechaNacimiento) {
    this.idDetalleUsuario = idDetalleUsuario;
    this.usuario = usuario;
    this.foto = foto;
    this.nombre = nombre;
    this.nombreUsuario = nombreUsuario;
    this.apellidoPaterno = apellidoPaterno;
    this.apellidoMaterno = apellidoMaterno;
    this.fechaNacimiento = fechaNacimiento;
  }

  public Integer getIdDetalleUsuario() {
    return idDetalleUsuario;
  }

  public void setIdDetalleUsuario(Integer idDetalleUsuario) {
    this.idDetalleUsuario = idDetalleUsuario;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombreUsuario() {
    return nombreUsuario;
  }

  public void setNombreUsuario(String nombreUsuario) {
    this.nombreUsuario = nombreUsuario;
  }

  public String getApellidoPaterno() {
    return apellidoPaterno;
  }

  public void setApellidoPaterno(String apellidoPaterno) {
    this.apellidoPaterno = apellidoPaterno;
  }

  public String getApellidoMaterno() {
    return apellidoMaterno;
  }

  public void setApellidoMaterno(String apellidoMaterno) {
    this.apellidoMaterno = apellidoMaterno;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public String obtenerNombreCompleto() {
    return this.nombre + " " + this.apellidoPaterno + " " + this.apellidoMaterno;
  }
}
