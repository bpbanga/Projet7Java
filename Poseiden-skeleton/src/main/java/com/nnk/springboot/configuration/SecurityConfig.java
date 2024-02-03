package com.nnk.springboot.configuration;

import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration. EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

  
  @Configuration
  
  @EnableWebSecurity
  /**
   * application security configuration class

   */
  public class SecurityConfig {
	  
	  /**
	   * method allowing the filtering of the different access paths on the application as well as the different authorizations on it
	   *value of session
	   *  @param http   instance of HttpSecurity
	   * @return build of http
	   * @throws Exception
	   */
	  @Bean
	  	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
		
		  http.authorizeRequests()
		  .requestMatchers("/login","/user/list", "/css/**")
		  .permitAll() 
		  .anyRequest()
		  .authenticated() 
		  .and() 
		  .formLogin()
		  
		  .loginPage("/login") 
		  .defaultSuccessUrl("/home")
		  .failureUrl("/login?error=true")
		  .usernameParameter("username")
          .passwordParameter("password")
		  .successHandler(this.loginSuccessHandler())
		  .permitAll() 
		  .and() 
		  .logout()
		  .logoutSuccessHandler(this.logoutSuccessHandler())
		  .logoutUrl("/logout")
		  .invalidateHttpSession(true)
		  .deleteCookies("JSESSIONID") ;
		  
		  return http.build();
		 
	
  }
  
	  
  
  
	/**
	 * method allowing encodage of the password
	 * @return new instance of BCryptPasswordEncoder 
	 */
	  @Bean
	  public BCryptPasswordEncoder  passwordEncoder() { 
		  return new  BCryptPasswordEncoder();
	  }

		
	/**
	 * method allowing zuthentification of application
	 * @return new instance of CustutmAuthentificationSuccessHandler
	 */	
	  @Bean public AuthenticationSuccessHandler loginSuccessHandler() {
		  return new CustumAuthentificationSuccesHandler();
		  }
	  
	  /**
	   * method allowing logoff of application
	   * @return new instance of CustumLogoutSuccessHandler
	   */
	  @Bean
	  public LogoutSuccessHandler logoutSuccessHandler() {
	      return new CustumLogoutSuccessHandler();
	  }
		 
	  
	  
	  
  } 
