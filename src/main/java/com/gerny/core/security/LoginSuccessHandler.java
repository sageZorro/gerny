package com.gerny.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.gerny.core.entity.UserEntity;

public class LoginSuccessHandler extends  
        SavedRequestAwareAuthenticationSuccessHandler {  
    @Override    
    public void onAuthenticationSuccess(HttpServletRequest request,    
            HttpServletResponse response, Authentication authentication) throws IOException,    
            ServletException {    
        //获得授权后可得到用户信息   可使用SUserService进行数据库操作  
        UserEntity userDetails = (UserEntity)authentication.getPrincipal();    
       /* Set<SysRole> roles = userDetails.getSysRoles();*/  
        //输出登录提示信息    
        System.out.println("管理员 " + userDetails.getUsername() + " 登录");    
          
                
        super.onAuthenticationSuccess(request, response, authentication);    
    }    
      
}  