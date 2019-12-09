package com.piesat.config;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 
 * @author xk
 *
 */

@Aspect
@Component
@Slf4j
public class AspectLogs {

     //切点整个所有包
     @Pointcut("execution(public * com.piesat.controller.*.*(..))")
     private void controllerAspect(){}


    //请求method前打印内容
     @Before(value = "controllerAspect()")
     public void methodBefore(JoinPoint joinPoint){
         ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
         HttpServletRequest request = requestAttributes.getRequest();
         //打印请求内容
         log.info("===============请求内容===============");
         log.info("请求地址:"+request.getRequestURL().toString());
         log.info("请求方式:"+request.getMethod());
         log.info("请求类方法:"+joinPoint.getSignature());
         log.info("请求类方法参数:"+ Arrays.toString(joinPoint.getArgs()));
         log.info("===============请求内容===============");
     }

     //打印返回值日志
    @AfterReturning(returning = "o",pointcut = "controllerAspect()")
     public void methodAfterReturing(Object o){
        log.info("===============返回内容===============");
        log.info("返回值为：" + JSONArray.toJSONString(o));
        log.info("===============返回内容===============");
     }

}
