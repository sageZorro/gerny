package com.gerny.core.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sys_role")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleid;
	@Column
	private String rolename;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name= "sys_role_module", joinColumns = { @JoinColumn(name ="roleid", nullable = false, updatable = false) }, inverseJoinColumns= { @JoinColumn(name = "moduleid", nullable = false, updatable =false) })
	@JsonIgnore
	private Set<ModuleEntity> moduleEntities = new HashSet<ModuleEntity>();

	@ManyToMany(fetch= FetchType.LAZY)
	@JoinTable(name= "sys_user_role", joinColumns = {@JoinColumn(name = "roleid", nullable = false, updatable = false) },inverseJoinColumns = { @JoinColumn(name = "userid", nullable =false, updatable = false) })
	@JsonIgnore
	private Set<UserEntity> userEntities = new HashSet<UserEntity>();


	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}


	public Set<ModuleEntity> getModuleEntities() {
		return moduleEntities;
	}

	public void setModuleEntities(Set<ModuleEntity> moduleEntities) {
		this.moduleEntities = moduleEntities;
	}
	
	
	

}
