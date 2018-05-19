package com.gerny.core.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="sys_user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer  userid;
	@Column
	private String  username;
	@Column
	private String  password;
	@Column
	private String  truename;
	@Column
	private String  phone;
	@Column
	private String  mobile;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date  createdate;
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name= "sys_user_role", joinColumns = { @JoinColumn(name ="userid", nullable = false, updatable = false) }, inverseJoinColumns= { @JoinColumn(name = "roleid", nullable = false, updatable =false) })
	@JsonIgnore
	private Set<RoleEntity> roleEntities = new HashSet<RoleEntity>();

	
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Set<RoleEntity> getRoleEntities() {
		return roleEntities;
	}
	public void setRoleEntities(Set<RoleEntity> roleEntities) {
		this.roleEntities = roleEntities;
	}

	
	
	
	
}
