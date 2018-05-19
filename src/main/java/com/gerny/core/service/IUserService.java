package com.gerny.core.service;

import java.util.Map;

import com.gerny.core.entity.UserEntity;
import com.gerny.core.vo.PageResults;

public interface IUserService extends IBaseService<UserEntity, Integer> {
	public PageResults<UserEntity> findPageByHql(Map<String, String> map);
	public UserEntity findByName(String usernmae);
		
}
