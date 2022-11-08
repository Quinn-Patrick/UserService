package com.quinnsgames.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

//Welcome to Quinn's 1 on 1 chat app, created as a coding challenge for a job opportunity.
//This is based on a Spring Security tutorial by AmigosCode which I completed long before this, although it is heavily modified.
//I created a github repo called userservice from the tutorial, and forked it for the purposes of this project,
//adding all messaging and websocket functionality.
@SpringBootApplication
@ComponentScan
@EnableWebSocketMessageBroker
@EnableWebSocket
public class UserserviceApplication {
	//Run from here.
	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	//Register the password encoder bean.
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
