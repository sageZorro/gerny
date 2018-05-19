package com.gerny.core.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gerny.core.entity.UserEntity;
import com.gerny.core.service.impl.UserService;
import com.gerny.core.vo.PageResults;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping("/")
	public String user() {
		  return "user/user_list";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public PageResults<UserEntity> userlist(@RequestParam Map<String,String> map) {
		return userService.findPageByHql(map);
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(UserEntity user) {
		if(user.getUserid()!=null && user.getUserid()!=0){
			userService.update(user);
		}else{
			userService.save(user);
		}
		
		return "success";
	}
	
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public boolean remove(@PathVariable Integer id) {
		return userService.deleteById(id);
	}

	
}
