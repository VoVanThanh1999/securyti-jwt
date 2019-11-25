package com.example.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Roles {
	@Id
	@Column(name="id_roles")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRole;
	
	@Column(name="role_name")
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
	Set<User> users;
	
	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
