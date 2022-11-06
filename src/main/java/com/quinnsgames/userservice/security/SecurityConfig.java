package com.quinnsgames.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.quinngames.userservice.filter.CORSFilter;
import com.quinngames.userservice.filter.CustomAuthorizationFilter;
import com.quinnsgames.userservice.dsl.CustomDsl;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled = true)
@EnableWebSecurity
@ComponentScan("com.quinnsgames.userservice")
public class SecurityConfig{
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/api/login/**", "token/refresh/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/**").hasAnyAuthority("ROLE_USER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users").hasAnyAuthority("ROLE_USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/save/**").permitAll();
		http.authorizeRequests().antMatchers("/ws/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterAfter(new CORSFilter(), BasicAuthenticationFilter.class);
		http.cors();
		
		http.apply(CustomDsl.customDsl());
		return http.build();
    }
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
            }
        };
    }
}
