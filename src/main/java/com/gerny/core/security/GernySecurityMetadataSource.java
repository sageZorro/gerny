package com.gerny.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.gerny.core.entity.ModuleEntity;
import com.gerny.core.entity.RoleEntity;
import com.gerny.core.service.impl.RoleService;

/**
 * 自定义访问资源深度颗粒化 过滤器
 * 
 * @author zorro
 *
 */
@Service
public class GernySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	RoleService roleService;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	// tomcat开启时加载一次，加载所有url和权限（或角色）的对应关系
	
	@PostConstruct
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		} else {
			resourceMap.clear();
		}
		List<RoleEntity> all_roles = roleService.findAll();
		
		for (RoleEntity role : all_roles) {
			ConfigAttribute roleAttr = new SecurityConfig(role.getRolename());  
			Set<ModuleEntity> moduleEntities = role.getModuleEntities();
			
			for(ModuleEntity moduleEntity : moduleEntities){
				String uri = moduleEntity.getModuleuri();
				if (resourceMap.containsKey(uri)) {
					Collection<ConfigAttribute> rolelist = resourceMap.get(uri);
					rolelist.add(roleAttr);
					resourceMap.put(uri, rolelist);
				} else {
					Collection<ConfigAttribute> roleAttrs = new ArrayList<ConfigAttribute>();
					roleAttrs.add(roleAttr);
					resourceMap.put(uri, roleAttrs);
				}
				
				logger.debug("GernySecurityMetadataSource  uri="+uri);
			}
		}
		
		
	}

	
	//http 权限拦截
	public List<ConfigAttribute> getAttributes(Object object) {
		FilterInvocation fi = (FilterInvocation) object;
		String url = fi.getRequestUrl();
		/*String httpMethod = fi.getRequest().getMethod();
		List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();*/
        if (resourceMap == null) {  
            loadResourceDefine();  
        }  
        
        Iterator<String> ite = resourceMap.keySet().iterator();  
        while (ite.hasNext()) {  
            String resURL = ite.next();  
             RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);  
                if(requestMatcher.matches(fi.getHttpRequest())) {  
                	logger.debug("GernySecurityMetadataSource  url="+url);
                	return (List<ConfigAttribute>) resourceMap.get(url);  
            }  
        }  
        
        //TODO 考虑优化
        /*List<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>(); 
        returnCollection.add(new SecurityConfig("ROLE_NO_PERMISSION")); */
    	return null;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<ConfigAttribute>();
	}

	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
}