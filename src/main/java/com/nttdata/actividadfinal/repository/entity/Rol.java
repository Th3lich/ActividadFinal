package com.nttdata.actividadfinal.repository.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Table
public class Rol implements GrantedAuthority {

	@Id
	@Column
	private Integer id;
	
	@Column
	private String rol;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	/*
	 * 'ADMIN' -> 'ROLE_ADMIN'
	 * 'GESTOR' -> 'ROLE_GESTOR'
	 */
	@Override
	public String getAuthority() {
		return ("ROLE_" + this.rol).toUpperCase();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		return Objects.equals(id, other.id);
	}
}
