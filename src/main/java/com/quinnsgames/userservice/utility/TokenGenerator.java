package com.quinnsgames.userservice.utility;

import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.quinnsgames.userservice.domain.Role;
import com.quinnsgames.userservice.domain.User;

public class TokenGenerator {
	public static String generateAccessToken(HttpServletRequest request, User user) {
		Algorithm algorithm = getAlgorithm();
		return JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000 * 100000))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
				.sign(algorithm);
	}
	
	public static Algorithm getAlgorithm() {
		return Algorithm.HMAC256(System.getenv("SecretString").getBytes());
	}
}
