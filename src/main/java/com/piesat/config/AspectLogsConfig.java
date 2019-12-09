package com.piesat.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author xk
 *
 */

@Aspect
@Component
@Slf4j
public class AspectLogsConfig {

	// 切点整个所有包
	@Pointcut("execution(public * com.piesat.controller.*.*(..))")
	private void controllerAspect() {

	}

	// 请求method前打印内容
	@Before(value = "controllerAspect()")
	public void methodBefore(JoinPoint joinPoint) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		// 打印请求内容
		log.info("===============请求内容===============");
		log.info("请求地址:" + request.getRequestURL().toString());
		log.info("请求方式:" + request.getMethod());
		log.info("请求类方法:" + joinPoint.getSignature());
		log.info("请求类方法参数:" + Arrays.toString(joinPoint.getArgs()));
		log.info("===============请求内容===============");
	}

	// 打印返回值日志
	@AfterReturning(returning = "o", pointcut = "controllerAspect()")
	public void methodAfterReturing(Object o) {
		log.info("===============返回内容===============");
		log.info("返回值为：" + JSONArray.toJSONString(o));
		log.info("===============返回内容===============");
	}

	// 异常日志
	@AfterThrowing(value = "controllerAspect()", throwing = "e")
	public void afterThrowingAdvice(Exception e) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		log.error("===============捕获异常===============");
		log.error("请求地址:" + request.getRequestURL().toString());
		log.error("请求方式:" + request.getMethod());
		log.error("AfterThrowing捕获到了 --->" + e);
		log.error("===============捕获异常===============");
	}

}
