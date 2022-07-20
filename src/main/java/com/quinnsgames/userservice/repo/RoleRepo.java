package com.quinnsgames.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quinnsgames.userservice.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long>  {
	Role findByName(String username);
}
