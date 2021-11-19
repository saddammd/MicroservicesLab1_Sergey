package com.appsdeveloperblog.photoapp.api.albums.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CustomWebSecurity extends WebSecurityConfigurerAdapter {

	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.httpBasic().disable();
		http.csrf().disable();
		http.addFilter(getAuthorizationFilter());
		http.headers().frameOptions().disable();
		http.addFilter(getAuthorizationFilter());
	 		
	}
	
		
	public AuthorizationFilter getAuthorizationFilter() throws Exception {
		return new AuthorizationFilter(authenticationManagerBean());
	}
	
}
