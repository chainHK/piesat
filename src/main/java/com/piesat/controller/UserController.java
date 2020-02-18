package com.piesat.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.piesat.entity.User;
import com.piesat.service.IUserService;
import com.piesat.utils.JsonResult;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userserver;

	@GetMapping("/test")
	public String tset(User user) {

		Set<String> roles = userserver.getRoles(user.getUsername());

		System.out.println(roles);
		return "login";
	}

	/**
	 * 身份认证测试接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin")
	public String admin(HttpServletRequest request) {
		Object user = request.getSession().getAttribute("user");
		return "success";
	}

	/**
	 * 角色认证测试接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/student")
	@ResponseBody
	public JsonResult student(HttpServletRequest request) {
		return JsonResult.createSuccessResponse("角色认证测试通过");
	}

	/**
	 * 权限认证测试接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/teacher")
	@ResponseBody
	public JsonResult teacher(HttpServletRequest request) {
		return JsonResult.createSuccessResponse("权限认证测试通过");
	}

	/**
	 * 用户登录接口
	 * 
	 * @param user    user
	 * @param request request
	 * @return string
	 */
	@PostMapping("/login")
	public String login(User user, HttpServletRequest request) {

		// 根据用户名和密码创建 Token
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		// 获取 subject 认证主体
		Subject subject = SecurityUtils.getSubject();
		try {
			// 开始认证，这一步会跳到我们自定义的 Realm 中
			subject.login(token);
			request.getSession().setAttribute("user", user);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("user", user);
			request.setAttribute("error", "用户名或密码错误！");
			return "login";
		}
	}

}
