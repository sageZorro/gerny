package com.gerny.core.controller;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@RequestMapping(value = {"/","/index"})
    public String index(Map<String, Object> model){
        return "index";
    }
/*	@RequestMapping(value = "/logon")
    public String logon(@RequestParam String username,@RequestParam String password) {
		
		
		String userName = null;  
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
		   
        if (principal instanceof UserDetails) {  
            userName = ((UserDetails)principal).getUsername();  
        } else {  
            userName = principal.toString();  
        }  

		 return "forward:/"; 
    }*/
}
