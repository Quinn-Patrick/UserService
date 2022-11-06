package com.quinnsgames.userservice;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.quinnsgames.userservice.domain.Role;
import com.quinnsgames.userservice.domain.User;
import com.quinnsgames.userservice.service.UserService;

@SpringBootApplication
@ComponentScan
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			
			userService.saveUser(new User(null, "User1", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "User2", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "User3", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "User4", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "User5", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "User6", "1234", new ArrayList<>()));
			
			
			userService.addRoleToUser("User1", "ROLE_USER");
			userService.addRoleToUser("User2", "ROLE_USER");
			userService.addRoleToUser("User3", "ROLE_USER");
			userService.addRoleToUser("User4", "ROLE_USER");
			userService.addRoleToUser("User5", "ROLE_USER");
			userService.addRoleToUser("User6", "ROLE_USER");
		};
	}*/

}
