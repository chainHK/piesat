package com.piesat.service.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.entity.User;
import com.piesat.mapper.UserMapper;
import com.piesat.service.IUserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public Set<String> getRoles(String username) {
		
		Map<String, Object> map = userMapper.selectUserRoles(username);
		Set<String> set = new HashSet<>();
		Set<Entry<String, Object>> entrySet = map.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			String value = entry.getValue().toString();
			set.add(value);
		}
		System.out.println("Roles:"+set);
		return set;
	}

	@Override
	public Set<String> getPermissions(String username) {
		Set<String> permissions = userMapper.getPermissions(username);
		System.out.println("permissions:"+permissions);
		return permissions;
	}

	@Override
	public User getByUsername(String username) {
		User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
		System.out.println("user:"+user);
		return user;
	}



}
