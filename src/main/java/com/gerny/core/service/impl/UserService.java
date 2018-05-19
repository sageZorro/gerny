package com.gerny.core.service.impl;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerny.core.dao.impl.UserDao;
import com.gerny.core.entity.UserEntity;
import com.gerny.core.service.IUserService;
import com.gerny.core.vo.PageResults;

@Service
@Transactional
public class UserService implements IUserService{
	
	@Autowired
	UserDao userdao;
	
	@Override
	public PageResults<UserEntity> findPageByHql(Map<String, String> map) {
		return	userdao.findPageByHql(map);
	}

	@Override
	public UserEntity findByName(String username) {
		return userdao.findUserByName(username);
	}

	@Override
	public void save(UserEntity t) {
		userdao.save(t);
	}

	@Override
	public UserEntity get(Integer id) {
		return userdao.get(id);
	}

	@Override
	public void delete(UserEntity t) {
		userdao.delete(t);
		
	}

	@Override
	public boolean deleteById(Integer Id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteAll(Collection<UserEntity> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(UserEntity t) {
		// TODO Auto-generated method stub
		
	}
}
