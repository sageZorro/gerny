package com.gerny.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.gerny.core.security.GernyAuthenticationProvider;
import com.gerny.core.security.GernySecurityFilter;
import com.gerny.core.security.GernyUserDetailsService;
import com.gerny.core.security.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired					
	private GernySecurityFilter gernySecurityFilter;    
	@Autowired  
	private GernyUserDetailsService gernyUserDetailsService;  
	
	@Autowired
    private GernyAuthenticationProvider authenticationProvider;//自定义验证
	
	@Override  
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
        /*auth.userDetailsService(gernyUserDetailsService);   去掉，不然会有两次*/
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
    	http 
        .addFilterBefore(gernySecurityFilter, FilterSecurityInterceptor.class)
        .authorizeRequests()  
        /*.antMatchers("/login","/logon").permitAll()//访问：/home 无需登录认证权限  
*/        .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问  
        .and()  
        .formLogin()  
        .loginPage("/login")//指定登录页是”/login”  
        .permitAll()  
        .successHandler(loginSuccessHandler()) //登录成功后可使用loginSuccessHandler()存储用户信息，可选。  
        .and()  
        .logout()  
        .logoutSuccessUrl("/login") //退出登录后的默认网址是”/login”  
        .permitAll();  
        /*.invalidateHttpSession(true)  ;*/
    }
    
    
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
    	super.configure(web);
    }
    @Override  
    public AuthenticationManager authenticationManagerBean() throws Exception {  
       
    	return super.authenticationManagerBean();  
       
    }  
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       /* auth
            .inMemoryAuthentication()
                .withUser("admin").password("123456").roles("USER");*/
    	auth.userDetailsService(gernyUserDetailsService).passwordEncoder(passwordEncoder()); 
        auth.eraseCredentials(false); 
    }  
    @Bean  
    public LoginSuccessHandler loginSuccessHandler(){  
        return new LoginSuccessHandler();  
    } 
    @Bean  
    public BCryptPasswordEncoder passwordEncoder() {  
        return new BCryptPasswordEncoder();  
    }
    
 /*   @Bean  
    public AuthenticationProvider authenticationProvider(){  
        AuthenticationProvider authenticationProvider=new GernyAuthenticationProvider(userDetailsService());  
        return authenticationProvider;  
    } */
    
}