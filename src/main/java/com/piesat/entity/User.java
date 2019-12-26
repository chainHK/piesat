package com.piesat.entity;

import java.util.Set;

import lombok.Data;

@Data
public class User {

	private Integer id;
	private String username;
	private String password;
	private Set<Integer> roleIds;
	
}
