package com.piesat.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.entity.Class;
import com.piesat.service.IClassService;
import com.piesat.utils.JsonResult;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xk
 * @since 2019-12-08
 */
@Slf4j
@RestController
@RequestMapping("/class")
public class ClassController{
	
	@Autowired 
	private IClassService classService;
	
	@GetMapping("/showAllClass")
	public JsonResult showAllClass(@RequestParam Map<String, Object> map) {
		
		QueryWrapper<com.piesat.entity.Class> queryWrapper=new QueryWrapper<>();
		//queryWrapper.eq("cid", 1);
		
		List<com.piesat.entity.Class> list = classService.list(queryWrapper);
				
		return JsonResult.createResponse(200, "success", list);
		
	}
	
	@GetMapping("/showAllClassPage")
	public JsonResult showAllClassPage(@RequestParam Map<String, Object> map) {
		
		
		IPage<com.piesat.entity.Class> page = new Page<>(1, 2);
				
		QueryWrapper<com.piesat.entity.Class> queryWrapper=new QueryWrapper<>();
		
		queryWrapper.gt("cid", 2);

		IPage<Class> page2 = classService.page(page,queryWrapper);
		
		return JsonResult.createResponse(200, "success", page2);
		
	}
	
}
