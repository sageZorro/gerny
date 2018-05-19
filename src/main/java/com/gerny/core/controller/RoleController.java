package com.gerny.core.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gerny.core.entity.ModuleEntity;
import com.gerny.core.entity.RoleEntity;
import com.gerny.core.service.impl.ModuleService;
import com.gerny.core.service.impl.RoleService;
import com.gerny.core.vo.PageResults;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	ModuleService moduleService;
	
	
	@RequestMapping("/")
	public String role() {
		  return "role/role_list";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public PageResults<RoleEntity> rolelist(@RequestParam Map<String,String> map) {
		return roleService.findPageByHql(map);
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(RoleEntity role) {
		if(role.getRoleid()!=null && role.getRoleid()!=0){
			roleService.update(role);
		}else{
			roleService.save(role);
		}
		
		return "success";
	}
	
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public boolean remove(@PathVariable Integer id) {
		return roleService.deleteById(id);
	}

	@RequestMapping("/module/{id}")
	@ResponseBody
	public Set<ModuleEntity> module(@PathVariable Integer id) {
		return roleService.get(id).getModuleEntities();
	}
	
	
	@RequestMapping("/module/edit/{id}")
	@ResponseBody
	public String moduleEdit(@PathVariable Integer id,@RequestParam(value = "modules[]") String[] modules) {
		RoleEntity roleEntity = roleService.get(id);
		roleEntity.getModuleEntities().clear();
		Set<ModuleEntity> moduleEntities = new HashSet<>();
		if(modules !=null && modules.length >0){
			for(int i = 0 ;i<modules.length;i++){
				moduleEntities.add(moduleService.get(Integer.valueOf(modules[i])));
			}
		}
		roleEntity.setModuleEntities(moduleEntities);
		return "success";
	}
}
