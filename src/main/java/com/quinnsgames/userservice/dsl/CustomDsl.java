package com.quinnsgames.userservice.dsl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.quinngames.userservice.filter.CustomAuthenticationFilter;

public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity>{
	@Override
	public void configure(HttpSecurity http) throws Exception{
		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
		UsernamePasswordAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager);
		customAuthenticationFilter.setFilterProcessesUrl("/api/login");
		http.addFilter(customAuthenticationFilter);
	}
	
	public static CustomDsl customDsl() {
		return new CustomDsl();
	}
}
