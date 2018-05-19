package com.gerny.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gerny.core.entity.ModuleEntity;
import com.gerny.core.service.impl.ModuleService;

@Controller
@RequestMapping("/module")
public class ModuleController {
	
	@Autowired
	ModuleService moduleService;
	
	/**
	 * to_view
	 * @return
	 */
	@RequestMapping("/")
	public String Module() {
		  return "module/module_list";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<ModuleEntity> Modulelist(@RequestParam Map<String,String> map) {
		return moduleService.findAll();
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(ModuleEntity module) {
		if(module.getModuleid()!=null && module.getModuleid()!=0){
			ModuleEntity entity = moduleService.get(module.getModuleid());
			if(!StringUtils.isEmpty(module.getModulecode())){
				entity.setModulecode(module.getModulecode());
			}
			if(!StringUtils.isEmpty(module.getModulename())){
				entity.setModulename(module.getModulename());
			}
			if(!StringUtils.isEmpty(module.getModuleuri())){
				entity.setModuleuri(module.getModuleuri());
			}
			if(!StringUtils.isEmpty(module.getParentid())){
				entity.setParentid(module.getParentid());
			}
			moduleService.update(module);
		}else{
			moduleService.save(module);
			return String.valueOf(module.getModuleid());
		}
		return "success";
	}
	
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public boolean remove(@PathVariable Integer id) {
		return moduleService.deleteById(id);
	}
}
