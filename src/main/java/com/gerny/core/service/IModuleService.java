package com.gerny.core.service;

import java.util.List;

import com.gerny.core.entity.ModuleEntity;

public interface IModuleService extends IBaseService<ModuleEntity, Integer> {
	public List<ModuleEntity> findAll();
}
