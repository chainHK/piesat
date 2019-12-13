package com.piesat.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.entity.Emp;
import com.piesat.mapper.EmpDao;
import com.piesat.service.IEmpService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xk
 * @since 2019-12-08
 */

@Slf4j
@Service
public class EmpServiceImpl extends ServiceImpl<EmpDao, Emp> implements IEmpService {

	@Autowired
	private EmpDao empDao;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
     * cacheNames 设置缓存的值
     * key：指定缓存的key，这是指参数id值。key可以使用spEl表达式
     *
     * @param id
     * @return
     */
   // @SuppressWarnings("unchecked")
	@Cacheable(value = "empCache", key = "#empno", unless="#result == null")
    public Emp getById(int empno) {
        log.info("获取用户start...");
        
//        redisTemplate.opsForValue().set("key", empno);
//        Object object = redisTemplate.opsForValue().get("key");
//        
//        System.out.println(object);
        
        return empDao.selectById(empno);
    }
}
