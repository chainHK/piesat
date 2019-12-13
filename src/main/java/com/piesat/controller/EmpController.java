package com.piesat.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piesat.entity.Emp;
import com.piesat.service.IEmpService;
import com.piesat.utils.JsonResult;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xk
 * @since 2019-12-08
 */
@RestController
@RequestMapping("/emp")
public class EmpController{

	@Autowired 
	private IEmpService empService;
	
	@GetMapping("/showAllEmp")
	public JsonResult showAllClass(@RequestParam Map<String, Object> map) {
		
		Emp emp = empService.getById(1001);
				
		return JsonResult.createResponse(200, "success", emp);
		
	}
}
