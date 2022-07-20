package com.quinnsgames.userservice;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.quinnsgames.userservice.domain.Role;
import com.quinnsgames.userservice.domain.User;
import com.quinnsgames.userservice.service.UserService;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			
			userService.saveUser(new User(null, "Quinn", "Quinn_Patrick", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Gumwax", "GuyUser", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Torbus", "Blaster5000", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Glamwalch", "Shaggy", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Sporkler", "Gonswaldo", "1234", new ArrayList<>()));
			
			userService.addRoleToUser("GuyUser", "ROLE_USER");
			userService.addRoleToUser("GuyUser", "ROLE_MANAGER");
			userService.addRoleToUser("Blaster5000", "ROLE_MANAGER");
			userService.addRoleToUser("Gonswaldo", "ROLE_ADMIN");
			userService.addRoleToUser("Shaggy", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("Shaggy", "ROLE_ADMIN");
			userService.addRoleToUser("Shaggy", "ROLE_USER");
		};
	}

}
