package com.gerny.core.security;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;


@Component
public class GernySecurityFilter extends FilterSecurityInterceptor implements Filter {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired  
    private GernySecurityMetadataSource  gernySecurityMetadataSource;  
      
   /* @Autowired  
    private GernyAccessDecisionManager gernyAccessDecisionManager;  */
    @Autowired
    public void setMyAccessDecisionManager(GernyAccessDecisionManager gernyAccessDecisionManager) {
        super.setAccessDecisionManager(gernyAccessDecisionManager);
    }
      
  /*  @Autowired  
    private AuthenticationManager authenticationManager; */ 

	@Override
	public void destroy() {
		
	}

	/*@PostConstruct  
    public void init(){  
        super.setAuthenticationManager(authenticationManager);  
        super.setAccessDecisionManager(gernyAccessDecisionManager);  
        
        logger.debug("GernySecurityFilter init");
    }  
	*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation( request, response, chain );  
        invoke(fi);  
        logger.debug("GernySecurityFilter dofilter");
	}


	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class; 
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		logger.debug("gernySecurityMetadataSource.size = "+gernySecurityMetadataSource.getAllConfigAttributes().size());
        return this.gernySecurityMetadataSource;  
	}

	
	 public void invoke( FilterInvocation fi ) throws IOException, ServletException{  
	        InterceptorStatusToken  token = super.beforeInvocation(fi);  
	        try{  
	            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());  
	        }finally{  
	            super.afterInvocation(token, null);  
	        }  
	          
	    }  
}
