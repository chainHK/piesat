package com.piesat.mapper;

import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.entity.User;

public interface UserMapper extends BaseMapper<User>{

	Map<String, Object> selectUserRoles(String username);
	
	Set<String> getPermissions(String username);
}
