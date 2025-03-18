package com.concell.system.modelos;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
				name = "usuario",
				uniqueConstraints = {
								@UniqueConstraint(columnNames = {"email"})
				}
			)
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;

	@Column(name = "foto")
	private String foto;

	@Column(name = "nombre_usuario")
	private String nombreUsuario;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido_paterno")
	private String apellidoPaterno;

	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private Estado estado;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Rol> roles;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime fechaCreacion;

	@LastModifiedDate
	@Column(insertable = false)
	private LocalDateTime fechaModificacion;

	public String obtenerNombreCompleto() {
		return this.nombre + " " + this.apellidoPaterno + " " + this.apellidoMaterno;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
