package com.gerny.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_org")
public class OrgEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer  orgid;
	@Column
	private String  orgcode;
	@Column
	private String  orgname;
	@Column
	private String  parentcode;

	
}
