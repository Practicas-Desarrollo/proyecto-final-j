package com.concell.system.modelos;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rol")
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

	@OneToMany(mappedBy = "rol")
	private List<Usuario> usuarios = new ArrayList<>();

	public Rol() {
	}

	public Rol(Integer idRol, String nombre,
	           Estado estado, List<Usuario> usuarios) {
		this.idRol = idRol;
		this.nombre = nombre;
		this.estado = estado;
		this.usuarios = usuarios;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
