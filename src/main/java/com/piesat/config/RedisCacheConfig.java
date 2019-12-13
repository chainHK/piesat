package com.piesat.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: RedisCacheConfig
 * @Description: Redis缓存配置
 * @author Kanox
 * @date 2019年12月13日
 *
 */

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

	@Autowired
	private LettuceConnectionFactory lettuceConnectionFactory;

	/*
	 * @Autowired private Environment env;
	 * 
	 * @Bean public LettuceConnectionFactory redisConnectionFactory() {
	 * 
	 * //单机模式配置 // RedisStandaloneConfiguration redisConf = new
	 * RedisStandaloneConfiguration(); //
	 * redisConf.setHostName(env.getProperty("spring.redis.host")); //
	 * redisConf.setPort(Integer.parseInt(env.getProperty("spring.redis.port"))); //
	 * redisConf.setPassword(RedisPassword.of(env.getProperty(
	 * "spring.redis.password")));
	 * 
	 * //集群模式配置 RedisClusterConfiguration redisClusterConf=new
	 * RedisClusterConfiguration(); Iterable<RedisNode> nodes=new ArrayList<>();
	 * redisClusterConf.setClusterNodes(nodes);; LettuceConnectionFactory
	 * lettuceConnectionFactory=new LettuceConnectionFactory(redisClusterConf);
	 * lettuceConnectionFactory.afterPropertiesSet(); return
	 * lettuceConnectionFactory; }
	 */

	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig()
				.serializeKeysWith(
						RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(RedisSerializationContext.SerializationPair
						.fromSerializer(new GenericJackson2JsonRedisSerializer()));
	}

	@Bean
	public RedisCacheManager cacheManager() {
		RedisCacheManager rcm = RedisCacheManager.builder(lettuceConnectionFactory).cacheDefaults(cacheConfiguration())
				.transactionAware().build();
		return rcm;
	}

	@SuppressWarnings("all")
	@Bean
	public RedisTemplate redisTemplate(LettuceConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		RedisSerializer stringSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringSerializer);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.setHashKeySerializer(stringSerializer);
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

	/**
	 * 自定义缓存key的生成类实现
	 */
//	@Bean(name = "myKeyGenerator")
//	public KeyGenerator myKeyGenerator() {
//		return new KeyGenerator() {
//			@Override
//			public Object generate(Object o, Method method, Object... params) {
//				log.info("自定义缓存，使用第一参数作为缓存key，params = " + Arrays.toString(params));
//				// 仅仅用于测试，实际不可能这么写
//				return params[0];
//			}
//		};
//	}

	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuffer sb = new StringBuffer();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

}