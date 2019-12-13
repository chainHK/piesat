package com.piesat.config;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import lombok.extern.slf4j.Slf4j;


    /**
    * @ClassName: RedisCacheConfig
    * @Description: Redis缓存配置
    * @author Kanox
    * @date 2019年12月13日
    *
    */
    
@Slf4j
@Configuration
@EnableCaching
public class RedisCacheConfig {

	@Autowired
	private Environment env;

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
		redisConf.setHostName(env.getProperty("spring.redis.host"));
		redisConf.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));
		redisConf.setPassword(RedisPassword.of(env.getProperty("spring.redis.password")));
		return new LettuceConnectionFactory(redisConf);
	}

	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(600)).disableCachingNullValues();
		return cacheConfig;
	}

	@Bean
	public RedisCacheManager cacheManager() {
		RedisCacheManager rcm = RedisCacheManager.builder(redisConnectionFactory()).cacheDefaults(cacheConfiguration())
				.transactionAware().build();
		return rcm;
	}

	/**
	 * 自定义缓存key的生成类实现
	 */
	@Bean(name = "myKeyGenerator")
	public KeyGenerator myKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... params) {
				log.info("自定义缓存，使用第一参数作为缓存key，params = " + Arrays.toString(params));
				// 仅仅用于测试，实际不可能这么写
				return params[0];
			}
		};
	}
}