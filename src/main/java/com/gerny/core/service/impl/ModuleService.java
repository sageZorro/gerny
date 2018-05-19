package com.gerny.core.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerny.core.dao.impl.ModuleDao;
import com.gerny.core.entity.ModuleEntity;
import com.gerny.core.service.IModuleService;

@Service
@Transactional
public class ModuleService implements IModuleService{
	
	@Autowired
	ModuleDao moduleDao;
	


	@Override
	public List<ModuleEntity> findAll() {
		return moduleDao.findALL();
	}



	@Override
	public boolean deleteById(Integer Id) {
		return moduleDao.deleteById(Id);
	}



	@Override
	public void save(ModuleEntity t) {
		moduleDao.save(t);
		
	}



	@Override
	public ModuleEntity get(Integer id) {
		return moduleDao.get(id);
	}



	@Override
	public void delete(ModuleEntity t) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deleteAll(Collection<ModuleEntity> entities) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void update(ModuleEntity t) {
		// TODO Auto-generated method stub
		
	}



}
