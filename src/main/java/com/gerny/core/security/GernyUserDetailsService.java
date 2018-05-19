package com.gerny.core.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gerny.core.entity.UserEntity;
import com.gerny.core.service.impl.UserService;

@Component  
public class GernyUserDetailsService implements UserDetailsService {  
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
    @Autowired  //业务服务类  
    private UserService userService;  
  
    @Override  
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {  
    	logger.debug("GernyUserDetailsService loadUserByUsername parame"+userName);
        //SysUser对应数据库中的用户表，是最终存储用户和密码的表，可自定义  
        //本例使用SysUser中的name作为用户名:  
        UserEntity user = userService.findByName(userName);
        if (user == null) {  
            throw new UsernameNotFoundException("UserName " + userName + " not found");  
        }  
        // SecurityUser实现UserDetails并将SysUser的name映射为username  
        SecurityUser seu = new SecurityUser(user);  
        return  seu;  
    }  
  
}  