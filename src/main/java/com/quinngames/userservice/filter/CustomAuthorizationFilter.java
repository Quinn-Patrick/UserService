package com.quinngames.userservice.filter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import com.quinnsgames.userservice.utility.TokenAuthorizer;

//The authorization filter filters requests based on the authorization header.
@CrossOrigin
public class CustomAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getServletPath().equals("api/login") || request.getServletPath().equals("api/token/refresh")) {
			filterChain.doFilter(request, response);
		} else {
			String authorizationHeader = request.getHeader(AUTHORIZATION);
			if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				boolean success = TokenAuthorizer.authorizeToken(response, authorizationHeader);
				if(success) {
					filterChain.doFilter(request,  response);
				}
			} else {
				filterChain.doFilter(request,  response);
			}
		}
	}
}
