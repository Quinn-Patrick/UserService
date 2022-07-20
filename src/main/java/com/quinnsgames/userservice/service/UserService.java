package com.quinnsgames.userservice.service;

import java.util.List;

import com.quinnsgames.userservice.domain.Role;
import com.quinnsgames.userservice.domain.User;

public interface UserService {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	User getUser(String username);
	List<User> getUsers();
	User updateUser(User user);
}
