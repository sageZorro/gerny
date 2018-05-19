package com.gerny.core.service;

import java.util.List;
import java.util.Map;

import com.gerny.core.entity.RoleEntity;
import com.gerny.core.vo.PageResults;

public interface IRoleService extends IBaseService<RoleEntity, Integer> {
	public PageResults<RoleEntity> findPageByHql(Map<String, String> map);

	List<RoleEntity> findAll();
		
}
