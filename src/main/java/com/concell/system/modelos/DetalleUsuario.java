package com.concell.system.modelos;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "detalle_usuario")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime fechaCreacion;

  @LastModifiedDate
  private LocalDateTime fechaModificacion;

  public String obtenerNombreCompleto() {
    return this.nombre + " " + this.apellidoPaterno + " " + this.apellidoMaterno;
  }
}
