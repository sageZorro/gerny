package com.gerny.core.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerny.core.dao.impl.RoleDao;
import com.gerny.core.entity.RoleEntity;
import com.gerny.core.service.IRoleService;
import com.gerny.core.vo.PageResults;

@Service
@Transactional
public class RoleService implements IRoleService{
	
	@Autowired
	RoleDao roleDao;
	

	@Override
	public void save(RoleEntity t) {
		roleDao.save(t);
	}

	@Override
	public RoleEntity get(Integer id) {
		return roleDao.get(id);
	}

	@Override
	public void delete(RoleEntity t) {
		roleDao.delete(t);
		
	}

	@Override
	public boolean deleteById(Integer Id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteAll(Collection<RoleEntity> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(RoleEntity t) {
		roleDao.update(t);
	}

	@Override
	public List<RoleEntity> findAll() {
		return roleDao.findAll();
	}
	
	@Override
	public PageResults<RoleEntity> findPageByHql(Map<String, String> map) {
		
		return roleDao.findPageByHql(map);
	}
}
