package com.concell.system.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rol")
@EntityListeners(AuditingEntityListener.class)
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Integer idRol;

	@Column(name = "nombre", unique = true)
	private String nombre;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private Estado estado;

	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private List<Usuario> usuarios;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime fechaCreacion;

	@LastModifiedDate
	@Column(insertable = false)
	private LocalDateTime fechaModificacion;
}
