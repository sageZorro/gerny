package com.gerny.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gerny.core.entity.ModuleEntity;

@Repository
public class ModuleDao extends BaseDao<ModuleEntity, Integer>{

	public List<ModuleEntity> findALL(){
		String hql = new String("from ModuleEntity");
		return super.getListByHQL(hql);
	}
	
	public ModuleEntity findUserByName(String username){
		String hql = new String("from ModuleEntity where username=?");
		return super.getByHQL(hql,username);
	}
}
