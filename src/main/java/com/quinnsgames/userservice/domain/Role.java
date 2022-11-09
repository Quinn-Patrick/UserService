package com.quinnsgames.userservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//The role model. Roles are mostly something left over from the tutorial.
//The only currently used role is ROLE_USER.
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Role {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
}
