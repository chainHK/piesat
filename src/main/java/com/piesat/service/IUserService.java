package com.piesat.service;

import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.entity.User;

public interface IUserService extends IService<User>{
	
	Set<String> getRoles(String username);
	
	Set<String> getPermissions(String username);
	
	User getByUsername(String username);

}
