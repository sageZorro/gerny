package com.gerny.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gerny.core.entity.RoleEntity;
import com.gerny.core.entity.UserEntity;  
  
/**
 * 本地用户 mapping = security 用户封装
 * @author zorro
 *
 */

public class SecurityUser extends UserEntity implements UserDetails {  
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
    private static final long serialVersionUID = 1L;  
    public SecurityUser(UserEntity user) {  
        if(user != null)  
        {  
        	this.setUserid(user.getUserid());
        	this.setUsername(user.getUsername());
        	this.setPassword(user.getPassword());
        	this.setRoleEntities(user.getRoleEntities());
        }         
    }  
      
    @Override  
    public Collection<? extends GrantedAuthority> getAuthorities() {  
    	logger.debug("SecurityUser roleSize="+this.getRoleEntities().size());
        Collection<GrantedAuthority> authorities = new ArrayList<>();  
        Set<RoleEntity> userRoles = this.getRoleEntities();  
          
        if(userRoles != null)  
        {  
            for (RoleEntity role : userRoles) {  
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRolename());  
                authorities.add(authority);  
            }  
        }  
        return authorities;  
    }  
  
    @Override  
    public String getPassword() {  
        return super.getPassword();  
    }  
  
    @Override  
    public String getUsername() {  
        return super.getUsername();  
    }  
  
    @Override  
    public boolean isAccountNonExpired() {  
        return true;  
    }  
  
    @Override  
    public boolean isAccountNonLocked() {  
        return true;  
    }  
  
    @Override  
    public boolean isCredentialsNonExpired() {  
        return true;  
    }  
  
    @Override  
    public boolean isEnabled() {  
        return true;  
    }  
}  