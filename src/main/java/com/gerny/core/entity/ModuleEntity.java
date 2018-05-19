package com.gerny.core.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="sys_module")
public class ModuleEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer  moduleid;
	@Column
	private String  modulename;
	@Column
	private String  modulecode;
	
	@Column
	private String  moduleuri;
	
	@Column
	private Integer  parentid;
	
/*	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)  
    @JoinColumn(name = "parentid")
	@JsonIgnore
    public ModuleEntity parentModule;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="parentModule")
	@JsonIgnore
	public Set<ModuleEntity> moduleEntities = new HashSet<ModuleEntity>();*/
	
	
	@ManyToMany(fetch= FetchType.LAZY)
	@JoinTable(name= "sys_role_module", joinColumns = {@JoinColumn(name = "moduleid", nullable = false, updatable = false) },inverseJoinColumns = { @JoinColumn(name = "roleid", nullable =false, updatable = false) })
	@JsonIgnore
	public Set<RoleEntity> roleEntities = new HashSet<RoleEntity>();


	
	
	
	
	
	public Integer getModuleid() {
		return moduleid;
	}


	public void setModuleid(Integer moduleid) {
		this.moduleid = moduleid;
	}


	public String getModulename() {
		return modulename;
	}


	public void setModulename(String modulename) {
		this.modulename = modulename;
	}


	public String getModulecode() {
		return modulecode;
	}


	public void setModulecode(String modulecode) {
		this.modulecode = modulecode;
	}




	public String getModuleuri() {
		return moduleuri;
	}


	public void setModuleuri(String moduleuri) {
		this.moduleuri = moduleuri;
	}


	public Set<RoleEntity> getRoleEntities() {
		return roleEntities;
	}


	public void setRoleEntities(Set<RoleEntity> roleEntities) {
		this.roleEntities = roleEntities;
	}


	public Integer getParentid() {
		return parentid;
	}


	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}




	
	

}
